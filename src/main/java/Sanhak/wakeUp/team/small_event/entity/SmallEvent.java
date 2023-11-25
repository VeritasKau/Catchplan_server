package Sanhak.wakeUp.team.small_event.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

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
    private Long image;
    private String text;
    private String place;
    private Long duration;
    private String url;
    private Long detail;
    private String detail2;
    private Boolean status;


    public SmallEvent(Long image, String text, String place, Long duration, String url, Long detail, String detail2) {
        this.image=image;
        this.text=text;
        this.place=place;
        this.duration=duration;
        this.url=url;
        this.detail=detail;
        this.detail2=detail2;
        this.status=Boolean.TRUE;
    }
}
