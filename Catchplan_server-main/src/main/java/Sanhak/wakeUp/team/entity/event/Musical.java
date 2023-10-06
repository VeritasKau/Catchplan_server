package Sanhak.wakeUp.team.entity.event;


import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Musical extends Event{
    private String director;

}
