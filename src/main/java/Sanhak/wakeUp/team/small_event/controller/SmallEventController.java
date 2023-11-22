package Sanhak.wakeUp.team.small_event.controller;

import Sanhak.wakeUp.team.small_event.dto.SmallEventRequest;
import Sanhak.wakeUp.team.small_event.dto.SmallEventResponse;
import Sanhak.wakeUp.team.small_event.service.SmallEventService;
import Sanhak.wakeUp.team.global.exception.TokenNotFoundException;
import Sanhak.wakeUp.team.global.exception.TokenValidationException;
import Sanhak.wakeUp.team.global.utils.valid.TokenValidator;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/small_events")
public class SmallEventController {
    private final SmallEventService smallEventService;
    private final TokenValidator tokenValidator;

    @PostMapping("/create")
    public ResponseEntity<String> createSmallEvent(HttpServletRequest request, @RequestBody SmallEventRequest smallEventRequest) {
        validateToken(request.getHeader("Authorization"));
        try {
            smallEventService.createSmallEvent(smallEventRequest);
            return new ResponseEntity<>("OK", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("")
    public List<SmallEventResponse> getAllSmallEvents(HttpServletRequest request) {
        validateToken(request.getHeader("Authorization"));
        return smallEventService.getAllSmallEvents();
    }

    @GetMapping("/text")
    public List<SmallEventResponse> getSmallEventByText(HttpServletRequest request, @RequestParam("text") String text) {
        validateToken(request.getHeader("Authorization"));
        List<SmallEventResponse> smallEventByText = smallEventService.getSmallEventByText(text);
        if (smallEventByText != null) {
            return smallEventByText;
        } else {
            return null;
        }
    }

    @GetMapping("/id")
    public SmallEventResponse getSmallEventById(HttpServletRequest request, @RequestParam("id") Long id) {
        validateToken(request.getHeader("Authorization"));
        SmallEventResponse getSmallEvent = smallEventService.getSmallEventById(id);
        if (getSmallEvent != null) {
            return getSmallEvent;
        } else {
            return null;
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> editSmallEvent(HttpServletRequest request, @RequestBody SmallEventRequest smallEventRequest, @PathVariable("id") Long id) {
        validateToken(request.getHeader("Authorization"));
        try {
            SmallEventResponse editSmallEvent = smallEventService.editSmallEvent(smallEventRequest, id);
            if (editSmallEvent != null) {
                return new ResponseEntity<>("OK", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Not Found", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSmallEvent(HttpServletRequest request, @PathVariable("id") Long id) {
        validateToken(request.getHeader("Authorization"));
        try {
            SmallEventResponse smallEventResponse = smallEventService.deleteSmallEvent(id);
            if (smallEventResponse != null) {
                return new ResponseEntity<>("OK", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Not Found", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private void validateToken(String authorizationHeader) {
        try {
            // JWT 토큰 검증
            tokenValidator.validateToken(authorizationHeader.replace("Bearer ", ""));
        } catch (TokenNotFoundException | TokenValidationException e) {
            throw e;
        }
    }
}
