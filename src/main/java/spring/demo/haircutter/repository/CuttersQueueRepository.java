package spring.demo.haircutter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.demo.haircutter.model.CuttersQueue;

public interface CuttersQueueRepository extends JpaRepository<CuttersQueue, Integer> {

}