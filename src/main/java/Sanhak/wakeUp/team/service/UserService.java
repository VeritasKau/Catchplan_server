//package Sanhak.wakeUp.team.service;
//
//import Sanhak.wakeUp.team.dto.UserRequest;
//import Sanhak.wakeUp.team.dto.UserResponse;
//import Sanhak.wakeUp.team.entity.Users;
//import Sanhak.wakeUp.team.repository.UserRepository;
//import jakarta.persistence.EntityNotFoundException;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Service
//@RequiredArgsConstructor
//public class UserService {
//    private final UserRepository userRepository;
//
//    //등록(c)
//    @Transactional
//    public void createUser(UserRequest userRequest) {
//        Users newUser = new Users(userRequest.getName(), userRequest.getEmail(),
//                userRequest.getPassword(), userRequest.getAge());
//        userRepository.save(newUser);
//    }
//
//    //전체조회(r)
//    @Transactional
//    public List<UserResponse> getAllUsers() {
//        List<Users> users = userRepository.findAll();
//        List<UserResponse> userResponses = new ArrayList<>();
//
//        for (Users user : users) {
//            UserResponse userResponse = UserResponse.of(
//                    user.getId(),
//                    user.getName(),
//                    user.getEmail(),
//                    user.getAge()
//            );
//            userResponses.add(userResponse);
//        }
//        return userResponses;
//    }
//
//    //특정인물조회(r)
//    @Transactional
//    public UserResponse getUserById(Long userId) {
//        Users user = userRepository.findById(userId)
//                .orElseThrow(() -> new EntityNotFoundException("사용자를 찾을 수 없습니다. ID: " + userId));
//        return UserResponse.of(
//                user.getId(),
//                user.getName(),
//                user.getEmail(),
//                user.getAge()
//        );
//    }
//
//    //업데이트(u)
//    @Transactional
//    public UserResponse editUserById(UserRequest userRequest,Long userId) {
//        Users user = userRepository.findById(userId)
//                .orElseThrow(() -> new EntityNotFoundException("사용자를 찾을 수 없습니다. ID: " + userId));
//        user.setName(userRequest.getName());
//        user.setEmail(userRequest.getEmail());
//        user.setAge(userRequest.getAge());
//        user.setPassword(userRequest.getPassword());
//
//        userRepository.save(user);
//
//        return UserResponse.of(
//                user.getId(),
//                user.getName(),
//                user.getEmail(),
//                user.getAge()
//        );
//    }
//
//    //삭제(d)
//    @Transactional
//    public UserResponse deleteUser(Long id) {
//        Users user = userRepository.findById(id)
//                .orElseThrow(() -> new EntityNotFoundException("사용자를 찾을 수 없습니다. ID: " + id));
//        userRepository.delete(user);
//
//        return UserResponse.of(
//                user.getId(),
//                user.getName(),
//                user.getEmail(),
//                user.getAge()
//        );
//    }
//}
