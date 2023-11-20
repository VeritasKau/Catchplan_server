package Sanhak.wakeUp.team.small_event.repository;

import Sanhak.wakeUp.team.event.entity.Event;
import Sanhak.wakeUp.team.small_event.entity.SmallEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SmallEventRepository extends JpaRepository<SmallEvent,Long> {
    Optional<SmallEvent> findById(Long id);
    List<SmallEvent> findByText(String text);

}
