package Sanhak.wakeUp.team.member.controller;

import Sanhak.wakeUp.team.member.dto.ScrapRequest;
import Sanhak.wakeUp.team.member.service.ScrapService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/scraps")
@RequiredArgsConstructor
public class ScrapController {

    private final ScrapService scrapService;

    @PostMapping
    public ResponseEntity<String> addScrap(@RequestBody ScrapRequest scrapRequest) {
        try {
            scrapService.addScrap(scrapRequest.getUniqueUserInfo(), scrapRequest.getEventId());
            return ResponseEntity.ok("Scrap added successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to add scrap");
        }
    }



}

