package Sanhak.wakeUp.team.member.dto;


//import Sanhak.wakeUp.team.member.constraint.ValidMBTIValue;
//import Sanhak.wakeUp.team.member.constraint.ValidSexValue;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MemberInfoUpdateRequest {

    @NotBlank(message = "이름은 필수입니다.")
    private String name;    // 이름

    @NotBlank(message = "성별은 필수입니다.")
//    @ValidSexValue     //sex는 M,F중에 선택하세요.
    private String sex;     // 성별

    @NotBlank(message = "장르1은 필수입니다.")
    private String genre1;   // 장르

    private String genre2;   // 장르
    private String genre3;   // 장르

    @NotBlank(message = "MBTI는 필수입니다.")
//    @ValidMBTIValue   //mbti는 16개중에서만 선택하세요
    private String mbti;    // MBTI

}
