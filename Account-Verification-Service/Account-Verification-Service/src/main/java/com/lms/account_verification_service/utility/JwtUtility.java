package com.lms.account_verification_service.utility;

import com.lms.account_verification_service.dto.LoginDto;
import com.lms.account_verification_service.exceptions.UserException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class JwtUtility {

    @Autowired
    LoginDto loginDto;

    public boolean validateToken (String token){
        try {
            Jwts.parser()
                    .setSigningKey(SecurityConstants.JWT_SECRET_KEY)
                    .parseClaimsJws(token);
            return true;
        }catch (ExpiredJwtException | MalformedJwtException e){
            throw new UserException("Token Is Invalid Or Expired ", HttpStatus.BAD_GATEWAY,null);
        }
    }

    public LoginDto decodeToken (String token){

        if(validateToken(token)){
            Claims claims = Jwts
                                .parser()
                                .setSigningKey(SecurityConstants.JWT_SECRET_KEY)
                                .parseClaimsJws(token)
                                .getBody();

            loginDto.setEmail(claims.get("email").toString());
            loginDto.setPassword(claims.get("password").toString());
            return loginDto;
        }
        else {
            return null;
        }
    }

}
