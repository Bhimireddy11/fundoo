package com.bridgelabz.fundoonotes.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


import com.amazonaws.services.s3.model.S3Object;
import com.bridgelabz.fundoonotes.Model.Profile;
import com.bridgelabz.fundoonotes.Response.Response;
import com.bridgelabz.fundoonotes.Sevice.ProfilePicService;

import io.swagger.annotations.ApiOperation;
@CrossOrigin("*")
@RestController
public class ProfilePicController {

	@Autowired
	private ProfilePicService profilePicService;

	/* Api to upload a profice pic */
	@PostMapping("/uploadprofilepic")
	@ApiOperation(value = "Api to upload profile pic of User for Fundoonotes", response = Response.class)
	public ResponseEntity<Response> addProfilePic(@ModelAttribute MultipartFile file,
			@RequestHeader("token") String token) {
		Profile profile = profilePicService.storeObjectInS3(file, file.getOriginalFilename(), file.getContentType(),
				token);
		return profile.getUserLabel() != null
				? ResponseEntity.status(HttpStatus.OK).body(new Response("profile added succussefully"))
				: ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("something went Wrong "));
	}
	/* Api to update a profice pic */
	@PutMapping("/updateprofilepic")
	@ApiOperation(value = "Api to update profile pic of User for Fundoonotes", response = Response.class)
	public ResponseEntity<Response> updateProfile(@ModelAttribute MultipartFile file , @RequestHeader("token") String token){
		Profile profile = profilePicService.updateObejctInS3(file, file.getOriginalFilename(), file.getContentType(),
				token);
		return profile.getUserLabel() != null
				? ResponseEntity.status(HttpStatus.OK).body(new Response("profile updated succussefully"))
				: ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("something went Wrong "));
	}
	

	/* Api to get profile pic */
	@GetMapping("/getprofilepic")
	@ApiOperation(value = "Api to get profile pic", response = Response.class)
	public ResponseEntity<Response> getProfilePic(@RequestHeader("token") String token){
	
	S3Object s3 = 	profilePicService.getProfilePic(token);
		return s3!=null ?  ResponseEntity.status(HttpStatus.OK).body(new Response("profile pic"))
				: ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("no profile pic "));
	}
}


