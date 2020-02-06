package com.blz.Sevice;

import java.util.Map;

import javax.validation.Valid;

import java.io.UnsupportedEncodingException;
import java.util.List;
import com.blz.DTO.LoginDetails;
import com.blz.DTO.UpdatePassword;
import com.blz.DTO.UserDto;
import com.blz.Model.UserDemo;

public interface UserService {

	UserDemo registration( UserDto userDto) throws UnsupportedEncodingException;

	List<UserDemo> getAllDetails();

	Map<String, Object> findByIdUserId(long userId);

	boolean updateDetails(UserDemo user);

	boolean findByUserId(long userId);

	UserDemo login(LoginDetails loginDetails) throws UnsupportedEncodingException;

	boolean verify(String token) throws UnsupportedEncodingException;

	boolean isUserAvailable(String email) throws UnsupportedEncodingException;

	UserDemo updatePassword(String token, UpdatePassword pswd) throws UnsupportedEncodingException;

	List<UserDemo> getAllUsers();

	UserDemo UpdatePassword(String token, UpdatePassword pswd);



}
	

