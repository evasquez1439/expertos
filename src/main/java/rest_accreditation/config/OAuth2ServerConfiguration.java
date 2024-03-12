

package rest_accreditation.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

/**
 * Prop&oacute;sito: esta clase maneja la autenticaci&oacute;n y la autorizaci&oacute;n 
 * de acceso a los recursos, en esta clase est&aacute;n combinados tanto el servidor 
 * de recursos como el servidor de autorizaci&oacute;n
 * Tipo de autenticaci&oacute;n: 2 LEGGED OAUTH2 la autenticaci&oacute;n se hace con el cliente sin
 * un usuario, es Igual a una autenticaci&oacute;n b&aacute;sica, solo que la comunicaci&oacute;n es con tokens
 * y codificada en base 64 por jwt
 * <b>Anotaciones:</b>
 * <br>
 * <b>{@literal @}Configuration:</b> Indica que se trata de una clase de configuraci&oacute;n
 * que debe cargar al momento de iniciar el servlet container
 * <br>
 * <b>{@literal @}EnableAuthorizationServer:</b> Anotaci&oacute;n para indicar cual es el servidor
 * de autorizaci&oacute;n, el encargado de manejar los request de las aplicaciones clientes
 * <b>{@literal @}EnableResourceServer:</b>Anotaci&oacute;n para indicar el servidor de recursos 
 * al cual consultaran los clientes por la informaci&oacute;n
 * <br>
 * @author:Jackson Palacios
 * @version: 1.0
 * @since: 31/10/2014
 */
@Configuration
public class OAuth2ServerConfiguration {

	private static final String RESOURCE_ID = "restaccreditation";
	/**
	 * Servidor de recursos, es el encargado de controlar el 
	 * acceso a la informaci&oacute;n por parte de los clientes
	 * @author 71268359
	 *
	 */
	@Configuration
	@EnableResourceServer
	protected static class ResourceServerConfiguration extends
			ResourceServerConfigurerAdapter {
		/**Funci&oacute;n de configuraci&oacute;n que indica el id del recurso al que se
		 * puede acceder en el servidor
		 * @param resources
		 */
		@Override
		public void configure(ResourceServerSecurityConfigurer resources) {
			// @formatter:off
			resources
				.resourceId(RESOURCE_ID);
			// @formatter:on
		}

		/**
		 * Funci&oacute;n de configuraci&oacute;n que captura los request desde la aplicaci&oacute;n del cliente 
		 * e indica exactamente cuales son los recursos
		 * a los que  puede acceder dependiendo del perf&iacute;l
		 * @param http, captura los request desde la aplica
		 */
		@Override
		public void configure(HttpSecurity http) throws Exception {
			// @formatter:off
			http
			.csrf().disable()
				//.authorizeRequests()				
			  //.authorizeRequests().antMatchers("/cirugia.html").permitAll()
			  .authorizeRequests().antMatchers("/principal.html").permitAll()
			 // .anyRequest().authenticated();
			  .anyRequest().hasAnyRole("TRUSTED_CLIENT");//Se le quita el prefijo ROLE_
//				.authorizeRequests()
          //    .anyRequest().hasRole("USER")
            //    .and().antMatcher("/accreditation/cirugia.html");
					//.antMatchers("/users").hasRole("ADMIN")
					//.antMatchers("/surgery/**","/surgery").hasRole("USER");
			// @formatter:on
		}

	}
	/**
	 * Servicio de autorizaci&oacute;n, encargado de validar las credenciales 
	 * de cliente.
	 * @author 71268359
	 *
	 */
	@Configuration
	@EnableAuthorizationServer
	protected static class AuthorizationServerConfiguration extends
			AuthorizationServerConfigurerAdapter {

		private TokenStore tokenStore = new InMemoryTokenStore();

		@Autowired
		@Qualifier("authenticationManagerBean")
		private AuthenticationManager authenticationManager;
		
		@Autowired
		@Qualifier("restDataSource")
		private BasicDataSource dataSource;

		@Override
		public void configure(AuthorizationServerEndpointsConfigurer endpoints)
				throws Exception {
			// @formatter:off
			endpoints
				.tokenStore(this.tokenStore)
				.authenticationManager(this.authenticationManager)
				.accessTokenConverter(accessTokenConverter())//Agregar Soporte para Json Web tokens
				.approvalStoreDisabled();//Indica que esta autenticación NO fue aprobada por el usuario
			//Es obligatoria para que funcione la comunicación con el server
			// @formatter:on
		}

		/**
		 * Funci&oacute;n de configuraci&oacute;n de fuente de datos de clientes
		 * se configura de donde se obtiene la informaci&oacute;n de los clientes
		 * autorizados
		 * @param clients Servicio de detalles de cliente
		 */
		@Override
		public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
			// @formatter:off
			clients.jdbc(dataSource);
			//	clients.inMemory()//Se debe obtener desde una base de datos
//			Se trasladan todos los datos a la tabla oauth_client_details
//					.withClient("clientapp")
//						.secret("123456")
//						.scopes("read")
//						.authorizedGrantTypes("client_credentials")
//						.authorities("ROLE_TRUSTED_CLIENT")
//						.resourceIds(RESOURCE_ID);
//						
						//.accessTokenValiditySeconds(3600);
			// @formatter:on
		}
		/**
		 * Configuraci&oacute;n del tipo de roles admitidos para el cliente
		 */
		@Override
		public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
			oauthServer.tokenKeyAccess("isAnonymous() || hasAuthority('ROLE_TRUSTED_CLIENT')").checkTokenAccess(
					"hasAuthority('ROLE_TRUSTED_CLIENT')");
		}

		/**
		 * Tienda de tokens encargada de generar autom&aacute;ticamente los 
		 * tokens de acceso para el cliente
		 * @return
		 */
		@Bean
		@Primary
		public DefaultTokenServices tokenServices() {
			DefaultTokenServices tokenServices = new DefaultTokenServices();
			tokenServices.setSupportRefreshToken(true);
			tokenServices.setTokenStore(this.tokenStore);
			return tokenServices;
		}
		@Bean
		JwtAccessTokenConverter accessTokenConverter(){
			return new JwtAccessTokenConverter();
		}
		

	}

}
