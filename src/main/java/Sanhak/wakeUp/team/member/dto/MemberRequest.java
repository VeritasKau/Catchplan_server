
package Sanhak.wakeUp.team.member.dto;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MemberRequest {
    @NotNull
    @Size(min = 1, max = 50)
    private String uniqueUserInfo;

}
