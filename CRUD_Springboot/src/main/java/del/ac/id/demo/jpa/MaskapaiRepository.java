package del.ac.id.demo.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface MaskapaiRepository extends CrudRepository<Maskapai, Integer> {	
	List<Maskapai> findAll();
	
}


