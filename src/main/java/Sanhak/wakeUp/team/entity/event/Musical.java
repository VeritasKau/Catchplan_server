package Sanhak.wakeUp.team.entity.event;//package Sanhak.wakeUp.team.entity.event;

import Sanhak.wakeUp.team.entity.Event;
import Sanhak.wakeUp.team.entity.Scrap;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;


@Getter
@Entity
@DiscriminatorValue("Musical")
public class Musical extends Event {

}
