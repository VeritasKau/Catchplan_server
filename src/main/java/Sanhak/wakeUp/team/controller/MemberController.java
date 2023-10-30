package Sanhak.wakeUp.team.controller;

import Sanhak.wakeUp.global.utils.valid.TokenValidator;
import Sanhak.wakeUp.team.entity.Member;
import Sanhak.wakeUp.team.service.MemberService;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api")
public class MemberController {

    @Autowired
    private TokenValidator tokenValidator;

    @Autowired
    private MemberService memberService;

    @PostMapping("/secure-endpoint")
    public ResponseEntity<String> secureEndpoint(@RequestHeader("Authorization") String token) {
        // Authorization 헤더에서 토큰을 추출
        String jwtToken = token.replace("Bearer ", "");

        // 토큰을 검증
        Claims claims = tokenValidator.validateToken(jwtToken);


        if (claims != null) {
            // 토큰이 유효한 경우
            // 사용자 인증 및 권한 확인
            // claims에서 필요한 정보를 추출하여 사용자 확인 및 권한 부여 수행
            String uniqueUserInfo = (String) claims.get("uniqueUserInfo");
            Optional<Member> memberOptional = memberService.findByUniqueUserInfo(uniqueUserInfo);

            if (memberOptional.isPresent()) {
                Member member = memberOptional.get();
                // Member 엔티티에서 원하는 정보를 추출하여 반환
                // 예를 들어, Member 엔티티에 name 필드가 있다면 다음과 같이 사용 가능
                String memberUniqueUserInfo = member.getUniqueUserInfo();
                return ResponseEntity.ok("Authenticated. uniqueUserInfo: " + memberUniqueUserInfo);
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not found");
            }
        } else {
            // 토큰이 유효하지 않은 경우
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token validation failed");
        }
    }
    @GetMapping("/check-member")
    public ResponseEntity<String> checkMember(@RequestParam("uniqueUserInfo") String uniqueUserInfo) {
        boolean memberExists = memberService.checkIfMemberExists(uniqueUserInfo);

        if (memberExists) {
            return ResponseEntity.ok("Member exists");
        } else {
            return ResponseEntity.ok("Member does not exist");
        }
    }



}



