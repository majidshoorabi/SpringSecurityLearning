package com.github.majidshoorabi.security.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author majid.shoorabi
 * @created 2022-19-May
 * @project peysaz
 */

@Component
public class JwtUtils {


    private final String SECRET = "mySecret";

    public String generateToken(String username){
        return Jwts.builder()
                .setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + (60*15*1000)))
                .signWith(SignatureAlgorithm.HS256, this.SECRET)
                .compact();
    }

    public String getUsername(String token){
        return Jwts.parser()
                .setSigningKey(this.SECRET)      // check jwt is valid
                .parseClaimsJws(token)           // get data of token from signe token
                .getBody()
                .getSubject();                  // get username
    }

}
