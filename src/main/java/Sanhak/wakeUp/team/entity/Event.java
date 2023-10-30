package Sanhak.wakeUp.team.entity;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;


@Getter
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "event_id")
    private Long id;
    private String image;
    private String text;
    private String place;
    private String duration;
    private String url;
    private String detail;
    private String detail2;
    private String status;
    @OneToMany(mappedBy = "event")
    private List<Scrap> scraps = new ArrayList<>();
}
