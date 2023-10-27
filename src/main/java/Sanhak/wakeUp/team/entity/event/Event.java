package Sanhak.wakeUp.team.entity.event;

import Sanhak.wakeUp.team.entity.Scrap;
import jakarta.persistence.*;
import lombok.Getter;


import java.util.ArrayList;
import java.util.List;


@Getter
@Entity
public class Event   {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "event_id")
    private Long id;
    private String image;
    private String text;
    private String where;
    private String duration;
    private String url;
    private String detail;
    private String detail2;
    @OneToMany(mappedBy = "event")
    private List<Scrap> scraps = new ArrayList<>();
}