package Sanhak.wakeUp.team.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponse {
    private Long id;
    private String name;
    private String email;
    private Integer age;

    public static UserResponse of(Long id, String name, String email, Integer age) {
        return UserResponse.builder()
                .id(id)
                .name(name)
                .email(email)
                .age(age)
                .build();
    }
    //UserResponse user = UserResponse.of(1L, "John", "john@example.com", 30);
}
