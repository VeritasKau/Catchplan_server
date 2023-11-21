package Sanhak.wakeUp.team.member.controller;

import Sanhak.wakeUp.team.global.utils.valid.TokenValidator;
import Sanhak.wakeUp.team.member.dto.ScrapInfoResponse;
import Sanhak.wakeUp.team.member.dto.ScrapRequest;
import Sanhak.wakeUp.team.member.exception.DuplicateScrapException;
import Sanhak.wakeUp.team.member.exception.ScrapNotFoundException;
import Sanhak.wakeUp.team.member.service.ScrapService;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/scrap")
@RequiredArgsConstructor
public class ScrapController {

    private final TokenValidator tokenValidator;

    private final ScrapService scrapService;

    @PostMapping("/save")
    public ResponseEntity<String> addScrap(
            @RequestHeader(name = "Authorization") String authorizationHeader,
            @RequestBody ScrapRequest scrapRequest) {
        try {
            // JWT 토큰 검증
            Claims claims = tokenValidator.validateToken(authorizationHeader.replace("Bearer ", ""));
            String uniqueUserInfo = claims.get("uniqueUserInfo", String.class);

            scrapService.addScrap(uniqueUserInfo, scrapRequest.getEventId());
            return ResponseEntity.ok("Scrap added successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to add scrap");
        }
    }



    @GetMapping("/getScrap/{scrapId}")
    public ResponseEntity<ScrapInfoResponse> getScrapInfo(@PathVariable Long scrapId) {
        try {
            ScrapInfoResponse scrapInfoResponse = scrapService.getScrapInfo(scrapId);
            return ResponseEntity.ok(scrapInfoResponse);
        } catch (ScrapNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }
}
