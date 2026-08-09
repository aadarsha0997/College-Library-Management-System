package College.Library.Management.System.Project.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;

import java.util.Date;
import java.util.Map;
import java.util.function.Function;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;

@Service
public class JwtService {

    @Value("${jwt.secret}")
    private String secretkey;

    private SecretKey getSignKey(){
        byte[] keyBytes= Decoders.BASE64.decode(secretkey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String extractUsername(String token){
        return extractClaim(token,Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver){
        final Claims claims=extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public String generateToken(
            Map<String,Object> extraClaims,
            UserDetails userDetails){
        return Jwts
                .builder()
                .claims(extraClaims)
                .subject(userDetails.getUsername())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis()+1000*60*24))
                .signWith(getSignKey())
                .compact();
    }

    public  boolean isTokenValid(String token, UserDetails userDetails){
        final String username=extractUsername(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);

    }

    public boolean isTokenExpired(String token){
        return extractExpiration(token).before(new Date());
    }
    public Date extractExpiration(String token){
        return extractClaim(token,Claims::getExpiration);
    }




    private Claims extractAllClaims(String token){
        return Jwts.parser()
                .verifyWith(getSignKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
}
