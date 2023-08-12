package com.lms.login_service.jwt_config;

import com.lms.login_service.dto.LoginDto;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtility {

    public String generateToken(LoginDto loginDto){

        Date currentDate = new Date();
        Date expiryDate = new Date(currentDate.getTime() + SecurityConstants.JWT_EXPIRATION_TIME_IN_MS);

        Map<String, Object> claims = new HashMap<>();
        claims.put("email",loginDto.getEmail());
        claims.put("password",loginDto.getPassword());

        return Jwts
                .builder()
                .setClaims(claims)
                .setIssuedAt(new Date())
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS256,SecurityConstants.JWT_SECRET_KEY)
                .compact();

    }


}
