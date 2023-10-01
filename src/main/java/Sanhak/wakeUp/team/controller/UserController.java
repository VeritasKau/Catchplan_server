package Sanhak.wakeUp.team.controller;


import Sanhak.wakeUp.team.dto.UserRequest;
import Sanhak.wakeUp.team.dto.UserResponse;
import Sanhak.wakeUp.team.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private  final UserService userService;

    //user등록하는 API
    @Operation(summary = "회원가입",description = "회원가입 api")
    @PostMapping("/sign-up")
    public String createUser(@RequestBody UserRequest userRequest){
       userService.createUser(userRequest);
        return "OK";   //프론트로 넘겨주는 부분
    }

    //모든 User를 조회하는 API
    @Operation(summary = "user전체조회",description = "user전체조회 api",tags = "회원관리")
    @GetMapping("")
    public List<UserResponse> getAllUsers(){
        return userService.getAllUsers();
    }

    //특정 User를 조회하는 API
    @Operation(summary = "user조회",description = "user조회 api",tags = "회원관리")
    @GetMapping("/{userId}")
    public UserResponse  getUserById (@PathVariable("userId") Long userId){
        UserResponse getUser = userService.getUserById(userId);
        if(getUser !=null) {
            return getUser;
        }else{
            return null;
        }
    }

    //특정 User를 수정하는 API
    @Operation(summary = "user수정",description = "user수정 api",tags = "회원관리")
    @PutMapping("/{userId}")
    public UserResponse editUserById(@RequestBody UserRequest userRequest,@PathVariable("userId")Long userId){
        UserResponse editedUser = userService.editUserById(userRequest,userId);
        if(editedUser != null) {
            return editedUser;
        }
        else{
            return  null;
        }
    }



    //특정 User를 삭제하는 API
    @Operation(summary = "user삭제",description = "user삭제 api",tags = "회원관리")
    @DeleteMapping("/{userId}")
    public String deleteUser(@PathVariable("userId")Long userId){
        UserResponse user = userService.deleteUser(userId);
        if(user !=null) {
            return "OK";
        }else{
            return null;
        }
    }
}
