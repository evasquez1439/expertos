package rest_accreditation.config;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.util.concurrent.TimeUnit;

import javax.persistence.EntityManagerFactory;

import org.apache.commons.dbcp2.BasicDataSource;
import org.jasypt.util.text.BasicTextEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.time.Duration;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Prop&oacute;sito: Esta clase es la principal para el web service de
 * acreditaci&oacute;n determina el punto de arranque de cualquier aplicacion de
 * spring boot, se genera por defecto y no debe ser modificada<br>
 * <b>Anotaciones:</b> <br>
 * <b>{@literal @}EntityScan:</b> Busca la ubicaci&oacute;n de las entidades de
 * JPA <br>
 * <b>{@literal @}ComponentScan:</b> Busca los paquetes de configuraci&oacute;n
 * o componentes del sistema <br>
 * <b>{@literal @}EnableJpaRepositories:</b> busca en la ubicaci&oacute;n
 * indicada por repositorios de jpa(Base de datos) en el paquete indicado <br>
 * 
 * @author:Jackson Palacios
 * @version: 1.0
 * @since: 31/10/2014
 */
@EnableJpaRepositories(basePackages = { "rest_accreditation.repository" })
// Ldap y jpa deben estar en rutas diferentes
@EntityScan(basePackages = { "rest_accreditation.domain" })
@ComponentScan(basePackages = { "rest_accreditation.web",
		"rest_accreditation.config" })
@EnableAutoConfiguration

@SpringBootApplication
@EnableTransactionManagement
public class RestAccreditationApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestAccreditationApplication.class, args);
		RuntimeMXBean runtimeBean = ManagementFactory.getRuntimeMXBean();
		String jvmName = runtimeBean.getName();
		System.out.println("JVM Name:" +jvmName);
		long pid = Long.valueOf(jvmName.split("@")[0]);
		System.out.println("JVM PID: "+pid);
	}

	@Autowired
	private Environment env;

	/**
	 * Funci&oacute;n que gerena la conexi&oacute;n a base de datos
	 * personalizada,
	 * 
	 * @return dataSource fuente de datos con la conexi&oacute;n a base de datos
	 *         los datos son extraidos de application.properties y est&aacute;n
	 *         encriptados si se desea cambiar la conexi&oacute;n a ldap o a
	 *         base de datos, se debe modificar ese archivo
	 * @see "application.properties"
	 */
	@Bean(destroyMethod = "close")
	public BasicDataSource restDataSource() {
		// Se obtienen variables del properties y se desencriptan
		BasicTextEncryptor textEncryptor = new BasicTextEncryptor();
		textEncryptor.setPassword(env.getProperty("spring.datasource.team"));
		String user = env.getProperty("spring.datasource.username");
		String password = env.getProperty("spring.datasource.password");
		String url = env.getProperty("spring.datasource.url");
		BasicDataSource dataSource = new BasicDataSource();

		dataSource.setDriverClassName(env.getProperty("spring.datasource.driverClassName"));
		dataSource.setUrl(textEncryptor.decrypt(url));
		dataSource.setUsername(textEncryptor.decrypt(user));
		dataSource.setPassword(textEncryptor.decrypt(password));

		System.out.println("usuario: "+textEncryptor.decrypt(user));
		System.out.println("passwd: "+textEncryptor.decrypt(password));
		System.out.println("url: "+textEncryptor.decrypt(url));



		return dataSource;
	}

	/**
	 * Funci&oacute;n para configuraciones adicionales para tomcat puerto, duraci&oacute;n de
	 * la sesi&oacute;n y ruta
	 * 
	 * @return factory
	 */
	@Bean
public ConfigurableServletWebServerFactory servletContainer() {
    TomcatServletWebServerFactory factory = new TomcatServletWebServerFactory();
    factory.setContextPath("/webServExpertos");
    factory.setPort(8383);
    return factory;
}
	
	/**
	 * Funci&oacute;n para registrar en un contexto Spring un EntityManager
	 * 
	 * @return LocalContainerEntityManagerFactoryBean
	 */	
	@Bean //Creating and registering in spring context an entityManager
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(restDataSource());
        em.setPackagesToScan(new String[]{"rest_accreditation.domain"}); // package where are the @Entity classes are located, ususaly your domain package
        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter(); // JPA implementation 
        em.setJpaVendorAdapter(vendorAdapter);


        return em;
    }
	
	/**
	 * Funci&oacute;n para configurar el manejo de las transacciones (TransactionManager)
	 * @param emf Entity Manager Factory
	 * @return PlatformTransactionManager
	 */		
	@Bean //Configuring the transactionManager
    public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(emf);
        return transactionManager;
    }

	/**
	 * Funci&oacute;n para el manejo de excepciones en el contexto de persistencia
	 * 
	 * @return PersistenceExceptionTranslationPostProcessor
	 */			
	@Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }	
}
