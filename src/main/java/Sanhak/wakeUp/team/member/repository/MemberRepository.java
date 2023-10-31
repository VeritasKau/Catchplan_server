package Sanhak.wakeUp.team.member.repository;

import Sanhak.wakeUp.team.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface MemberRepository extends JpaRepository<Member,Long> {

    Optional<Member> findByUniqueUserInfo(String email);

}

