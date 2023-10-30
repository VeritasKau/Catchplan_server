//package Sanhak.wakeUp.team.entity;
//
//
//import Sanhak.wakeUp.global.entity.BaseTimeEntity;
//import jakarta.persistence.*;
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Getter;
//import lombok.Setter;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Entity
//@Getter
//@Builder
//@AllArgsConstructor
//public class Member extends BaseTimeEntity {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "member_id")
//    private Long id;
//
//
//    @Column(unique = true, length = 50, nullable = false)
//    private String uniqueUserInfo;
//
//
//    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    @JoinColumn(name = "user_id")
//    private UserInfo userInfo;
//
//    // public 생성자
//    public Member() {
//        this.userInfo = new UserInfo();
//        this.userInfo.setMember(this);
//    }
//
//    // public setter 메서드
//    public void setUniqueUserInfo(String uniqueUserInfo) {
//        this.uniqueUserInfo = uniqueUserInfo;
//    }
//    public UserInfo getUserInfo() {
//        return userInfo;
//    }
//
////    public void setUserInfo(UserInfo userInfo) {
////        this.userInfo = userInfo;
////    }
//
//}




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
    private String genre1;
    private String genre2;
    private String genre3;
    private String mbti;

    @OneToMany(mappedBy = "member")
    private List<Scrap> scraps = new ArrayList<>();
    public Member() {
    }

    public void setUniqueUserInfo(String uniqueUserInfo) {
        this.uniqueUserInfo = uniqueUserInfo;
    }

}
