package spring.demo.login.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import spring.demo.login.model.Product;
import spring.demo.login.repository.ProductRepository;

import java.util.List;

@RestController
public class AdminUserController {

    private final ProductRepository productRepository;

    @Autowired
    public AdminUserController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping("/api/products")
    public ResponseEntity<List<Product>> getAllProducts(){
        return ResponseEntity.ok(productRepository.findAll());
    }

    @PostMapping("/api/admin/product")
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        product.setName(product.getName());
        Product createdProduct = productRepository.save(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);
    }

    @GetMapping("/api/users/only")
    public ResponseEntity userAccessOnly(){
        return ResponseEntity.status(HttpStatus.OK).body("Only Users can access this API");
    }

    @GetMapping("/api/admin-and-user/both")
    public ResponseEntity adminAndUserAccess(){
        return ResponseEntity.status(HttpStatus.OK).body("Both Admin and Users can access this API");
    }


}
