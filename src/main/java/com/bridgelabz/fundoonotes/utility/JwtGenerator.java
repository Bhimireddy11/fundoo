package com.bridgelabz.fundoonotes.utility;

import org.springframework.stereotype.Component;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;


	@Component
	public class JwtGenerator {

		private static final String SECRET = "9886002418";
		
		public String jwtToken(long l) {
			String token = null;
			try {
				token = JWT.create().withClaim("id", 1).sign(Algorithm.HMAC512(SECRET));
			} catch (IllegalArgumentException | JWTCreationException e) {

				e.printStackTrace(); 
			}
			return token;
		}

		/*
		 * method to parse the jwt token into integer
		 * */
		public int parseJWT(String jwt) {

			Integer userId = 0;
			if (jwt != null) {
				try {
					userId = JWT.require(Algorithm.HMAC512(SECRET)).build().verify(jwt).getClaim("id").asInt();
				} catch (JWTVerificationException | IllegalArgumentException e) {
					e.printStackTrace();
				}
			}
			return userId;
		}

	}
	

	

