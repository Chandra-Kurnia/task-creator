package com.example.taskcreator.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.function.Function;

@Component
public class JwtUtil {
    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private int expiration;

//    public String generateToken(User user) {
//        Date now = new Date();
//        Date expiryDate = new Date(now.getTime() + expiration * 1000);
//        Claims claims = Jwts.claims().setSubject(user.getEmail());
//        claims.put("email", user.getEmail());
//        claims.put("firstname", user.getFirstname());
//        claims.put("lastname", user.getLastname());
//        claims.put("phone", user.getPhone());
//
//        return Jwts.builder()
//                .setClaims(claims)
//                .setIssuedAt(now)
//                .setExpiration(expiryDate)
//                .signWith(SignatureAlgorithm.HS512, secret)
//                .compact();
//    }

    public Date extractExpirationDate(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

//    public UserPojo getDataToken(String token) {
//        UserPojo userPojo = new UserPojo();
//        Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
//
//        userPojo.setFirstname((String) claims.get("firstname"));
//        userPojo.setLastname((String) claims.get("lastname"));
//        userPojo.setEmail((String) claims.get("email"));
//        userPojo.setPhone((String) claims.get("phone"));
//
//        return userPojo;
//    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
    }

    public boolean isTokenExpired(String token) {
        Date expirationDate = extractExpirationDate(token);
        return expirationDate.before(new Date());
    }

}
