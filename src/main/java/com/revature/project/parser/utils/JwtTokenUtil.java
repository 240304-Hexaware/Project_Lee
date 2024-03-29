package com.revature.project.parser.utils;

import java.util.Date;

import org.springframework.stereotype.Component;

import com.revature.project.parser.exceptions.InvalidJwtException;
import com.revature.project.parser.models.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.InvalidKeyException;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

@Component
public class JwtTokenUtil {

  private String secretKey = "m9rE8zDx5teVBt+7APMo6oznF4XnSsyzMj2ggl30dPk=m9rE8zDx5teVBt+7APMo6oznF4XnSsyzMj2ggl30dPk=";

  public static final String TOKEN_NAME = "token";

  public String createJwt(User user) throws InvalidKeyException {
    return Jwts.builder()
        .setIssuer("admin")
        .claim("userId", user.getId().toHexString())
        .claim("username", user.getUsername())
        .claim("isAdmin", user.getIsAdmin())
        .setIssuedAt(new Date(System.currentTimeMillis()))
        .signWith(Keys.hmacShaKeyFor(secretKey.getBytes()))
        .compact();
  }

  public boolean isAdmin(HttpServletRequest request) throws InvalidJwtException {
    String token = resolveToken(request);
    return extractIsAdmin(token);
  }

  public String getUserIdFromRequest(HttpServletRequest request) throws InvalidJwtException {
    String token = resolveToken(request);
    return extractUserId(token);
  }

  private String resolveToken(HttpServletRequest request) throws InvalidJwtException {
    for (Cookie cookie : request.getCookies()) {
      if (cookie.getName().equals(TOKEN_NAME)) {
        return cookie.getValue();
      }
    }
    throw new InvalidJwtException();
  }

  private String extractUserId(String token) throws InvalidJwtException {
    Jws<Claims> claims = verifyAndGetClaims(token);

    return claims.getBody().get("userId").toString();
  }

  private Jws<Claims> verifyAndGetClaims(String token) throws InvalidJwtException {
    Jws<Claims> claims = null;

    try {
      claims = Jwts.parserBuilder()
          .setSigningKey(secretKey.getBytes())
          .build()
          .parseClaimsJws(token);

    } catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException | SignatureException e) {
      throw new InvalidJwtException();
    }

    return claims;
  }

  private boolean extractIsAdmin(String token) throws InvalidJwtException {
    Jws<Claims> claims = verifyAndGetClaims(token);

    return (boolean) claims.getBody().get("isAdmin");
  }
}
