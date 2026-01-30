package com.infinitiasoft.projects.lovable_clone.security;

import com.infinitiasoft.projects.lovable_clone.enity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
//import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.security.core.Authentication;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;

@Component
public class AuthUtil {

    @Value("${jwt.secret}")
    private String jwtSecretKey;

    private SecretKey getJwtSecretKey() {
        return Keys.hmacShaKeyFor(jwtSecretKey.getBytes(StandardCharsets.UTF_8));
    }

    public String generateToken(User user) {
        return Jwts.builder()
                .subject(user.getUsername())
                .claim("id", user.getId().toString())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 60 * 10 * 1000))
                .signWith(getJwtSecretKey())
                .compact();
    }

    public JwtUserPrincipal verifyAccessToken(String token) {
        Claims claims = Jwts.parser().verifyWith(getJwtSecretKey())
                .build().parseSignedClaims(token)
                .getPayload();

        Long userId = Long.parseLong(claims.get("userId", String.class));
        String username = claims.getSubject();
        return new JwtUserPrincipal(userId, username, new ArrayList<>());
    }

    public Long getCurrentUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication == null || !(authentication.getPrincipal() instanceof JwtUserPrincipal)) {
            throw new AuthenticationCredentialsNotFoundException("No jwt found!!");
        }
        JwtUserPrincipal principal = (JwtUserPrincipal) authentication.getPrincipal();
        return principal.userId();
    }
}
