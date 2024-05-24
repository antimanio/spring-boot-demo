package spring.demo.login.payload.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import spring.demo.login.model.RefreshToken;
import spring.demo.login.model.User;
import spring.demo.login.payload.response.AccessTokenResponse;


@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class JWTResponse {

    private AccessTokenResponse accessTokenResponse;
    private RefreshToken refreshToken;
    private String message;
    private String error;
    private User users;



}
