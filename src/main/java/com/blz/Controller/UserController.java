package com.blz.Controller;

import java.io.UnsupportedEncodingException;

import java.util.List;

import javax.validation.Valid;

import org.apache.catalina.connector.Response;
import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.blz.DTO.LoginDetails;
import com.blz.DTO.UpdatePassword;
import com.blz.DTO.UserDto;
import com.blz.Model.UserDemo;
import com.blz.Repository.UserRepository;
import com.blz.Responce.Responce;
import com.blz.Responce.UserAuntication;
import com.blz.Sevice.UserService;
import com.blz.utility.JwtGenerator;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;




@RestController
@Slf4j
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private  JwtGenerator generate;

	@Autowired
	private UserRepository userRepository;

	private Log log;

	/*
	 * API to register new user
	 */
	@PostMapping(value = "/users/register")
	@ApiOperation(value = "Api to register user", response = Response.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "registration successfull"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	public ResponseEntity<Responce> registration(@Valid @RequestBody UserDto userDto, BindingResult bindingResult) throws UnsupportedEncodingException {

		if (bindingResult.hasErrors()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(new Responce(bindingResult.getAllErrors().get(0).getDefaultMessage(), 400, null));
		}else {
			UserDemo user = userService.registration(userDto);
			return user!=null
					? ResponseEntity.status(HttpStatus.CREATED).body(new Responce("registration successfull", 200, user))
					: ResponseEntity.status(HttpStatus.ALREADY_REPORTED)
							.body(new Responce("user already exist", 400, user));
		}
	}

	/*
	 * Api for user authentication
	 */
	@PostMapping("/users/login")
	@ApiOperation(value = "Api for Login", response = Response.class)
	@CachePut(key = "#token", value = "userId")
	public ResponseEntity< UserAuntication> login(@RequestBody LoginDetails loginDetails) throws UnsupportedEncodingException {

		UserDemo userInformation = userService.login(loginDetails);
		loginDetails.setPassword("******");
		if (userInformation != null) {
			String token;
		
				token = generate.jwtToken(userInformation.getUserId());
	
			return ResponseEntity.status(HttpStatus.ACCEPTED).header("login successfull", loginDetails.getEmail())
					.body(new  UserAuntication(token, 200, loginDetails));
		
		
			} else {

			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(new  UserAuntication("Login failed", 404, loginDetails));
		}
	
	}
	/*
	 * API to verify the user
	 */
	@GetMapping("/users/verify/{token}")
	public ResponseEntity<Responce> userVerification(@PathVariable("token") String token) throws UnsupportedEncodingException {

		System.out.println("token for verification" + token);
		boolean update = userService.verify(token);
		return (update) ? ResponseEntity.status(HttpStatus.ACCEPTED).body(new Responce("verified", 200))
				: (ResponseEntity<Responce>) ResponseEntity.status(HttpStatus.ACCEPTED).body(new Responce("not verified", 400));
	}

	/*
	 * API for forgot password
	 */
	@ApiOperation(value = "Api for forgotpassword", response = Responce.class)
	@PostMapping("users/forgotpassword")
	public ResponseEntity<Responce> forgotPassword(@RequestParam("email") String email) throws UnsupportedEncodingException {
		
		log.info("Email :" + email);

		boolean result = userService.isUserAvailable(email);
		return (result) ? ResponseEntity.status(HttpStatus.ACCEPTED).body(new Responce("User Exist", 200))
				: ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Responce("User Doesn't Exist", 400));
	}

	/*
	 * API to update password
	 */
	@PostMapping("users/updatepassword/{token}")
	@ApiOperation(value = "Api for update password", response = Responce.class)
	public ResponseEntity<Responce> updatePassword(@PathVariable("token") String token,
			@RequestBody UpdatePassword pswd) throws UnsupportedEncodingException{

		
		 log.info("Token :" + token);
		log.info("New Password :" + pswd.getChnpassword());

		UserDemo result = userService.updatePassword(token, pswd);
		return (result!=null)
				? ResponseEntity.status(HttpStatus.ACCEPTED).body(new Responce("Password is Update Successfully", 200))
				: ResponseEntity.status(HttpStatus.NOT_MODIFIED).body(new Responce("Password and Confirm Password doesn't matched", 400));
						
	}

	/*
	 * API to get User list
	 * 
	 */
	@GetMapping("users")
	@ApiOperation(value = "Api to get user list", response = Response.class)
	public ResponseEntity<Responce> usersList() {
		List<UserDemo> userList = (List<UserDemo>) userRepository.findAll();
		return ResponseEntity.status(HttpStatus.OK).body(new Responce("Users are", 200, userList));
	}
}