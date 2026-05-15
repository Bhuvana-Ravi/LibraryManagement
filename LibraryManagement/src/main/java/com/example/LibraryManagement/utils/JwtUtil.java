package com.example.LibraryManagement.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtUtil {

    //1. jjwt-api  2. jjwt-impl  3. jjwt- jackson  add these dependency in pom.xml

    //Secret key
    private final String SECRET="mysecretkeymysecretkeymysecretkey12345";

    private final SecretKey key= Keys.hmacShaKeyFor(SECRET.getBytes());

    //Generate Token

    public String generateToken(String username){
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(
                        new Date(
                                System.currentTimeMillis()+1000*60*60
                        )
                )
                .signWith(key)
                .compact();
    }

    //-------  Extract Username--------
    public String extractUsername(String token){
        Claims claims=Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload();
        return claims.getSubject();
    }

    //Validate Token

    public boolean validateToken(String token){
        try{
            Jwts.parser()
                    .verifyWith(key)
                    .build()
                    .parseSignedClaims(token);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
