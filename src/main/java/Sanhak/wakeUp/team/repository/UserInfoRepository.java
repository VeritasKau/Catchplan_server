package Sanhak.wakeUp.team.repository;

import Sanhak.wakeUp.team.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

//import java.util.Optional;
//
public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {

    UserInfo findByUniqueUserInfo(String uniqueUserInfo);
}