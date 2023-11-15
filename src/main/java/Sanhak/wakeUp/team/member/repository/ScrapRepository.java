package Sanhak.wakeUp.team.member.repository;

import Sanhak.wakeUp.team.member.entity.Scrap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScrapRepository extends JpaRepository<Scrap, Long> {
}
