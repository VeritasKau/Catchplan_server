package Sanhak.wakeUp.team.entity;


import Sanhak.wakeUp.global.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
public class Member extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(unique = true, length = 50, nullable = false)
    private String uniqueUserInfo;

    private String name;
    private String sex;
    private String genre;
    private String mbti;
    private String token;
    public Member() {
        // 필요하다면 기본 값을 초기화할 수 있습니다.
    }

    public void setUniqueUserInfo(String uniqueUserInfo) {
        this.uniqueUserInfo = uniqueUserInfo;
    }

}