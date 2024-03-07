package rest_accreditation.exception;

/**
 * Prop&oacute;sito: esta clase maneja excepciones personalizadas para la aplicaci&oacute;n
 * @author:Jackson Palacios
 * @version: 1.0
 * @since: 31/10/2014
 */
public class InvalidParameterException extends RuntimeException  {


	private static final long serialVersionUID = 1L;
	
	 public InvalidParameterException(String parameters){
		 super("Los parametros: "+parameters+" Parecen ser incorrectos");
	 }

}
