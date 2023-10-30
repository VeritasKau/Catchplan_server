package Sanhak.wakeUp.team.controller;

import Sanhak.wakeUp.team.dto.UserInfoUpdateRequest;
import Sanhak.wakeUp.team.exception.UserNotFoundException;
import Sanhak.wakeUp.team.exception.ValidationException;
import Sanhak.wakeUp.team.service.UserInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/user-info")
public class UserInfoController {

    private final UserInfoService userInfoService;

    private static final Logger logger = LoggerFactory.getLogger(UserInfoController.class);

    @Autowired
    public UserInfoController(UserInfoService userInfoService) {
        this.userInfoService = userInfoService;
    }

    @PostMapping("/update")
    public ResponseEntity<Map<String, String>> updateUserInfo(@RequestBody UserInfoUpdateRequest request) {
        try {
            logger.debug("Received request to update user info: {}", request);

            // 사용자 정보를 업데이트하는 서비스 메서드 호출
            userInfoService.saveOrUpdateUserInfo(request);

            logger.debug("User info updated successfully");
            // 업데이트된 정보를 JSON 응답에 추가
            Map<String, String> response = new HashMap<>();
            response.put("message", "User info updated successfully");
            response.put("mbti", request.getMbti());
            response.put("name", request.getName());
            response.put("sex", request.getSex());
            response.put("genre", request.getGenre());

            return ResponseEntity.ok(response);


        } catch (UserNotFoundException e) {
            logger.error("User not found: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.singletonMap("error", "User not found"));
        } catch (ValidationException e) {
            logger.error("Validation failed: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.singletonMap("error", "Validation failed: " ));
        } catch (Exception e) {
            logger.error("Failed to update user info", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.singletonMap("error", "Failed to update user info"));
        }
    }
}
