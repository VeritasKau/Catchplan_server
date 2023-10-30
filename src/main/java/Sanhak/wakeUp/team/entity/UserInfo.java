package Sanhak.wakeUp.team.entity;

import Sanhak.wakeUp.global.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Table(name = "UserInfo")
@Builder
@AllArgsConstructor
public class UserInfo extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;
    private String name;
    private String sex;
    private String genre;
    private String mbti;
    @Column(name = "unique_user_info")
    private String uniqueUserInfo;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;
    public UserInfo() {
    }

}
