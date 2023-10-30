package Sanhak.wakeUp.team.dto;


import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UserInfoUpdateRequest {
    private Long memberId;  // 회원 ID (memberId)
    private String uniqueUserInfo;  // 고유 사용자 정보

    private String name;    // 이름
    private String sex;     // 성별
    private String genre1;   // 장르
    private String genre2;   // 장르
    private String genre3;   // 장르

    private String mbti;    // MBTI

}
