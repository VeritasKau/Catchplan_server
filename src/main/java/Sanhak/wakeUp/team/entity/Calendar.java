package Sanhak.wakeUp.team.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor //모든 필드값을 파라미터로 받는 생성자를 생성
@NoArgsConstructor //파라미터가 없는 기본 생성자 생성
@Entity
@Table(name = "Calendar")
public class Calendar extends TimeEntity{

    @Id //테이블의 기본 키(PK)와 객체의 필드를 매핑
    //@Id만 사용할 경우 기본 키를 직접 할당해 주어야 한다.
    //
    //기본 키를 직접 할당하는 대신 데이터베이스가 생성해주는 값을 사용하려면 @GeneratedValue를 사용
    //IDENTITY 전략은 기본 키 생성을 데이터베이스에 위임하는 전략입니다. MySQL의 AUTO_INCREMENT 기능
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "calendar_id")
    private Long id;

    @Column(name = "url")
    private String url;

    @Column(name = "picture")
    private String picture;

    @Column(name = "title")
    private String title;
}
