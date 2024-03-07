/**
 * 
 */
package rest_accreditation.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 1103099800
 *
 */
public class VisitanteResponseDTO {
	
	private static final long serialVersionUID = 1L;
	private String code;
	private String description;
	private List<VisitanteDTO> results = new ArrayList<VisitanteDTO>();
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public List<VisitanteDTO> getResults() {
		return results;
	}
	public void setResults(List<VisitanteDTO> results) {
		this.results = results;
	}

}
