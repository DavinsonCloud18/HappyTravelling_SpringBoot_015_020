package del.ac.id.demo.jpa;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="t_jadwal_penerbangan")
public class Penerbangan {
	@Id
	@Column(name = "no_penerbangan")
	private int penerbanganid;
	@Column(name = "no_maskapai")
	private int maskapai;
	@Column(name = "asal")
	private String asal;
	@Column(name = "tujuan")
	private String tujuan;
	@Column(name = "berangkat")
	private Timestamp berangkat;
	@Column(name = "sampai")
	private Timestamp sampai;
	@Column(name = "harga")
	private int harga;
	
	
	public int getPenerbanganid() {
		return this.penerbanganid;
	}
	
	public int getMaskapai() {
		return maskapai;
	}

	public void setMaskapai(int maskapai) {
		this.maskapai = maskapai;
	}

	public String getAsal() {
		return asal;
	}
	public void setAsal(String asal) {
		this.asal = asal;
	}
	public Timestamp getBerangkat() {
		return this.berangkat;
	}
	
	public void setPenerbanganid(int penerbanganid){
		this.penerbanganid = penerbanganid;
	}
	public void setBerangkat(Timestamp berangkat) {
		this.berangkat = berangkat;
	}
	
	public Timestamp getSampai() {
		return this.sampai;
	}
	public void setSampai(Timestamp kembali) {
		this.sampai = kembali;
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