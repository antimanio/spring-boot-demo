package spring.demo.login.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.demo.login.payload.response.AccessTokenResponse;
import spring.demo.login.payload.response.JWTResponse;
import spring.demo.login.payload.request.RefreshTokenRequest;
import spring.demo.login.model.RefreshToken;
import spring.demo.login.model.User;
import spring.demo.login.repository.RefreshTokenRepository;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@Service
public class RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;

    private final JWTService jwtService;

    @Autowired
    public RefreshTokenService(RefreshTokenRepository refreshTokenRepository, JWTService jwtService){
        this.refreshTokenRepository = refreshTokenRepository;
        this.jwtService = jwtService;
    }


    public RefreshToken createRefreshToken(User user){
            RefreshToken refreshToken = new RefreshToken();
            refreshToken.setUser(user);
            refreshToken.setToken(UUID.randomUUID().toString());
            refreshToken.setExpirydate(LocalDate.now().plusDays(1));
            return refreshTokenRepository.save(refreshToken);
    }

    public Optional<RefreshToken> findByToken(String token){
        return refreshTokenRepository.findByToken(token);
    }

    public boolean verifyExpiration(RefreshToken token){
        if(token.getExpirydate().isBefore(LocalDate.now())){
            refreshTokenRepository.delete(token);
            return false;
        }
        return true;
    }


    public JWTResponse validateRefreshToken(RefreshTokenRequest request) {
        JWTResponse response = new JWTResponse();
        Optional<RefreshToken> token = findByToken(request.getToken());
        boolean validRefreshToken = verifyExpiration(token.get());

        if (token.isPresent() && validRefreshToken) {
            User user = token.get().getUser();
            String jwt = jwtService.generateToken(user);

            AccessTokenResponse accessTokenResponse = new AccessTokenResponse();
            accessTokenResponse.setAccessToken(jwt);
            accessTokenResponse.setExpirationTime("10 min");
            response.setAccessTokenResponse(accessTokenResponse);
            response.setRefreshToken(token.get());
            response.setMessage("Successfully RefreshToken");

        } else {
            response.setError("Failed to validate refresh token...");
        }
        return response;
    }


}

