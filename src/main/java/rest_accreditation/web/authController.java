package rest_accreditation.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import rest_accreditation.config.*;


@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationmanager;

    @Autowired
    private ProjectUserDetailsService projectUserDetailsService;

    @Autowired
    private JwtUtil jwtUtil;
    
    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<AuthenticationResponse> createToken(@RequestParam(value = "username") String username){

        try {
            
           // authenticationmanager.authenticate(new UsernamePasswordAuthenticationToken(username, password));         
            UserDetails userDetails = projectUserDetailsService.loadUserByUsername(username);           
            String jwt = jwtUtil.generateToken(userDetails);           
            return new ResponseEntity<AuthenticationResponse>(new AuthenticationResponse("200",jwt),HttpStatus.OK);
        } catch (BadCredentialsException e) {           
            return new ResponseEntity<AuthenticationResponse>(new AuthenticationResponse("500","Error"),HttpStatus.BAD_GATEWAY);
        }

    }




}