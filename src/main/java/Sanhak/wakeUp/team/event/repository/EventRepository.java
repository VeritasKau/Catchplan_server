package Sanhak.wakeUp.team.event.repository;

import Sanhak.wakeUp.team.event.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
    List<Event> findByDtype(String dtype);
    Optional<Event> findById(Long id);

    List<Event> findByText(String text);
    List<Event> findByPlace(String place);
}
