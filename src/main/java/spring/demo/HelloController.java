package spring.demo.login;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    
@RequestMapping("/hello")
public String hello(){
    return "HEllo Tonight";
}

}
