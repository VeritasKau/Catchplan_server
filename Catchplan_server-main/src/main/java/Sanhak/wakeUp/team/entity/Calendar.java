package Sanhak.wakeUp.team.entity;

import Sanhak.wakeUp.team.entity.event.Event;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor //모든 필드값을 파라미터로 받는 생성자를 생성
@NoArgsConstructor //파라미터가 없는 기본 생성자 생성
@Entity
@Table(name = "Calendar")

public class Calendar extends TimeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "calendar_id")
    private Long id;


    @Column(name = "content")
    private String content;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "scrap_id")
    private Scrap scrap;



//    @Column(name = "title")
//    private String title;
//
//
//    @Column(name = "picture")
//    private String picture;
//
//    @OneToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "event_id")
//    private Event event;
//
//
//    @OneToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "user_id")
//    private User user;
}
