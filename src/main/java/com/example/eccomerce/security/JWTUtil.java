package com.example.eccomerce.security;

import com.example.eccomerce.exceptions.ErrorException;
import com.example.eccomerce.models.UserModel;
import com.example.eccomerce.services.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.Date;

@Component
public class JWTUtil {

	private static String secret = "59ccd823b8ebc9b35b548f5ca07be7db";

	private static int expiration = 60000;

	@Autowired
	UserService service;

	public String generateToken(String username) {
		return Jwts.builder().setSubject(username).setExpiration(new Date(System.currentTimeMillis() + expiration))
				.signWith(SignatureAlgorithm.HS512, secret.getBytes()).compact();
	}

	public static Authentication getAuthentication(HttpServletRequest request) throws ErrorException {
		String token = request.getHeader("Authorization");
		if (token != null) {
			try{
				String user = Jwts.parser().setSigningKey(secret.getBytes()).parseClaimsJws(token.replace("Bearer", ""))
						.getBody().getSubject();

				if (user != null) {
					return new UsernamePasswordAuthenticationToken(user, null, Collections.emptyList());
				}
			}catch (Exception e){
				throw new ErrorException("JWT expired");
			}
		}
		return null;
	}

	public UserModel getCreditials(String token) {
		if (token != null) {
			String user = Jwts.parser().setSigningKey(secret.getBytes()).parseClaimsJws(token.replace("Bearer", ""))
					.getBody().getSubject();
			if (user != null) {
				return service.getUser(user);
			}
		}
		return null;
	}

}
