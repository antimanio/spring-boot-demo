package spring.demo.login.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.demo.login.dto.JWTRequest;
import spring.demo.login.dto.JWTResponse;
import spring.demo.login.service.AuthService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService){
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<JWTResponse> register(@RequestBody JWTRequest request) {
        JWTResponse response = authService.register(request);
        if(response.getError() != null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
        return ResponseEntity.ok(response);
    }

    @PostMapping("/signin")
    public ResponseEntity<JWTResponse> signIn(@RequestBody JWTRequest request) {
        JWTResponse response = authService.signIn(request);
        if(response.getError() != null){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
        }
        return ResponseEntity.ok(response);
    }

}
