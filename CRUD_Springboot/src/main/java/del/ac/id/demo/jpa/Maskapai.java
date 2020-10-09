package del.ac.id.demo.jpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="t_maskapai")
public class Maskapai {
	@Id
	@Column(name = "no_maskapai")
	private int noMaskapai;
	@Column(name = "nama_maskapai")
	private String namaMaskapai;
	@Column(name = "lokasi_maskapai")
	private String lokasiMaskapai;
	
	public int getNoMaskapai() {
		return noMaskapai;
	}
	public void setNoMaskapai(int noMaskapai) {
		this.noMaskapai = noMaskapai;
	}
	public String getNamaMaskapai() {
		return namaMaskapai;
	}
	public void setNamaMaskapai(String namaMaskapai) {
		this.namaMaskapai = namaMaskapai;
	}
	public String getLokasiMaskapai() {
		return lokasiMaskapai;
	}
	public void setLokasiMaskapai(String lokasiMaskapai) {
		this.lokasiMaskapai = lokasiMaskapai;
	}
	
	
	

}
