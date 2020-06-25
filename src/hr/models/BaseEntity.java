package hr.models;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class BaseEntity {
	
	private Integer iD;

	@Id
	@Column(name="ID", unique = true, nullable = true)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getID() {
		return iD;
	}

	public void setID(Integer iD) {
		this.iD = iD;
	}

}
