package rest_accreditation.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;





import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{    
        return http
        .csrf(csrf ->csrf .disable())
        
        .authorizeHttpRequests(authRequest ->
        authRequest
        .antMatchers("/principal.html").permitAll() 
        .antMatchers("/auth/token").permitAll()
        .antMatchers("/js/**").permitAll()
        .antMatchers("/css/**").permitAll()      
        .anyRequest().authenticated()     
        )
        .addFilterBefore(new JwtTokenFilter(), UsernamePasswordAuthenticationFilter.class)  
        .build();

        
    



    }
}