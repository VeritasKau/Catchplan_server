package Sanhak.wakeUp.team.event.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@ToString
@Getter
@Builder
@AllArgsConstructor
@Entity
@Setter
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "event_id")
    private Long id;
    private String image;
    @NotNull
    private String dtype;
    private String text;
    private String place;
    private String duration;
    private String url;
    private String detail;
    private String detail2;
    private Integer status;
    //@OneToMany(mappedBy = "event")
    //private List<Scrap> scraps = new ArrayList<>();
}
