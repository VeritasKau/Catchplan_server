package Sanhak.wakeUp.team.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Comment")
public class Comment  extends TimeEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id;

    //여러개의 이벤트가 사용자 한명에게 사용될수 있으므로 @ManyToOne 어노테이션을 추가한다.
    //단방향은 한 쪽의 엔티티가 상대 엔티티를 참조하고 있는 상태입니다.
    //@JoinColumn 어노테이션은 외래 키를 매핑할 때 사용합니다.
    //name 속성에는 매핑 할 외래 키 이름을 지정
    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


}
