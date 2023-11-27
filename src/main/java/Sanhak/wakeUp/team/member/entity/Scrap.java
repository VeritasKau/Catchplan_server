package Sanhak.wakeUp.team.member.entity;

import Sanhak.wakeUp.team.event.entity.Event;
import Sanhak.wakeUp.team.global.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Scrap extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "scrap_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id")
    private Event event;

    // scrapdb에 이벤트 및 멤버 정보를 저장할 추가 필드

    private String eventImage;
    private String eventText;
    private String eventPlace;
    private String eventDuration;
    private String eventDetail;
    private String eventUrl;
    private String eventDtype;

    private String memberUniqueUserInfo;
    private String memberGenre1;
    private String memberGenre2;
    private String memberGenre3;
    private String memberMbti;


}
