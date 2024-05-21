package spring.demo.hello;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/helloworld")
public class HelloEndpoint {

    @GetMapping
    public ResponseEntity helloWorld(){
        return ResponseEntity.ok().body("Hello World");
    }

}
