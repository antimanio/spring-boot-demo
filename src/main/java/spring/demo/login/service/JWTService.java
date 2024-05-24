package spring.demo.login.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.function.Function;

@Component
public class JWTService {

    private String secret = "843567893696976453275974432697R634976R738467TR678T34865R6834R8763T4783786376645387456738657";

    private String algorithm = "HmacSHA256";

    private long expiration_time = 600000;

    private SecretKey key;

    public JWTService() {
        //Secret should not be available in public (production)
        String secret = "843567893696976453275974432697R634976R738467TR678T34865R6834R8763T4783786376645387456738657";
        byte [] keyBytes = Base64.getDecoder().decode(this.secret.getBytes(StandardCharsets.UTF_8));
        this.key = new SecretKeySpec(keyBytes, this.algorithm);
    }


    public String extractUsername (String token) {
        return extractClaims(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaims(token, Claims::getExpiration);
    }

    public <T> T extractClaims(String token, Function<Claims, T> claimsTFunction) {
        return claimsTFunction.apply(Jwts.parser().verifyWith(key).build().parseSignedClaims(token).getPayload());
    }

    public boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public boolean isTokenValid (String token, UserDetails userDetails) {
        String username = extractUsername(token);
        boolean usernameMatch = username.equals(userDetails.getUsername()) ? true : false;
        boolean tokenExpired = isTokenExpired(token);
        return usernameMatch && !tokenExpired;
    }

    public String generateToken (UserDetails userDetails) {
        return Jwts.builder()
                .subject(userDetails.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + expiration_time))
                .signWith(key)
                .compact();
    }

}
