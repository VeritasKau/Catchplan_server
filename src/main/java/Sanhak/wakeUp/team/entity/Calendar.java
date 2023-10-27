package Sanhak.wakeUp.team.entity;



import lombok.*;
import Sanhak.wakeUp.global.entity.BaseTimeEntity;
import jakarta.persistence.*;


@Builder
@Getter
@Setter
@AllArgsConstructor //모든 필드값을 파라미터로 받는 생성자를 생성
@NoArgsConstructor //파라미터가 없는 기본 생성자 생성
@Entity
public class Calendar extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "calendar_id")
    private Long id;

    private String memo;


    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "scrap_id")
    private Scrap scrap;




}

