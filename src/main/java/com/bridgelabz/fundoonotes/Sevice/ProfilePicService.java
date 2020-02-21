package com.bridgelabz.fundoonotes.Sevice;


import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import com.amazonaws.services.s3.model.S3Object;
import com.bridgelabz.fundoonotes.Model.Profile;
@Component
public interface ProfilePicService {
	

	Profile storeObjectInS3(MultipartFile file, String originalFilename, String contentType, String token);

	Profile updateObejctInS3(MultipartFile file, String originalFilename, String contentType, String token);

	S3Object getProfilePic(String token);


}
