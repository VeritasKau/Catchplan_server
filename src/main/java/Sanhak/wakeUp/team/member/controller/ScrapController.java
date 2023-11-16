package Sanhak.wakeUp.team.member.controller;

import Sanhak.wakeUp.team.member.dto.ScrapInfoResponse;
import Sanhak.wakeUp.team.member.dto.ScrapRequest;
import Sanhak.wakeUp.team.member.exception.DuplicateScrapException;
import Sanhak.wakeUp.team.member.exception.ScrapNotFoundException;
import Sanhak.wakeUp.team.member.service.ScrapService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/scraps")
@RequiredArgsConstructor
public class ScrapController {

    private final ScrapService scrapService;
    @PostMapping("/save")
    public ResponseEntity<String> addScrap(@RequestBody ScrapRequest scrapRequest) {
        try {
            scrapService.addScrap(scrapRequest.getUniqueUserInfo(), scrapRequest.getEventId());
            return ResponseEntity.ok("Scrap added successfully");
        } catch (DuplicateScrapException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Duplicate scrap. This event is already scrapped by the user.");
        }
    }
    @GetMapping("/{scrapId}/info")
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
