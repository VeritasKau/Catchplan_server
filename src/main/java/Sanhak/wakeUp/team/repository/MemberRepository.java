package Sanhak.wakeUp.team.repository;

import Sanhak.wakeUp.team.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface MemberRepository extends JpaRepository<Member,Long> {

    Optional<Member> findByUniqueUserInfo(String token);

}

