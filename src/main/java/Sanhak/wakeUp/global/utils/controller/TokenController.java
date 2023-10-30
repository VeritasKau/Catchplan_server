package Sanhak.wakeUp.global.utils.controller;


import Sanhak.wakeUp.global.utils.dto.JwtTokenDto;
import Sanhak.wakeUp.global.utils.service.TokenManager;
import Sanhak.wakeUp.team.dto.MemberRequest;
import Sanhak.wakeUp.team.entity.Member;
import Sanhak.wakeUp.team.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

@RestController
@RequestMapping("/api/token")
public class TokenController {
    private final TokenManager tokenManager;
    private final MemberService memberService;

    @Autowired
    public TokenController(TokenManager tokenManager, MemberService memberService) {
        this.tokenManager = tokenManager;
        this.memberService = memberService;
    }

    @Operation(summary = "Generate JWT Token", description = "Generate a JWT token for the provided unique user information.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Token generated successfully"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @PostMapping("/generate")
    public JwtTokenDto generateToken(@RequestBody MemberRequest memberRequest) {
        String uniqueUserInfo = memberRequest.getUniqueUserInfo();
        Member member = memberService.findOrCreateMemberByUniqueUserInfo(uniqueUserInfo);
        JwtTokenDto jwtTokenDto = tokenManager.createJwtTokenDto(uniqueUserInfo);
        return jwtTokenDto;
    }
}
//
//package Sanhak.wakeUp.global.utils.controller;
//
//
//import Sanhak.wakeUp.global.utils.dto.JwtTokenDto;
//import Sanhak.wakeUp.global.utils.service.TokenManager;
//import Sanhak.wakeUp.team.dto.MemberRequest;
//import Sanhak.wakeUp.team.entity.Member;
//import Sanhak.wakeUp.team.exception.DuplicateUserException;
//import Sanhak.wakeUp.team.service.MemberService;
//import io.swagger.v3.oas.annotations.Operation;
//import io.swagger.v3.oas.annotations.responses.ApiResponse;
//import io.swagger.v3.oas.annotations.responses.ApiResponses;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.time.LocalDateTime;
//
//@RestController
//@RequestMapping("/api/token")
//public class TokenController {
//    private final TokenManager tokenManager;
//    private final MemberService memberService;
//
//    @Autowired
//    public TokenController(TokenManager tokenManager, MemberService memberService) {
//        this.tokenManager = tokenManager;
//        this.memberService = memberService;
//    }
//
//    @Operation(summary = "Generate JWT Token", description = "Generate a JWT token for the provided unique Member information.")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "Token generated successfully"),
//    })
//    @PostMapping("/generate") public ResponseEntity<JwtTokenDto> generateToken(@RequestBody MemberRequest memberRequest) {
//        String uniqueUserInfo = memberRequest.getUniqueUserInfo();
//
//        // 중복 사용자 확인
//        if (memberService.isDuplicateUser(uniqueUserInfo)) {
//            throw new DuplicateUserException("중복된 사용자가 있습니다. uniqueUserInfo: " + uniqueUserInfo);
//        }
//        Member member = memberService.findOrCreateMemberByUniqueUserInfo(uniqueUserInfo);
//
//
//
//        JwtTokenDto jwtTokenDto = tokenManager.createJwtTokenDto(uniqueUserInfo);
//        jwtTokenDto.setTransactionTime(LocalDateTime.now().toString());
//        jwtTokenDto.setStatus(HttpStatus.OK.toString());
//        jwtTokenDto.setDescription("The operation has been successfully completed.");
//        jwtTokenDto.setStatusCode(HttpStatus.OK.value());
//
//
//        return ResponseEntity.ok(jwtTokenDto);
//    }
//}
//
//
//


