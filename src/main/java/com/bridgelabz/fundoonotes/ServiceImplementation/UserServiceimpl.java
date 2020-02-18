package com.bridgelabz.fundoonotes.ServiceImplementation;





import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.transaction.Transactional;

import org.apache.commons.logging.Log;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.bridgelabz.fundoonotes.DTO.LoginDetails;
import com.bridgelabz.fundoonotes.DTO.UpdatePassword;
import com.bridgelabz.fundoonotes.DTO.UserDto;
import com.bridgelabz.fundoonotes.Model.UserDemo;
import com.bridgelabz.fundoonotes.Repository.UserRepository;
import com.bridgelabz.fundoonotes.Response.MailObject;
import com.bridgelabz.fundoonotes.Response.MailResponse;
import com.bridgelabz.fundoonotes.Sevice.UserService;
import com.bridgelabz.fundoonotes.config.RabbitMqSender;
import com.bridgelabz.fundoonotes.customexceptions.EmailAlreadyExistException;
import com.bridgelabz.fundoonotes.customexceptions.EmailNotFoundException;
import com.bridgelabz.fundoonotes.customexceptions.UserNotVerifiedException;
import com.bridgelabz.fundoonotes.utility.JwtGenerator;
import com.bridgelabz.fundoonotes.utility.MailServiceProvider;
import com.bridgelabz.fundoonotes.utility.Util;




@Service


public  class UserServiceimpl  implements UserService  {
	
private Log log;
		@Autowired
		private UserRepository userRepository;

		@Autowired
		private ModelMapper modelMapper;

		@Autowired
		private MailResponse mailresponse;

		@Autowired
		private JwtGenerator generate;

		@Autowired
		private MailObject mailObject;


		@Autowired
		private BCryptPasswordEncoder encryption;

		
		@Transactional
		@Override
		public UserDemo registration(UserDto userDto) throws Exception  {
			UserDemo userDetails = new UserDemo();

			Optional<UserDemo> checkEmailAvailability = userRepository.findOneByEmail(userDto.getEmail());
			if(checkEmailAvailability.isPresent()) {
				throw new EmailAlreadyExistException(userDto.getEmail()+" Already Exist");
			}
			else{
				userDetails.setEmail(userDto.getEmail());
				userDetails.setFirstName(userDto.getFirstName());
				userDetails.setLastName(userDto.getLastName());
				userDetails.setPhone(Long.parseLong(userDto.getPhone()));
				userDetails.setCreatedAt(Util.dateTime());
				userDetails.setVerified(false);
				String password = encryption.encode(userDto.getPswd());
				userDetails.setPswd(password);

				userRepository.save(userDetails);
				System.out.println(userDetails);

				String response = mailresponse.formMessage("http://localhost:8080/users/verify/",
						generate.jwtToken(userDetails.getUserId()));
				//log.info("Response URL :" + response);
				mailObject.setEmail(userDto.getEmail());
				mailObject.setMessage(response);
				mailObject.setSubject("verification");
				MailServiceProvider.sendEmail(mailObject.getEmail(),mailObject.getSubject(),mailObject.getMessage());
				//rabbitMqSender.send(mailObject);
				return userDetails;
			} 

		}

		@Override
		public List<UserDemo> getAllDetails() {
			return (List<UserDemo>) userRepository.findAll();
		}

		@Override
		public Map<String, Object> findByIdUserId(long userId) {
			Optional<UserDemo> isUserIdAvailable = userRepository.findById(userId);
			Map<String, Object> map = null;
			if (isUserIdAvailable.isPresent()) {
				map = new HashMap<String, Object>();
				map.put("UserId", isUserIdAvailable.get().getUserId());
				map.put("FirstName", isUserIdAvailable.get().getFirstName());
				map.put("LastName", isUserIdAvailable.get().getLastName());
			} else {

			}
			return map;
		}

		@Override
		public boolean updateDetails(UserDemo user) {
			long userId = user.getUserId();
			String pswd = user.getPswd();

			String encrpswd = Util.passwordEncoder(pswd);

			Optional<UserDemo> userinfo = userRepository.findOneByUserIdAndPswd(userId, encrpswd);
			if (userinfo.isPresent()) {
				userRepository.save(user);
				return true;
			}
			return false;
		}

		@Override
		public boolean findByUserId(long userId) {

			Optional<UserDemo> isUserIdAvailable = userRepository.findById(userId);
			if (isUserIdAvailable.isPresent()) {
				userRepository.deleteById(userId);
				return true;
			}
			return false;
		}

		@Transactional
		@Override
		public UserDemo login(LoginDetails loginDetails) throws Exception  {
			Optional<UserDemo> userInfo = userRepository.findOneByEmail(loginDetails.getEmail());

			if (userInfo.isPresent()) {
			//	log.info("User Information " + userInfo);
				if ((userInfo.get().isVerified())
						&& encryption.matches(loginDetails.getPassword(), userInfo.get().getPswd())) {

				//	log.info("Generated Token :" + generate.jwtToken(userInfo.get().getUserId()));

					return userInfo.get();
				} else {
					String response = mailresponse.formMessage("http://localhost:8080/users/verify",
							generate.jwtToken(userInfo.get().getUserId()));

					log.info("Verification Link :" + response);

					MailServiceProvider.sendEmail(loginDetails.getEmail(), "verification", response);

					throw new UserNotVerifiedException("Invalid credentials");
				}

			} else {
				throw new EmailNotFoundException(loginDetails.getEmail()+"Please register before login");
			}
		}

		@Transactional
		@Override
		public boolean verify(String token) throws Throwable  {
	//		log.info("id in verification" + (long) generate.parseJWT(token));
			Long id = (long) generate.parseJWT(token);
			Optional<UserDemo> userInfo = userRepository.findById(id);
			if (userInfo.isPresent()) {
				userInfo.get().setVerified(true);
				userRepository.save(userInfo.get());
				return true;
			}
			return false;
		}

		@Override
		public boolean isUserAvailable(String email) throws Exception  {
			Optional<UserDemo> isUserAvailable = userRepository.findOneByEmail(email);
			if (isUserAvailable.isPresent() && isUserAvailable.get().isVerified() == true) {
				String response = mailresponse.formMessage("http://localhost:3000/updatePassword",
						generate.jwtToken(isUserAvailable.get().getUserId()));
				MailServiceProvider.sendEmail(isUserAvailable.get().getEmail(), "Update Password", response);

				return true;
			} else {
				return false;
			}
		}

		@Override
		public UserDemo updatePassword(String token, UpdatePassword pswd) throws Exception {
			if (pswd.getNewpassword().equals(pswd.getChnpassword())) {
			//	log.info("Getting id from token :" + generate.parseJWT(token));
				long id = generate.parseJWT(token);
				Optional<UserDemo> isUserIdAvailable = userRepository.findById(id);
				if (isUserIdAvailable.isPresent()) {
					String password = encryption.encode(pswd.getNewpassword());
					isUserIdAvailable.get().setPswd(password);
					userRepository.save(isUserIdAvailable.get());
					return isUserIdAvailable.get();
				}
			}
			return null;
		}

		@Override
		public List<UserDemo> getAllUsers() {
			List<UserDemo> users = (List<UserDemo>) userRepository.findAll();
			return users;
		}

		@Override
		public UserDemo UpdatePassword(String token, com.bridgelabz.fundoonotes.DTO.UpdatePassword pswd) {

			return null;
		}
}