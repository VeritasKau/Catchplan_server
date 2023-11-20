package Sanhak.wakeUp.team.small_event.entity;


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
public class SmallEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "small_event_id")
    private Long id;
    private String image;
    private String text;
    private String place;
    private String duration;
    private String url;
    private String detail;
    private String detail2;
    private Integer status;

    public SmallEvent(String image, String text, String place, String duration, String url, String detail, String detail2) {
        this.image=image;
        this.text=text;
        this.place=place;
        this.duration=duration;
        this.url=url;
        this.detail=detail;
        this.detail2=detail2;
        this.status=1;
    }
}
