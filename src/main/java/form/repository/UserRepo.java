package form.repository;

import form.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepo extends JpaRepository<User, Long> {

	List<User> findByEmail(String email);

	@Query(value = "SELECT * FROM user WHERE staff.email= :email", nativeQuery = true)
	public List<User> myquery(String email);
}
