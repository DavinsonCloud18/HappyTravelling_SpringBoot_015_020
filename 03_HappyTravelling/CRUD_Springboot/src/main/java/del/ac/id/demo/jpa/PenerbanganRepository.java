package del.ac.id.demo.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PenerbanganRepository extends JpaRepository<Penerbangan, Integer>{
	List<Penerbangan> findAll();
}