package hr.models;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.FetchType;




@Entity
@Table(name="TCounty")
public class CountyEntity extends BaseEntity{	

	
	
	private String code;

	@Column(name="code", nullable = true)
	public String getCode() {
		return code;
	}
	
	public void setCode(String code) {
		this.code = code;
	}


	private String description;

	@Column(name="description", nullable = true)
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	private StateProvinceEntity stateProvince;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "StateProvinceID", nullable = false)
	public StateProvinceEntity getStateProvince() {
		return stateProvince;
	}
	
	public void setStateProvince(StateProvinceEntity stateProvince) {
		this.stateProvince = stateProvince;
	}
	
}
