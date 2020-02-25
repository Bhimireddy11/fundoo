package com.bridgelabz.fundoonotes.config;

	
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;



@Configuration
public class AwsConfiguration {
	
	
	
	
	private String accessKey=System.getenv("accessKey");
	
	private String secretKey=System.getenv("secretKey");
   private String region=System.getenv("region");
	@Bean
	public AmazonS3 awsS3Clint() {
		BasicAWSCredentials awsCreds= new BasicAWSCredentials(accessKey, secretKey);
		return AmazonS3ClientBuilder.standard().withRegion(Regions.fromName(region))
				   .withCredentials(new AWSStaticCredentialsProvider(awsCreds)).build();
         
	}



	
	
			

}
