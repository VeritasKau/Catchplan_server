package Sanhak.wakeUp.team.controller;


import Sanhak.wakeUp.global.utils.valid.TokenValidator;
import Sanhak.wakeUp.team.dto.MemberInfoUpdateRequest;
import Sanhak.wakeUp.team.dto.MemberInfoUpdateResponse;
import Sanhak.wakeUp.team.entity.Member;
import Sanhak.wakeUp.team.repository.MemberRepository;
import Sanhak.wakeUp.team.service.MemberService;
import io.jsonwebtoken.Claims;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/memberInfo")
public class MemberInfoController {
    private final MemberService memberService;
    private final TokenValidator tokenValidator;
    private final MemberRepository memberRepository;


    @Autowired
    public MemberInfoController(MemberService memberService, TokenValidator tokenValidator, MemberRepository memberRepository) {
        this.memberService = memberService;
        this.tokenValidator = tokenValidator;
        this.memberRepository = memberRepository;
    }

    @Operation(summary = "Update MemberInfo", description = "Update a MemberInfo.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "MemberInfo updated successfully"),
    })
    @PostMapping("/save")
    public ResponseEntity<MemberInfoUpdateResponse> saveMemberInfo(
            @RequestHeader(name = "Authorization") String authorizationHeader,
            @Valid @RequestBody MemberInfoUpdateRequest userInfoUpdateRequest) {

        // JWT 토큰 검증
        Claims claims = tokenValidator.validateToken(authorizationHeader.replace("Bearer ", ""));

        String uniqueUserInfo = claims.get("uniqueUserInfo", String.class);

        // 여기서 uniqueUserInfo를 사용하여 멤버 정보를 가져오거나 생성
        Member member = memberService.findOrCreateMemberByUniqueUserInfo(uniqueUserInfo);

        // 멤버 정보 업데이트 로직 구현
        member.setName(userInfoUpdateRequest.getName());
        member.setSex(userInfoUpdateRequest.getSex());
        member.setGenre1(userInfoUpdateRequest.getGenre1());
        member.setGenre2(userInfoUpdateRequest.getGenre2());
        member.setGenre3(userInfoUpdateRequest.getGenre3());


        member.setMbti(userInfoUpdateRequest.getMbti());

        // MemberRepository를 사용하여 멤버 정보 저장
        member = memberRepository.save(member);

        // MemberInfoUpdateResponse 객체 초기화
        MemberInfoUpdateResponse memberInfoUpdateResponse = MemberInfoUpdateResponse.builder()
                .name(member.getName())
                .sex(member.getSex())
                .genre1(member.getGenre1())
                .genre2(member.getGenre2())
                .genre3(member.getGenre3())

                .mbti(member.getMbti())
                .transactionTime(LocalDateTime.now().toString())
                .status(HttpStatus.OK.toString())
                .description("The operation has been successfully completed.")
                .statusCode(HttpStatus.OK.value())
                .build();

        return ResponseEntity.ok(memberInfoUpdateResponse);

    }


    @Operation(summary = "Delete Member", description = "Delete a member's account.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Member deleted successfully"),
            @ApiResponse(responseCode = "401", description = "Unauthorized")
    })
    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteMember(@RequestHeader(name = "Authorization") String authorizationHeader) {
        // JWT 토큰 검증
        Claims claims = tokenValidator.validateToken(authorizationHeader.replace("Bearer ", ""));
        String uniqueUserInfo = claims.get("uniqueUserInfo", String.class);

        // 여기서 uniqueUserInfo를 사용하여 멤버 정보를 가져오거나 삭제
        Member member = memberService.findByUniqueUserInfo(uniqueUserInfo);

        if (member != null) {
            // 멤버 정보가 존재하면 삭제
            memberService.deleteMember(member.getId());
            return ResponseEntity.ok("Member deleted successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Member not found.");
        }
    }

}
