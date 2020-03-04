package com.bridgelabz.fundoonotes.Sevice;

import java.util.List;
import java.util.Map;

import com.bridgelabz.fundoonotes.DTO.LoginDetails;
import com.bridgelabz.fundoonotes.DTO.UpdatePassword;
import com.bridgelabz.fundoonotes.DTO.UserDto;
import com.bridgelabz.fundoonotes.Model.UserDemo;

public interface UserService {

	UserDemo registration( UserDto userDto) throws Exception  ;

	List<UserDemo> getAllDetails();

	Map<String, Object> findByIdUserId(long userId);

	boolean forgotpassword(UserDemo user);

	boolean findByUserId(long userId);

	UserDemo login(LoginDetails loginDetails) throws Exception;

	boolean verify(String token) throws Throwable;

	boolean isUserAvailable(String email) throws Exception ;

	UserDemo updatePassword(String token, UpdatePassword pswd) throws Exception ;

	List<UserDemo> getAllUsers();

	UserDemo UpdatePassword(String token, UpdatePassword pswd);



}
	

