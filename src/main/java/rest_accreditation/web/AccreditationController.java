package rest_accreditation.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import rest_accreditation.dto.VisitanteResponseDTO;
import rest_accreditation.exception.AccreditationException;
import rest_accreditation.repository.VisitantesRepository;





/**
 * Prop&oacute;sito: Esta clase es el controlador principal del web service de acreditaci&oacute;n,
 * recibe las peticiones que se generan en en lado del cliente e intercambia 
 * informaci&oacute;n con la base de datos a trav&eacute;s de las entidades y los
 * repositorios
 * <br>
 * <b>Anotaciones:</b>
 * <br>
 * <b>{@literal @}RestController:</b> Indica que se trada de un controlador restuful y que 
 * impl&iacute;citamente todos los controladores tienen de respues la anotaci&oacute;n
 * {@literal @}ResPonseBody
 * <br>
 * <b>{@literal @}Autowired:</b> Anotaci&oacute;n utilizada para inyectar las dependencias 
 * al momento del inicio del contenedor
 * <br>
 * <b>{@literal @}ResPonseBody:</b> Aunque no est&aacute; expl&iacute;cito en 
 * el controlador se a&ntilde;ade autom&aacute;ticamente y sirve para indicar que toda
 * respuesta ser&aacute; convertida a formato JSON para ser procesada por angularjs
 * <br>
 * <b>{@literal @}RequestMapping:</b> Anotaci&oacute;n que indica que las peticiones a cierta ruta
 * ser&aacute;n dirijidas a cierta funci&oacute;n del controlador 
 * <br>
 * Ejm: svr-moroni:83/<b>stickers/all</b> ser&aacute; dirigida a la funci&oacute;n <b>stickersCollection</b>
 * @author:Jackson Palacios
 * @version: 1.0
 * @since: 31/10/2014
 */
@RestController
public class AccreditationController {

	@Autowired
	private VisitantesRepository VisitantesRepository;
	
		
		/**
		 * Funci&oacute;n para extraer información para la app de visitantes 
		 * @param doc documento del paciente
		 * @param nombres nombres del paciente
		 * @param apellidos apellidos del paciente
		 * @return lista con la información de pacientes
		 */		
		@RequestMapping(value = "visitantes/getDatosVisitantes")
		public VisitanteResponseDTO getDatosVisitantes(@RequestParam(value = "doc") String doc, @RequestParam(value = "nombres") String nombres, @RequestParam(value = "apellidos") String apellidos, @RequestParam(value = "ubicacion") String ubicacion){
			VisitanteResponseDTO response = null;
			try{

				/*Se realiza validación de los campos de entrada*/
				if (rest_accreditation.util.Utils.isEmpty(doc) && rest_accreditation.util.Utils.isEmpty(nombres) && rest_accreditation.util.Utils.isEmpty(apellidos) && rest_accreditation.util.Utils.isEmpty(ubicacion)){
					throw new AccreditationException(rest_accreditation.util.Constants.PARAMETROS_VACIOS2);
				}
				response = this.VisitantesRepository.getDatosVisitantes(doc, nombres, apellidos, ubicacion);
			}catch(AccreditationException e){
				response = new VisitanteResponseDTO();
				response.setCode("99");
				response.setDescription("ERROR: "+e.getMessage());
			}
			
			return response;
		}
		
}
