package Sanhak.wakeUp.team.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Scrap")
public class Scrap  extends TimeEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "scrap_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "calendar_id")
    private Calendar calendar;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
