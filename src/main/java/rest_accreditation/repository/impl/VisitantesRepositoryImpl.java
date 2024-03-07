/**
 * 
 */
package rest_accreditation.repository.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;
import javax.transaction.Transactional;

import rest_accreditation.dto.VisitanteDTO;
import rest_accreditation.dto.VisitanteResponseDTO;
import rest_accreditation.exception.AccreditationException;

/**
 * @author 1103099800
 *
 */
public class VisitantesRepositoryImpl implements VisitantesRepositoryCustom{
	
	@PersistenceContext(type = PersistenceContextType.TRANSACTION)
	private EntityManager entityManager;

	@Override
	@Transactional
	public VisitanteResponseDTO getDatosVisitantes(String doc, String nombres, String apellidos, String ubicacion)
			throws AccreditationException {
		VisitanteResponseDTO response = null;
		List<VisitanteDTO> results = new ArrayList<VisitanteDTO>();
		StringBuilder sb = null; 
		Query q = null;
		String parm[] = null;
		String parm2[] = null;
		String parm3[] = null;
		
		try {
			sb = new StringBuilder();
			sb.append("SELECT AB.PACTID, AB.PACIDE, AB.PACNOM, AB.PACNO2, AB.PACAP1,");
			sb.append("AB.PACAP2, UB.UBINOM, HA.EPIACTHAB, HA.EPIACTCO4, HA.EPIACTEPI ");
			sb.append("FROM HIEPIACT HA, ");
			sb.append("INUBI UB, ");
			sb.append("ABPAC AB ");
			sb.append("WHERE HA.EPIACTUBI = UB.UBICOD ");
			sb.append("AND AB.PACHIS = HA.EPIACTHIS ");
			
			if(doc!=null && !doc.isEmpty()){
				sb.append("AND AB.PACTID||AB.PACIDE like '%"+doc+"%' ");
			}
			
			if(nombres!=null && !nombres.isEmpty()){
				sb.append("AND (");
				parm = nombres.split(" ");
				for(int i=0; i < parm.length; i++){
					if(!(i == 0)){
						sb.append("OR ");
					}
					sb.append("AB.PACNOM like '%"+parm[i].toString()+"%' ");
					sb.append("OR AB.PACNO2 like '%"+parm[i].toString()+"%' ");
				}
				sb.append(")");
			}
			
			if(apellidos!=null && !apellidos.isEmpty()){
				sb.append("AND (");
				parm2 = apellidos.split(" ");
				for(int i=0; i < parm2.length; i++){
					if(!(i == 0)){
						sb.append("OR ");
					}
					sb.append("AB.PACAP1 like '%"+parm2[i].toString()+"%' ");
					sb.append("OR AB.PACAP2 like '%"+parm2[i].toString()+"%' ");
				}
				sb.append(")");
			}
			
			if(ubicacion!=null && !ubicacion.isEmpty()){
				sb.append("AND (");
				parm3 = ubicacion.split(" ");
				for(int i=0; i < parm3.length; i++){
					if(!(i == 0)){
						sb.append("AND ");
					}
					sb.append("UB.UBINOM like '%"+parm3[i].toString()+"%' ");
					sb.append("AND UB.UBINOM like '%"+parm3[i].toString()+"%' ");
				}
				sb.append(")");
			}
			
			q = entityManager.createNativeQuery(sb.toString());
			if (q!=null && q.getResultList()!=null){

				for (Object result : q.getResultList()){
					Object[] arrayData = (Object[])result;
					VisitanteDTO dto = new VisitanteDTO();
					dto.setTipoDoc(arrayData[0]!=null ? arrayData[0].toString():"");
					dto.setNumDoc(arrayData[1]!=null ? arrayData[1].toString():"");
					dto.setNombre1(arrayData[2]!=null ? arrayData[2].toString():"");
					dto.setNombre2(arrayData[3]!=null ? arrayData[3].toString():"");
					dto.setApellido1(arrayData[4]!=null ? arrayData[4].toString():"");
					dto.setApellido2(arrayData[5]!=null ? arrayData[5].toString():"");
					dto.setUbicacion(arrayData[6]!=null ? arrayData[6].toString():"");
					dto.setHabitacion(arrayData[7]!=null ? arrayData[7].toString():"");
					dto.setConfidencial(arrayData[8]!=null ? arrayData[8].toString():"");
					dto.setEpisodio(arrayData[9]!=null ? arrayData[9].toString():"");
					results.add(dto);
				}
				
				response = new VisitanteResponseDTO();
				if (results!=null && !results.isEmpty()){
					response.setCode("100");
					response.setDescription("OK");
					response.getResults().addAll(results);
				}else{
					response.setCode("98");
					response.setDescription("La consulta no arrojo resultados");
				}
				
			}
			
		} catch(NoResultException e){
			throw new AccreditationException("La Consulta no arrojo resultados");
		}catch (Exception e) {
			throw new AccreditationException("Ocurrio un error al realizar la consulta demografica del paciente:"+e.getMessage());
		}
		return response;
	}
	
}
