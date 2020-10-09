package del.ac.id.demo.jpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "t_user")
public class User {
	@Id
	@Column(name="username")
	private String username;
	@Column(name="pwd")
	private String pwd;
	@Column(name="roleid")
	private int roleid;
	
	public User() {}
	public User(String username, String pwd, int roleid) {
		this.username = username;
		this.pwd = pwd;
		this.roleid = roleid;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public void setRoleid(int roleid) {
		this.roleid = roleid;
	}
	
	public String getUsername() {
		return this.username;
	}
	public String getPwd() {
		return this.pwd;
	}
	public int getRoleid() {
		return this.roleid;
	}
}
