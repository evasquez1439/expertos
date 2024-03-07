package rest_accreditation.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

/**
 * Prop&oacute;sito: Clase encargada de responder con SC_UNAUTHORIZED o forbidden, 
 * por defecto niega el acceso a todos los recursos protegidos indicados 
 * en OAuth2ServerConfiguration.java 
 * @author Jackson Palacios
 * @version: 1.0
 * @since: 31/10/2014
 * @see rest_accreditation.config.OAuth2ServerConfiguration.ResourceServerConfiguration#configure(org.springframework.security.config.annotation.web.builders.HttpSecurity)
 * {@link rest_accreditation.config.OAuth2ServerConfiguration.ResourceServerConfiguration#configure(org.springframework.security.config.annotation.web.builders.HttpSecurity)}
 */
@Component
public class EntryPointUnauthorizedHandler implements AuthenticationEntryPoint {

	/**
	 * Funci&oacute;n que responde forbidden cuando el usuario no se encuentra autenticado
	 */
	@Override
	public void commence(HttpServletRequest request,
			HttpServletResponse response, AuthenticationException authException)
			throws IOException, ServletException {
		 response.sendError(HttpServletResponse.SC_UNAUTHORIZED,"Acceso denegado");
		 response.addHeader("acceso", "denegado");
		
	}
	

}
