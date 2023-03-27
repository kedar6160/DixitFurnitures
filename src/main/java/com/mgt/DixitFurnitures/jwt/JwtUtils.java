package com.mgt.DixitFurnitures.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

@Service
public class JwtUtils {

    private String secret ="KedarFucksShilpa";

    public String extractUserName(String token){
        return extractClaims(token,Claims::getSubject);
    }
    public Date extractExpirationDate (String token){
        return extractClaims(token,Claims::getExpiration);

    }
    public <T> T extractClaims(String token, Function<Claims,T> claimResolver){
        final Claims claims= extractAllClaims(token);
        return claimResolver.apply(claims);
    }
    public Claims extractAllClaims(String token){
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }
    private Boolean isTokenExpired(String token){
        return extractExpirationDate(token).before(new Date());
    }

    public String generateToken(String username, String role){
        Map<String,Object> claims =new HashMap<>();
        claims.put("role",role);
        return createToken(claims,username);

    }


    private String createToken(Map<String, Object> Claims, String subject){
        return Jwts.builder()
                .setClaims(Claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() +1000 * 60 * 60 *10))
                .signWith(SignatureAlgorithm.HS256,secret).compact();
    }

    public Boolean validation (String token, UserDetails user){
        final String userName= extractUserName(token);
        return userName.equals(user.getUsername()) && !isTokenExpired(token);
    }
}
