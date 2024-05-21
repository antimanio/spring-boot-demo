package spring.demo.login.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import spring.demo.login.model.User;


@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class JWTResponse {

    private String message;
    private String error;
    private String accessToken;
    private String expirationTime;
    private User users;

}
