package spring.demo.login.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import spring.demo.login.payload.response.AccessTokenResponse;
import spring.demo.login.payload.request.JWTRequest;
import spring.demo.login.payload.response.JWTResponse;
import spring.demo.login.model.RefreshToken;
import spring.demo.login.model.User;
import spring.demo.login.repository.UserRepository;

@Service
public class AuthService {

    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JWTService jwtService;
    private final RefreshTokenService refreshTokenService;
    private final UserRepository userRepository;

    @Autowired
    public AuthService(AuthenticationManager authenticationManager,
                       PasswordEncoder passwordEncoder,
                       UserRepository userRepository,
                       JWTService jwtService,
                       RefreshTokenService refreshTokenService)
    {
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.refreshTokenService = refreshTokenService;
    }

    public JWTResponse register(JWTRequest request) {
        JWTResponse result = new JWTResponse();
        try {

            User user = new User();
            user.setName(request.getName());
            user.setEmail(request.getEmail());
            user.setPassword(passwordEncoder.encode(request.getPassword()));
            user.setRole(request.getRole());
            User userResult = userRepository.save(user);

            if(user != null) {
                result.setUsers(userResult);
                result.setMessage("Successfully registered");
            }

        } catch (Exception e) {
            result.setError(e.getMessage());
        }

        return result;
    }

    public JWTResponse login(JWTRequest request) {
        JWTResponse response = new JWTResponse();
        AccessTokenResponse accessTokenResponse = new AccessTokenResponse();
        try {
          authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
          User user = userRepository.findByEmail(request.getEmail()).orElseThrow(() -> new UsernameNotFoundException("Username not Found"));
          String token = jwtService.generateToken(user);

          accessTokenResponse.setAccessToken(token);
          accessTokenResponse.setExpirationTime("10 min");
          RefreshToken refreshToken = refreshTokenService.createRefreshToken(user);

          response.setAccessTokenResponse(accessTokenResponse);
          response.setRefreshToken(refreshToken);
          response.setMessage("Successfully Signed In");
          response.setUsers(user);

        } catch (Exception e) {
            response.setError(e.getMessage());
        }
        return response;
    }

}


