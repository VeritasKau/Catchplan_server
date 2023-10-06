package Sanhak.wakeUp.team.service;

import Sanhak.wakeUp.team.dto.UserRequest;
import Sanhak.wakeUp.team.dto.UserResponse;
import Sanhak.wakeUp.team.entity.User;
import Sanhak.wakeUp.team.repository.UserRepository;
import jakarta.persistence.Column;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final KakaoService kakaoService;
//    private final JwtTokenProvider jwtTokenProvider;

    //등록(c)
    @Transactional
    public void createUser(UserRequest userRequest) {
        User newUser = new User();
        userRepository.save(newUser);

    }

    //전체조회(r)
    @Transactional
    public List<UserResponse> getAllUsers() {
        List<User> users = userRepository.findAll();
        List<UserResponse> userResponses = new ArrayList<>();

        for (User user : users) {
            UserResponse userResponse = UserResponse.of(
                    user.getId(),
                    user.getName(),
                    user.getEmail(),
                    user.getAge()
            );
            userResponses.add(userResponse);
        }
        return userResponses;
    }

    //특정인물조회(r)
    @Transactional
    public UserResponse getUserById(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("사용자를 찾을 수 없습니다. ID: " + userId));
        return UserResponse.of(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getAge()
        );
    }

    //업데이트(u)
    @Transactional
    public UserResponse editUserById(UserRequest userRequest,Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("사용자를 찾을 수 없습니다. ID: " + userId));
        user.setName(userRequest.getName());
        user.setEmail(userRequest.getEmail());
        user.setAge(userRequest.getAge());

        userRepository.save(user);

        return UserResponse.of(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getAge()
        );
    }

    //삭제(d)
    @Transactional
    public UserResponse deleteUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("사용자를 찾을 수 없습니다. ID: " + id));
        userRepository.delete(user);

        return UserResponse.of(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getAge()
        );
    }

    public String loginUser(String accessToken) {
        //TODO: 카카오 유저 인포 받아오기.
        String userEmail = "test@email.com";
        kakaoService.getUserInfo(accessToken);
        //TODO: 기존 유저인지 DB에서 체크
//        boolean checkUserResult = checkOldUser(userEmail);

//        if(checkUserResult){ // 기존 유저일 경우
//            //TODO: 기존 유저라면 그냥 DB pk를 기준으로 JWT 만들어서 리턴
//            // 유저등록 로직
//        }

//        //TODO: pk를 기준으로 JWT 만들어서 리턴
//        String jwt = jwtTokenProvider.createToken(user, User);
//

//        return jwt;
        return null;
    }

    private boolean checkOldUser(String email){
        return userRepository.findByEmail(email);
    }
}
