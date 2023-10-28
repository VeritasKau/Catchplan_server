package Sanhak.wakeUp.team.dto;

import lombok.Data;
//import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

@Data
public class UserRequest {
    private String name;
    private String email;
    private String password;
    private Integer age;

}
