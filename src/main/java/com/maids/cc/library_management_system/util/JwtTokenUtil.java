package com.maids.cc.library_management_system.util;

import com.maids.cc.library_management_system.config.PropsReader;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;
import java.util.function.Function;

@Component
@Slf4j
@RequiredArgsConstructor
public class JwtTokenUtil implements Serializable {
    private final PropsReader propsReader;

    public String generateJwtToken(Authentication authentication) {
        var userPrincipal = (UserDetails) authentication.getPrincipal();
        return Jwts.builder()
                .setSubject((userPrincipal.getUsername()))
                .setIssuedAt(new Date())
                .setExpiration(new Date((System.currentTimeMillis() * propsReader.getAccessTokenValiditity())))
                .signWith(SignatureAlgorithm.HS512, propsReader.getSecret())
                .compact();
    }

    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(propsReader.getSecret()).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException e) {
            log.error("Invalid JWT signature: {}", e.getMessage());
        } catch (MalformedJwtException e) {
            log.error("Invalid JWT token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            log.error("JWT token is expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            log.error("JWT token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            log.error("JWT claims string is empty: {}", e.getMessage());
        }
        return false;
    }

    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey(propsReader.getSecret()).parseClaimsJws(token).getBody();
    }
}
