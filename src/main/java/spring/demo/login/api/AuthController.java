package spring.demo.login.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.demo.login.payload.request.JWTRequest;
import spring.demo.login.payload.response.JWTResponse;
import spring.demo.login.payload.request.RefreshTokenRequest;
import spring.demo.login.service.AuthService;
import spring.demo.login.service.RefreshTokenService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;
    private final RefreshTokenService refreshTokenService;

    @Autowired
    public AuthController(AuthService authService, RefreshTokenService refreshTokenService){
        this.authService = authService;
        this.refreshTokenService = refreshTokenService;
    }

    @PostMapping("/register")
    public ResponseEntity<JWTResponse> register(@RequestBody JWTRequest request) {
        JWTResponse response = authService.register(request);
        if(response.getError() != null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<JWTResponse> login(@RequestBody JWTRequest request) {
        JWTResponse response = authService.login(request);
        if(response.getError() != null){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
        }
        return ResponseEntity.ok(response);
    }


    @PostMapping("/refreshtoken")
    public ResponseEntity<JWTResponse> refreshToken (@RequestBody RefreshTokenRequest request) {
        JWTResponse response = refreshTokenService.validateRefreshToken(request);
        if(response.getError() != null){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
        }
        return ResponseEntity.ok(response);
    }

}
