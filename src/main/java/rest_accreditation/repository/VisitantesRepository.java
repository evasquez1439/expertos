/**
 * 
 */
package rest_accreditation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import rest_accreditation.domain.PatientSurgery;
import rest_accreditation.repository.impl.VisitantesRepositoryCustom;

/**
 * @author 1103099800
 *
 */

@RepositoryRestResource
public interface VisitantesRepository  extends JpaRepository<PatientSurgery, Long> , VisitantesRepositoryCustom{

}
