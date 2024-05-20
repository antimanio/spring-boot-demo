package spring.demo.login.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.demo.login.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByEmail(String email);

}
