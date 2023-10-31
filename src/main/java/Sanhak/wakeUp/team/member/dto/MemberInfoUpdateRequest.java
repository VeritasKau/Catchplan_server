package Sanhak.wakeUp.team.member.dto;


import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MemberInfoUpdateRequest {

    @NotBlank(message = "이름은 필수입니다.")
    private String name;    // 이름

    @NotBlank(message = "성별은 필수입니다.")
    private String sex;     // 성별

    @NotBlank(message = "장르1은 필수입니다.")
    private String genre1;   // 장르
    private String genre2;   // 장르
    private String genre3;   // 장르

    @NotBlank(message = "MBTI는 필수입니다.")
    private String mbti;    // MBTI

}
