package Sanhak.wakeUp.team.event.controller;

import Sanhak.wakeUp.team.event.dto.EventResponse;
import Sanhak.wakeUp.team.event.service.EventService;
import Sanhak.wakeUp.team.global.exception.TokenNotFoundException;
import Sanhak.wakeUp.team.global.exception.TokenValidationException;
import Sanhak.wakeUp.team.global.utils.valid.TokenValidator;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/events")
public class EventController {
    private final EventService eventService;
    private final TokenValidator tokenValidator;

    //모든 event 조희 api
    //@Operation(summary="모든 event 조회",description = "전체 event 조회 api",tags="event관리")
    @GetMapping("")
    public List<EventResponse> getAllEvents(HttpServletRequest request) {
        validateToken(request.getHeader("Authorization"));
        return eventService.getAllEvents();
    }

    //카테고리별로 event를 조회하는 api
    @GetMapping("/dtype")
    public List<EventResponse> getEventByDtype(HttpServletRequest request, @RequestParam("dtype") String dtype) {
        validateToken(request.getHeader("Authorization"));
        List<EventResponse> dTypeEventResponse = eventService.getEventByDtype(dtype);
        if (dTypeEventResponse != null) {
            return dTypeEventResponse;
        } else {
            return null;
        }
    };

    //특정 eventId를 조회하는 api
    @GetMapping("/id")
    public EventResponse getEventById(HttpServletRequest request, @RequestParam("id") Long id) {
        validateToken(request.getHeader("Authorization"));
        EventResponse getEvent = eventService.getEventById(id);
        if (getEvent != null) {
            return getEvent;
        } else {
            return null;
        }
    }
    //특정 event제목으로 조회하는 api
    @GetMapping("/text")
    public List<EventResponse> getEventByText(HttpServletRequest request, @RequestParam("text") String text) {
        validateToken(request.getHeader("Authorization"));
        List<EventResponse> textEventResponse = eventService.getEventByText(text);
        if (textEventResponse != null) {
            return textEventResponse;
        } else {
            return null;
        }
    }

    //특정 PLACE이름으로 조회하는 api
    @GetMapping("/place")
    public List<EventResponse> getEventByPlace(HttpServletRequest request, @RequestParam("place") String place) {
        validateToken(request.getHeader("Authorization"));
        List<EventResponse> placeEventResponse = eventService.getEventByPlace(place);
        if (placeEventResponse != null) {
            return placeEventResponse;
        } else {
            return null;
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

