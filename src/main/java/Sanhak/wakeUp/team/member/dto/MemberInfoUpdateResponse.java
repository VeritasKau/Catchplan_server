package Sanhak.wakeUp.team.member.dto;


import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberInfoUpdateResponse {

    private String name;    // 이름
    private String sex;     // 성별
    private String genre1;   // 장르
    private String genre2;   // 장르
    private String genre3;   // 장르

    private String mbti;    // MBTI
    private String transactionTime;
    private String status;
    private String description;
    private int statusCode;

}
