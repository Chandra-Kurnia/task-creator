package com.example.taskcreator.utils;

import com.example.taskcreator.entities.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.function.Function;

@Component
public class JwtUtil {
    @Value("${jwt.secretToken}")
    private String secret;

    @Value("${jwt.secretRefreshToken}")
    private String secretRefresh;

    @Value("${jwt.expiration}")
    private int expiration;

    public String generateToken(User user, Boolean isRefreshToken) {
        if(isRefreshToken) {
            return tokenGenerator(user, 5000L, secretRefresh);
        }else {
            return tokenGenerator(user, 1000L, secret);
        }
    }

    public String tokenGenerator(User user, Long expireToken, String secretKey) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + expiration * expireToken);
        Claims claims = Jwts.claims().setSubject(user.getUsername());
        claims.put("userId", user.getUserId());
        claims.put("username", user.getUsername());

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .compact();
    }

    public Date extractExpirationDate(String token, Boolean isRefreshToken) {
        return extractClaim(token, Claims::getExpiration, isRefreshToken);
    }

    public User getDataToken(String token, Boolean isRefreshToken) {
        User user = new User();
        Claims claims = Jwts.parser().setSigningKey(isRefreshToken ? secretRefresh : secret)
                .parseClaimsJws(token).getBody();

        Integer userIdInt = (Integer) claims.get("userId");
        user.setUserId(userIdInt.longValue());
        user.setUsername((String) claims.get("username"));

        return user;
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver, Boolean isRefreshToken) {
        Claims claims = extractAllClaims(token, isRefreshToken);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token, Boolean isRefreshToken) {
        return Jwts.parser()
                .setSigningKey(isRefreshToken ? secretRefresh : secret)
                .parseClaimsJws(token)
                .getBody();
    }

    public boolean isTokenExpired(String token, Boolean isRefreshToken) {
        Date expirationDate = extractExpirationDate(token, isRefreshToken);
        return expirationDate.before(new Date());
    }

    public Boolean validateRefreshToken(String refreshToken) {
        try {
            if(refreshToken == null) {
                return false;
            }

            boolean expire = isTokenExpired(refreshToken, true);
            if(expire) {
                return false;
            }
        }catch (Exception e){
            return false;
        }

        return true;
    }

    public void forceExpireToken(String token, Boolean isRefreshToken) {
        Claims claims = Jwts
                .parser()
                .setSigningKey(isRefreshToken ? secretRefresh : secret)
                .parseClaimsJws(token).getBody();

        claims.setExpiration(new Date(System.currentTimeMillis() - 1000));
    }

}
