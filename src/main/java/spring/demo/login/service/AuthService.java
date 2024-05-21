package spring.demo.login.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import spring.demo.login.dto.JWTResponse;
import spring.demo.login.model.User;
import spring.demo.login.repository.UserRepository;

import java.util.HashMap;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final JWTUtils jwtUtils;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;


    @Autowired
    public AuthService(UserRepository userRepository, JWTUtils jwtUtils, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.jwtUtils = jwtUtils;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }

    public JWTResponse register(JWTResponse response) {
        JWTResponse result = new JWTResponse();
        try {

            User user = new User();
            user.setEmail(response.getEmail());
            user.setPassword(passwordEncoder.encode(response.getPassword()));
            user.setRole(response.getRole());
            User userResult = userRepository.save(user);

            if(user != null) {
                result.setUsers(userResult);
                result.setMessage("User Saved Successfully");
                result.setStatusCode(200);
            }

        } catch (Exception e) {
            result.setStatusCode(500);
            result.setError(e.getMessage());
        }

        return result;
    }


    public JWTResponse signIn(JWTResponse response) {
        JWTResponse result = new JWTResponse();
        try {

          authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(response.getEmail(), response.getPassword()));
          User user = userRepository.findByEmail(response.getEmail()).orElseThrow(() -> new UsernameNotFoundException("User not Found"));
          String jwt = jwtUtils.generateToken(user);
          String refreshToken = jwtUtils.generateRefreshToken(user,new HashMap<>());
          result.setStatusCode(200);
          result.setAccessToken(jwt);
          result.setRefreshToken(refreshToken);
          result.setExpirationTime("1Hr");
          result.setMessage("Successfully Signed In");

        } catch (Exception e) {
            result.setStatusCode(500);
            result.setError(e.getMessage());
        }

        return result;
    }

    public JWTResponse refreshToken(JWTResponse response) {
        JWTResponse result = new JWTResponse();
        String email = jwtUtils.extractUsername(response.getAccessToken());
        User user = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User not Found"));
        if(jwtUtils.isTokenValid(response.getAccessToken(), user)) {
            String jwt = jwtUtils.generateToken(user);
            result.setStatusCode(200);
            result.setAccessToken(jwt);
            result.setRefreshToken(result.getRefreshToken());
            result.setExpirationTime("1Hr");
            result.setMessage("Successfully Refreshed Token");
        }
        response.setStatusCode(500);
        return result;
    }

}
