package del.ac.id.demo.jpa;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired; 
@Service
public class MaskapaiService {

	@Autowired
	MaskapaiRepository maskapaiRepository;
	
	//mengambil data berdasarkan id
	public Maskapai getMaskapaiById(int noMaskapai) {
		return maskapaiRepository.findById(noMaskapai).get();
	}
	
	public void saveOrUpdate(Maskapai maskapai) {
		maskapaiRepository.save(maskapai);
	}
	
	public void delete(int id) {
		maskapaiRepository.deleteById(id);
	}
	
	public void update(Maskapai maskapai, int noMaskapai) {
		maskapaiRepository.save(maskapai);
	}
	
}
