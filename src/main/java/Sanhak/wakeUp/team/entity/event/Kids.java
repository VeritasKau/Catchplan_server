package Sanhak.wakeUp.team.entity.event;


import Sanhak.wakeUp.team.entity.Event;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;

@Getter
@Entity
@DiscriminatorValue("Kids")
public class Kids extends Event {
}
