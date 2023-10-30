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
