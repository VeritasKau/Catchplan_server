package Sanhak.wakeUp.team.entity;


import Sanhak.wakeUp.global.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
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
    private String genre;
    private String mbti;

    @OneToMany(mappedBy = "member")
    private List<Scrap> scraps = new ArrayList<>();
    public Member() {
    }

    public void setUniqueUserInfo(String uniqueUserInfo) {
        this.uniqueUserInfo = uniqueUserInfo;
    }

}