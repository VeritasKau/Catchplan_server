package Sanhak.wakeUp.team.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter  @Setter
@Table(name="user")
public class User extends TimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "name")
    private String name;

    @NotNull
    @Column(name = "email")
    private String email;

    @Column(name = "age")
    private Integer age;

    @Column(name = "sex")
    private String sex;

    @Column(name = "phone")
    private String phone;

    @Column(name = "mbti")
    private String mbti;


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
}



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
//public class User {
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
//    public User(Long id, String name,String email,String phone, String password, Role role) {
//        this.id = id;
//        this.name = name;
//        this.email=email;
//        this.phone=phone;
//        this.password = password;
//        this.role = role;
//    }
//    public User update(String name){
//        this.name=name;
//        return this;
//    }
//    public String getRoleKey(){
//        return this.role.getKey();
//    }
//}
