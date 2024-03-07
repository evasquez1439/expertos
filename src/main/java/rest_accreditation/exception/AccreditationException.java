package rest_accreditation.exception;

/**
 * Prop&oacute;sito: esta clase maneja excepciones personalizadas para la aplicaci&oacute;n
 * @author:Ingeneo S.A.S
 * @version: 1.0
 * @since: 31/10/2014
 */
public class AccreditationException extends RuntimeException  {


	private static final long serialVersionUID = 1L;
	
	 public AccreditationException(String message){
		 super(message);
	 }

}
