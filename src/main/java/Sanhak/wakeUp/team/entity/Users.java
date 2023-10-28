package Sanhak.wakeUp.team.entity;

import jakarta.persistence.*;
import lombok.*;

@ToString
@Getter
@Builder
@AllArgsConstructor
@Entity
@Setter
@NoArgsConstructor
@Table(name="user")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String password;
    private Integer age;


    public Users(String name, String email, String password, Integer age) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.age = age;
    }


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
