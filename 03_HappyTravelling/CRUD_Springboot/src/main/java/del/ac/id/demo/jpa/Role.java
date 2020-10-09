package del.ac.id.demo.jpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="t_role")
public class Role {
	@Id
	@Column(name="roleid")
	private int roleid;
	@Column(name="roledesc")
	private String roledesc;
	
	protected Role() {}
	public Role(int roleid,String roledesc) {
		this.roledesc = roledesc;
		this.roleid = roleid;
	}
	public void setRoleid(int roleid) {
		this.roleid = roleid;
	}
	public void setRoledesc(String roledesc) {
		this.roledesc = roledesc;
	}
	
	public int getRoleid() {
		return this.roleid;
	}
	public String getRoledesc() {
		return this.roledesc;
	}
}
