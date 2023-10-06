package Sanhak.wakeUp.team.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
//import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

@Data
public class UserRequest {
//    @Schema(description = "User 이름")
    private String name;
    private String email;
    private Integer age;
    private String sex;
    private String phone;
    private String mbti;


}
