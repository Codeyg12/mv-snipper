package com.codey.snipperapp.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.util.Base64;
import java.util.Date;

@Component
public class JwtUtil {
  @Value("${jwt.secret}")
  private String secret;

  public String generateToken(String email) {
    return Jwts.builder()
        .subject(email)
        .issuedAt(Date.from(Instant.now()))
        .expiration(Date.from(Instant.now().plusMillis(86400000)))
        .signWith(generateKey())
        .compact();
  }

  private SecretKey generateKey() {
    byte[] decodedKey = Base64.getDecoder().decode(secret);
    return Keys.hmacShaKeyFor(decodedKey);
  }

  public boolean validateToken(String token) {
    Claims claims = getClaims(token);
    return claims.getExpiration().after(Date.from(Instant.now()));
  }

  // Add this method to match what JwtFilter is calling
  public boolean validateToken(String token, UserDetails userDetails) {
    String email = extractEmail(token);
    return (email.equals(userDetails.getUsername()) && validateToken(token));
  }

  // Rename this method to match what JwtFilter is calling
  public String extractEmail(String token) {
    Claims claims = getClaims(token);
    return claims.getSubject();
  }

  // Keep this method for backward compatibility
  public String getEmailFromToken(String token) {
    return extractEmail(token);
  }

  private Claims getClaims(String jwt) {
    return Jwts.parser()
        .verifyWith(generateKey())
        .build()
        .parseSignedClaims(jwt)
        .getPayload();
  }
}