package hr.models;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="trole")
public class RoleEntity extends BaseEntity{	
	private String description;

	@Column(name="description", nullable = true)
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	private Set<UserEntity> users;

	@ManyToMany(cascade=CascadeType.ALL)
	@JoinTable(name = "TUserRole",	joinColumns = {	@JoinColumn(name="RoleID")	},	inverseJoinColumns = {	@JoinColumn(name="UserID")	})
	public Set<UserEntity> getUsers() {
		return users;
	}
	
	public void setUsers(Set<UserEntity> users) {
		this.users = users;
	}
}
