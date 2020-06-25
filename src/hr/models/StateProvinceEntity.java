package hr.models;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.OneToMany;
import javax.persistence.FetchType;

import org.hibernate.annotations.IndexColumn;




@Entity
@Table(name="TStateProvince")
public class StateProvinceEntity extends BaseEntity{	

	
	
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
			
	private Set<CountyEntity> counties;

	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name="StateProvinceID")

	public Set<CountyEntity> getCounties() {
		return counties;
	}
	
	public void setCounties(Set<CountyEntity> counties) {
		this.counties = counties;
	}
	
}
