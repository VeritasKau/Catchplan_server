


package Sanhak.wakeUp.team.member.entity;


import Sanhak.wakeUp.team.global.entity.BaseTimeEntity;
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
    private String genre1;
    private String genre2;
    private String genre3;
    private String mbti;

    //@OneToMany(mappedBy = "member")
    //private List<Scrap> scraps = new ArrayList<>();
    public Member() {
    }

    public void setUniqueUserInfo(String uniqueUserInfo) {
        this.uniqueUserInfo = uniqueUserInfo;
    }

}