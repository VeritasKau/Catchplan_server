//package Sanhak.wakeUp.team.entity;
//
//import Sanhak.wakeUp.global.entity.BaseTimeEntity;
//import jakarta.persistence.*;
//import lombok.*;
//
//@Entity
//@Getter
//@Setter
//@Table(name = "UserInfo")
//@Builder
//@AllArgsConstructor
//public class UserInfo extends BaseTimeEntity {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "user_id")
//    private Long id;
//    private String name;
//    private String sex;
//    private String genre;
//    private String mbti;
//    @Column(name = "unique_user_info")
//    private String uniqueUserInfo;
//
//    @OneToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "member_id")
//    private Member member;
//    public UserInfo() {
//    }
//
//}


//    public static void setName(String name) {
//        this.name = name;
//    }
//    private void setEmail(String email) {
//        this.email = email;
//    }
//    private void setAge(Integer age) {
//        this.age = age;
//    }
//    private void setPassword(String password) {
//        this.password = password;
//    }




//
//import jakarta.persistence.*;
//import lombok.Builder;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//
//import javax.management.relation.Role;
//
//@Entity
//@Getter
//@NoArgsConstructor
//public class Users {
//    private String email;
//    private String phone;
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//    private String name;
//    private String password;
//    @Enumerated(EnumType.STRING)
//    private Role role;
//
//    @Builder
//
//    public Users(Long id, String name,String email,String phone, String password, Role role) {
//        this.id = id;
//        this.name = name;
//        this.email=email;
//        this.phone=phone;
//        this.password = password;
//        this.role = role;
//    }
//    public Users update(String name){
//        this.name=name;
//        return this;
//    }
//    public String getRoleKey(){
//        return this.role.getKey();
//    }
//}
