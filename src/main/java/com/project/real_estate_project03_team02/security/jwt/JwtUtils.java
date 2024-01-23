package com.project.real_estate_project03_team02.security.jwt;


import com.project.real_estate_project03_team02.security.service.UserDetailsImpl;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtils {

    private static final Logger LOGGER= LoggerFactory.getLogger(JwtUtils.class);


    @Value("${backendapi.app.jwtExpirationMs}")
    private Long jwtExpirationMs;

    @Value("${backendapi.app.jwtSecret}")
    private String jwtSecret;
    public String generateJwtToken(Authentication authentication){
        //get infos of logged user from context
        UserDetailsImpl userDetails= (UserDetailsImpl) authentication.getPrincipal();
        return generateTokenFromUsername(userDetails.getUsername());
    }
    public boolean validateJwtToken(String jwtToken){
        try{
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(jwtToken);
            return true;
        } catch (ExpiredJwtException e){
            LOGGER.error("JWT token is expired :{}",e.getMessage());
        }catch (UnsupportedJwtException e){
            LOGGER.error("JWT token is unsupported :{}",e.getMessage());
        }catch (MalformedJwtException e){
            LOGGER.error("JWT token is invalid :{}",e.getMessage());
        }catch (SignatureException e){
            LOGGER.error("JWT Signature  is invalid :{}",e.getMessage());
        }catch (IllegalArgumentException e){
            LOGGER.error("JWT  is empty :{}",e.getMessage());
        }
        return false;
    }

    /**
     *
     * @param username as String
     * @return JWT signed with algorithm and our jwtSecret key
     */
    public String generateTokenFromUsername(String username){
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime()+jwtExpirationMs))
                .signWith(SignatureAlgorithm.HS512,jwtSecret)
                .compact();
    }

    public String getUserNameFromJwtToken(String token){
        return Jwts.parser().setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody().getSubject();
    }



}
