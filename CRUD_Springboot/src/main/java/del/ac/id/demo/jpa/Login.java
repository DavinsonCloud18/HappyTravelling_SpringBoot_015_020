package del.ac.id.demo.jpa;

import java.sql.Timestamp;
//import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "t_login")
public class Login {
	@Id
	@Column(name="username")
	private String username;
	@Column(name="roleid")
	private int roleid;
	@Column(name="lastlogin" )
	private Timestamp date;
	@Column(name="isactive")
	private int isactive;
	
	public Login() {}
	public Login(String username, int roleid,Timestamp date, int isactive) {
		this.username = username;
		this.roleid = roleid;
		this.date = date;
		this.isactive = isactive;
	}
	
	public void setUsername(String username){
		this.username = username;
	}
	public void setRoleId(int roleid) {
		this.roleid = roleid;
	}
	public void setDate(Timestamp date) {
		this.date = date;
	}
	public void setIsactive(int isactive) {
		this.isactive = isactive;
	}
	
	public String getUsername() {
		return this.username;
	}
	public int getRoleid() {
		return this.roleid;
	}
	public Date getDate() {
		return this.date;
	}
	public int getIsactive() {
		return this.isactive;
	}
}