package spring.demo.login.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import spring.demo.login.dto.JWTRequest;
import spring.demo.login.dto.JWTResponse;
import spring.demo.login.model.User;
import spring.demo.login.repository.UserRepository;

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


    public JWTResponse signIn(JWTRequest request) {
        JWTResponse response = new JWTResponse();
        try {
          authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
          User user = userRepository.findByEmail(request.getEmail()).orElseThrow(() -> new UsernameNotFoundException("Username not Found"));
          String jwt = jwtUtils.generateToken(user);
          response.setAccessToken(jwt);
          response.setExpirationTime("10 min");
          response.setMessage("Successfully Signed In");
          response.setUsers(user);

        } catch (Exception e) {
            response.setError(e.getMessage());
        }
        return response;
    }

}


