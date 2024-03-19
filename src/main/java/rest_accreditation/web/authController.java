package rest_accreditation.web;

import java.security.Key;
import java.util.Date;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;



@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class authController {

    @PostMapping("/token")
    public ResponseEntity<String> generateToken() {
        String token = generateJWT("cliente");
            // Devolver el token en la respuesta
            return ResponseEntity.ok(token);
    }

    private final static long ACCES_TIMER =3600;
    private static final String SECRET_KEY ="345762348562347562465gff78666g8h6g8686gh86hgdf6fg7590099g9sdfg0dfsg99fg";
        private String generateJWT(String username) {
        
        long expirationTime = ACCES_TIMER * 1000;
        Date expirationDate = new Date(System.currentTimeMillis()+expirationTime);

        return Jwts.builder()
            .setSubject(username)
            .setExpiration(expirationDate)
            .signWith(getKey(),SignatureAlgorithm.HS256)
            .compact();
    }
    
    private Key getKey(){
        byte[] keyBytes=Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
    

    
}