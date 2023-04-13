package com.gateway.jwt;

import com.gateway.pojo.UserLogin;
import com.gateway.pojo.UserRegister;
import com.gateway.service.UserLoginService;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class Jwtutil {

    @Autowired
    private UserLoginService service;
    private static final long EXPIRATION_DURATION = 20 * 60 * 60 * 1000; //24 HOURS

    private static final Logger LOGGER = LoggerFactory.getLogger(Jwtutil.class);

    private String SECRET_KEY = "SECRET_KEY";

    public boolean validateAccessToken(String token)
    {
        try
        {
            String email = getSubject(token);
            System.out.println("subject: "+email);
            //now the user has to be retreaved based on the email
            UserRegister foundUser =  service.getUser(email);
            if(foundUser.getJwtToken().equals(token))
            {
                Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
                return true;
            }
            else
            return false;
        }
        catch(ExpiredJwtException e)
        {
            LOGGER.error("JWT expired");
        }
        catch(IllegalArgumentException e)
        {
            LOGGER.error("Token is null");
        }
        catch(MalformedJwtException e)
        {
            LOGGER.error("JWT invalid");
        }
        catch(UnsupportedJwtException e)
        {
            LOGGER.error("JWT is not supported");
        }
        catch(SignatureException e)
        {
            LOGGER.error("JWT signature validation failed");
        }
        return false;
    }

    public String getSubject(String token)
    {
        return parsClaims(token).getSubject();
    }

    private Claims parsClaims(String token)
    {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();
    }


}
