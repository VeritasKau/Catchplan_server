package Sanhak.wakeUp.team.entity;

import Sanhak.wakeUp.global.entity.BaseTimeEntity;
import Sanhak.wakeUp.team.entity.event.*;
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
    @JoinColumn(name = "camping_id")
    private Camping camping;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "classic_id")
//    private Classic classic;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "concert_id")
//    private Concert concert;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "drama_id")
//    private Drama drama;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "event_id")
//    private Event event;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "Kids_id")
//    private Kids kids;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "korea_id")
//    private Korea korea;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "musical_id")
//    private Musical musical;


}

