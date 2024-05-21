package spring.demo.login.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
    public ResponseEntity<JWTResponse> register(@RequestBody JWTResponse response) {
        System.out.print("HELLO....");
        return ResponseEntity.ok(authService.register(response));
    }

    @PostMapping("/signin")
    public ResponseEntity<JWTResponse> signIn(@RequestBody JWTResponse response) {
        return ResponseEntity.ok(authService.signIn(response));
    }

    @PostMapping("/refresh")
    public ResponseEntity<JWTResponse> refreshToken(@RequestBody JWTResponse response) {
        return ResponseEntity.ok(authService.refreshToken(response));
    }


}
