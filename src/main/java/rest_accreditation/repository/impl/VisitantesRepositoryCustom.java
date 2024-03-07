/**
 * 
 */
package rest_accreditation.repository.impl;

import rest_accreditation.dto.VisitanteResponseDTO;
import rest_accreditation.exception.AccreditationException;

/**
 * @author 1103099800
 *
 */
public interface VisitantesRepositoryCustom {

	public VisitanteResponseDTO getDatosVisitantes(String doc, String nombres, String apellidos, String ubicacion)
			throws AccreditationException;

}
