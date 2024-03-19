package rest_accreditation.config;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.SignatureException;

public class JwtTokenFilter extends OncePerRequestFilter {

    
    private String secretKey="345762348562347562465gff78666g8h6g8686gh86hgdf6fg7590099g9sdfg0dfsg99fg"; // Clave secreta del token JWT

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = extractToken(request); // Método para extraer el token del encabezado de autorización
        
        if (token != null) {
            
            // Validar el token y establecer la autenticación en el contexto de seguridad
            if (validateToken(token)) {
                
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(null, null);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        
        filterChain.doFilter(request, response);
    }

    private String extractToken(HttpServletRequest request) {
        // Extraer el token del encabezado de autorización

        

        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            
            return bearerToken.substring(7);
        }
        return null;
    }

    private boolean validateToken(String token) {
        // Validar el token JWT
        try {
            
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
          
            return true;
        } catch (ExpiredJwtException e) {
            System.out.println("El token ha expirado.");
        } catch (UnsupportedJwtException e) {
            System.out.println("Token JWT no soportado.");
        } catch (MalformedJwtException e) {
            System.out.println("Token JWT malformado.");
        } catch (SignatureException e) {
            System.out.println("Error en la firma del token JWT.");
        } catch (IllegalArgumentException e) {
            System.out.println("Clave secreta inválida.");
        } catch (Exception e) {
            System.out.println("Ocurrió un error al validar el token JWT.");
        }
        
        return false;
    }
}