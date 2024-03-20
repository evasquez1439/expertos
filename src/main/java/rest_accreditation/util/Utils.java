package rest_accreditation.util;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import rest_accreditation.exception.AccreditationException;

/**
 * Clase encargada de realizar diferentes operaciones de validaci&oacute;n sobre los datos de entrada de las diferentes operaciones de los servicios web
 * <br>
 * @author Ingeneo S.A.S
 */
public class Utils {

	private static SecureRandom random = new SecureRandom();
	/**
	 * Metodo encargado de generar el tocken de forma automática
	 * @return string con el tocken generado
	 */
	public static String genTocken(){
		return new BigInteger(40, random).toString(32);
	}
	
	/**
	 * Metodo encargado de validar si un campo es vacio o no
	 * @param val Campo a validar
	 * @return true si el campo es vacio
	 */
	public static Boolean isEmpty(String val){
		Boolean resultado = Boolean.FALSE;
		if (val==null || (val!=null && val.isEmpty())){
			resultado = Boolean.TRUE;
		}
		
		return resultado;
	}
	
	/**
	 * Metodo encargado de validar si un campo es numerico o no 
	 * @param val Campo a validar
	 * @return true si el campo si es numerico
	 */
	public static Boolean isNumeric(String val){
		try{
			Long.parseLong(val);
			return Boolean.TRUE;
		}catch(NumberFormatException e){
			return Boolean.FALSE;
		}
	}
	
	/**
	 * Metodo encargado de validar si un campo es numerico y menor o igual a 24 
	 * @param val Campo a validar
	 * @return true si el campo si es numerico
	 */
	public static Boolean isNumericMenorTo25(String val){
		Boolean resultado = Boolean.FALSE;
		try{
			if(Long.parseLong(val)<25){
				resultado = Boolean.TRUE;
			}
		}catch(NumberFormatException e){
			return resultado;
		}
		
		return resultado;
	}

	/**
	 * Metodo que valida si un campo es numerico y mayor a 0
	 * @param val Campo a validar
	 * @return true si el campo es numerico y mayor a 0
	 */
	public static Boolean isNumericMayorToZero(String val){
		Boolean resultado = Boolean.FALSE;
		try{
			if(Long.parseLong(val)>0){
				resultado = Boolean.TRUE;
			}
		}catch(NumberFormatException e){
			return resultado;
		}
		
		return resultado;
	}
	
	/**
	 * Metodo que se encarga de validar si la fecha inicial es mayor que la fecha final
	 * @param fechaIni Fecha inicial
	 * @param fechaFin Fecha Final
	 * @return true si la fecha inicial es mayor a la final
	 */
	public static Boolean fechaIniMayorFechaFin(String fechaIni, String fechaFin){
		Boolean resultado = Boolean.FALSE;
		SimpleDateFormat formato = new SimpleDateFormat("y/M/d");
		Date fecIni=null;
		Date fecFin=null;
		try{
			fecIni = formato.parse(fechaIni);
			fecFin = formato.parse(fechaFin);
			if (fecIni.after(fecFin)){
				resultado = Boolean.TRUE;
			}
		}catch (ParseException e){
			throw new AccreditationException(rest_accreditation.util.Constants.ERROR_FORMATO_FECHA+e.getMessage());
		}
		
		return resultado;
	}
	
	public static Boolean fechaIniMayorFechaFin2(String fechaIni, String fechaFin){
		Boolean resultado = Boolean.FALSE;
		SimpleDateFormat formato = new SimpleDateFormat("d/M/y");
		Date fecIni=null;
		Date fecFin=null;
		try{
			fecIni = formato.parse(fechaIni);
			fecFin = formato.parse(fechaFin);
			if (fecIni.after(fecFin)){
				resultado = Boolean.TRUE;
			}
		}catch (ParseException e){
			throw new AccreditationException(rest_accreditation.util.Constants.ERROR_FORMATO_FECHA+e.getMessage());
		}
		
		return resultado;
	}
	
	/**
	 * Metodo que valida si una fecha ingresada tiene el formato correcto
	 * @param fecha Fecha a validar
	 * @return true Si el formnato es correcto
	 */
	public static Boolean validarFormatoFecha(String fecha){
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		try{
			formato.parse(fecha);
			return Boolean.TRUE;
		}catch (ParseException e){
			return Boolean.FALSE;
		}
	}
	

	/**
	 * Metodo que valida si una fecha ingresada tiene el formato correcto
	 * @param fecha Fecha a validar
	 * @return true Si el formnato es correcto
	 */
	public static Boolean validarFormatoFecha2(String fecha){
		SimpleDateFormat formato = new SimpleDateFormat("yyyy/MM/dd");
		Boolean ret = false;
		try{
			Date fec = formato.parse(fecha);
			ret = Boolean.TRUE;
		}catch (ParseException e){
			ret =  Boolean.FALSE;
		}
		return ret;
	}
	
	/**
	 * Metodo que envía la fecha actual con un formato en específico
	 * @return fecha como String
	 */
	public static String getFechaInicial() {
	    //SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	    /*SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
	    ahora.setHours(0);
	    ahora.setMinutes(0);
	    ahora.setSeconds(0);
	    String fechaInicial = formateador.format(ahora);
	    return fechaInicial;*/
		
	    return "31/10/2011";
	}
	
	public static String getFechaFinal() {
	    /*Date ahora = new Date();
	    SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
	    ahora.setHours(23);
	    ahora.setMinutes(59);
	    ahora.setSeconds(0);
	    String fechaFinal = formateador.format(ahora);
	    return fechaFinal;*/
	    return "31/10/2011";
	}
	
	public static String formatFecha(String fecha) {
		
		SimpleDateFormat formato = new SimpleDateFormat("y/M/d");
		Date date=null;
		String res = null;
		try{
			date = formato.parse(fecha);
			formato = new SimpleDateFormat("d/M/y");
			res = formato.format(date);
			
		}catch (ParseException e){
			throw new AccreditationException(rest_accreditation.util.Constants.ERROR_FORMATO_FECHA+e.getMessage());
		}
		return res;
		
	}
	
	
}
