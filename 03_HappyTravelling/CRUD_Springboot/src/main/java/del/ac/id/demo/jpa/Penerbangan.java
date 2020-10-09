package del.ac.id.demo.jpa;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="t_penerbangan")
public class Penerbangan {
	@Id
	@Column(name = "asal")
	private String asal;
	@Column(name = "tujuan")
	private String tujuan;
	@Column(name = "tanggal_berangkat")
	private Date berangkat;
	@Column(name = "tanggal_kembali")
	private Date kembali;
	@Column(name = "harga")
	private int harga;
	
	public String getAsal() {
		return asal;
	}
	public void setAsal(String asal) {
		this.asal = asal;
	}
	
	public Date getBerangkat() {
		return this.berangkat;
	}
	public void setBerangkat(Date berangkat) {
		this.berangkat = berangkat;
	}
	
	public Date getKembali() {
		return this.kembali;
	}
	public void setKembali(Date kembali) {
		this.kembali = kembali;
	}
	
	public String getTujuan() {
		return tujuan;
	}
	public void setTujuan(String tujuan) {
		this.tujuan = tujuan;
	}
	public int getHarga() {
		return this.harga;
	}
	public void setHarga(int harga) {
		this.harga = harga;
	}
}