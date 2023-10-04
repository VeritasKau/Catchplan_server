package Sanhak.wakeUp.team.repository;

import Sanhak.wakeUp.team.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

//import java.util.Optional;
//
public interface UserRepository extends JpaRepository<User,Long> {

    boolean findByEmail(String email);
}