package del.ac.id.demo.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LoginRepository extends JpaRepository<Login, String> {
	Login findByUsername(String username);
}
