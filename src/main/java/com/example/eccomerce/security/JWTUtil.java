package com.example.eccomerce.security;

import com.example.eccomerce.models.UserModel;
import com.example.eccomerce.repositories.UserRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.Date;
import java.util.Optional;

@Component
public class JWTUtil {

    private static final String secret = "apiSerratecSecret";

    private static final int expiration = 3600000;

    @Autowired
    UserRepository userRepository;

    public String generateToken(String username) {
        return Jwts.builder().setSubject(username).setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(SignatureAlgorithm.HS512, secret.getBytes()).compact();
    }

    public static Authentication getAuthentication(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if (token != null) {
            String user = Jwts.parser().setSigningKey(secret.getBytes()).parseClaimsJws(token.replace("Bearer", "")).getBody().getSubject();
            if (user != null) {
                return new UsernamePasswordAuthenticationToken(user, null, Collections.emptyList());
            }
        }
        return null;
    }

    public UserModel getLoggedUser(String token) {
        if (token != null) {
            String user = Jwts.parser().setSigningKey(secret.getBytes()).parseClaimsJws(token.replace("Bearer", "")).getBody().getSubject();
            if (user != null) {
                Optional<UserModel> optional = userRepository.findByUsername(user);
                if (optional.isEmpty()) {
                    return null;
                }
                return optional.get();
            }
        }
        return null;
    }

}
