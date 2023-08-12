package com.lms.forget_password.utility;

import com.lms.forget_password.dto.OtpDto;
import com.lms.forget_password.exceptions.UserException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtility {

    @Autowired
    OtpDto otpDto;

    public String generateTokenUsingOtp(OtpDto otpDto){

        Date currentDate = new Date();
        Date expiryDate = new Date(currentDate.getTime() + SecurityConstants.JWT_EXPIRATION_TIME_IN_MS);

        Map<String,Object> claims = new HashMap<>();
        claims.put("email",otpDto.getEmail());
        claims.put("otp",otpDto.getOtp());
        return Jwts
                .builder()
                .setClaims(claims)
                .setIssuedAt(currentDate)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS256,SecurityConstants.JWT_SECRET_KEY)
                .compact();
    }

    public boolean validateToken (String token){
        try {
            Jwts.parser()
                    .setSigningKey(SecurityConstants.JWT_SECRET_KEY)
                    .parseClaimsJws(token);
            return true;
        }catch (ExpiredJwtException e){
            throw new UserException("Token Is Invalid Or Expired ", HttpStatus.BAD_GATEWAY,null);
        }
    }

    public OtpDto decodeToken (String token){

        if(validateToken(token)){
            Claims claims = Jwts

                    .parser()
                    .setSigningKey(SecurityConstants.JWT_SECRET_KEY)
                    .parseClaimsJws(token)
                    .getBody();

            otpDto.setEmail(claims.get("email").toString());
            otpDto.setOtp((int) claims.get("otp"));
            return otpDto;
        }
        else {
            return null;
        }
    }




}
