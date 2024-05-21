package spring.demo.login.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.demo.login.model.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {}
