package Sanhak.wakeUp.team.member.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ScrapInfoResponse {
    private String uniqueUserInfo;
    private String genre1;
    private String genre2;
    private String genre3;
    private String mbti;

    private String dtype;
    private String text;
    private String place;
    private String duration;
    private String url;
    private String detail;
    private String detail2;
}
