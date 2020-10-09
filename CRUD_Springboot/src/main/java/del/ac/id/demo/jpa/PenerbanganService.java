package del.ac.id.demo.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PenerbanganService {
	@Autowired
	PenerbanganRepository penerbanganRepository;
	
	//mengambil data berdasarkan id
	public Penerbangan getPenerbanganById(int idPenerbangan) {
		return penerbanganRepository.findById(idPenerbangan).get();
	}
	
	public void saveOrUpdate(Penerbangan penerbangan) {
		penerbanganRepository.save(penerbangan);
	}
	
	public void delete(int id) {
		penerbanganRepository.deleteById(id);
	}
	
	public void update(Penerbangan penerbangan, int idPenerbangan) {
		penerbanganRepository.save(penerbangan);
	}
}
