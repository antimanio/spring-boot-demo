package spring.demo.login.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.demo.login.model.Product;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    Optional<Product> findProductById(Integer id);

}
