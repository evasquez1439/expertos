package rest_accreditation.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import static org.springframework.security.config.Customizer.*;

import java.security.Key;

import javax.servlet.Filter;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    
    private final JwtTokenFilter JwtTokenFilter;
    private final AuthenticationProvider authProvider;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{


            
            
        return http
        .csrf(csrf ->csrf .disable())
        
        .authorizeHttpRequests(authRequest ->
        authRequest
        .antMatchers("/auth/token").permitAll()
        .antMatchers("/principal.html").permitAll()
        .anyRequest().authenticated()
        )
        .authenticationProvider(authProvider)
        .addFilterBefore(JwtTokenFilter, UsernamePasswordAuthenticationFilter.class)
        
               
        .build();
    }
}
