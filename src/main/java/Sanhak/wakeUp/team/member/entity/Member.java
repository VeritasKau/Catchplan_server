


package Sanhak.wakeUp.team.member.entity;


import Sanhak.wakeUp.team.global.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

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


    public Member() {
    }

    public void setUniqueUserInfo(String uniqueUserInfo) {
        this.uniqueUserInfo = uniqueUserInfo;
    }
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Scrap> scraps;

    public List<Scrap> getScraps() {
        return scraps;

    }
}
