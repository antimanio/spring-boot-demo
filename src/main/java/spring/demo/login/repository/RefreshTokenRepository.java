package spring.demo.login.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.demo.login.model.RefreshToken;

import java.util.Optional;

public interface RefreshTokenRepository  extends JpaRepository<RefreshToken, Integer> {
    Optional<RefreshToken> findByToken(String token);
}
