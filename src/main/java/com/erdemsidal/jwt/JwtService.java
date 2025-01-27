package com.erdemsidal.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.function.Function;

@Service
public class JwtService {


    public static final String SECRET_KEY = "KjT6DWn+EK+3WQSfreiG9O1ySehOYjQvMdFGsMsD7Ss=";


    public String generateToken(UserDetails userDetails){

        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 *60 *60 *2))
                .signWith(getKey(), SignatureAlgorithm.HS256)
                .compact();

    }


    public Claims getClaims(String token){
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(getKey())
                .build()
                .parseClaimsJws(token).getBody();
        return claims;
    }

    public <T> T exportToken(String token , Function<Claims,T> claimsFunction){
        Claims claims = getClaims(token);
        return claimsFunction.apply(claims);

    }


    public String getUsernameByToken(String token){
        return exportToken(token,Claims::getSubject);
    }

    public boolean isTokenValid(String token){
        Date expireDate = exportToken(token,Claims::getExpiration);
        return new Date().before(expireDate);
    }

    public Key getKey(){
        byte[] bytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(bytes);

    }




}
