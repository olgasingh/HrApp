package hr.models;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.ManyToMany;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.CascadeType;




@Entity
@Table(name="tuser")
public class UserEntity extends BaseEntity{	

	
	
	private String email;

	@Column(name="email", nullable = true)
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}


	private String firstName;

	@Column(name="firstName", nullable = true)
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	private String lastName;

	@Column(name="lastName", nullable = true)
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	private String password;

	@Column(name="password", nullable = true)
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
		
	private Set<RoleEntity> roles;

	@ManyToMany(cascade=CascadeType.ALL)
	@JoinTable(name = "TUserRole", joinColumns = {	@JoinColumn(name="UserID")	},	inverseJoinColumns = {	@JoinColumn(name="RoleID") } )
	public Set<RoleEntity> getRoles() {
		return roles;
	}
	
	public void setRoles(Set<RoleEntity> roles) {
		this.roles = roles;
	}
	
}
