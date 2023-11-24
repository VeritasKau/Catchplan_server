package Sanhak.wakeUp.team.small_event.controller;

import Sanhak.wakeUp.team.event.dto.EventResponse;
import Sanhak.wakeUp.team.small_event.dto.SmallEventRequest;
import Sanhak.wakeUp.team.small_event.dto.SmallEventResponse;
import Sanhak.wakeUp.team.small_event.service.SmallEventService;
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

    //새로운 small event 생성 api
    @PostMapping("/create")
    public ResponseEntity<String> createSmallEvent(@RequestBody SmallEventRequest smallEventRequest){
        try {
            smallEventService.createSmallEvent(smallEventRequest);
            return new ResponseEntity<>("OK", HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("Error",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //모든 small event 조회 api
    @GetMapping("")
    public List<SmallEventResponse> getAllSmallEvents(){
        return smallEventService.getAllSmallEvents();
    }

    //small event 제목으로 가져오기
    @GetMapping("/text")
    public List<SmallEventResponse> getSmallEventByText(@RequestParam("text") String text){
        List<SmallEventResponse> smallEventByText = smallEventService.getSmallEventByText(text);
        if(smallEventByText !=null){
            return smallEventByText;
        }else{
            return null;
        }
    }

    //small event 1개가져오기
    @GetMapping("/id")
    public SmallEventResponse getSmallEventById(@RequestParam("id") Long id){
        SmallEventResponse getSmallEvent=smallEventService.getSmallEventById(id);
        if(getSmallEvent !=null){
            return getSmallEvent;
        }else{
            return null;
        }
    }

    //작성한 small event 업데이트
    @PutMapping("/{id}")
    public ResponseEntity<String> editSmallEvent(@RequestBody SmallEventRequest smallEventRequest,@PathVariable("id")Long id){
        try {
            SmallEventResponse editSmallEvent = smallEventService.editSmallEvent(smallEventRequest, id);
            if (editSmallEvent != null) {
                return new ResponseEntity<>("OK", HttpStatus.OK);
            }else{
                return new ResponseEntity<>("Not Found",HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            return new ResponseEntity<>("Error",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    //작성한 small event삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSmallEvent(@PathVariable("id")Long id){
        try {
            SmallEventResponse smallEventResponse = smallEventService.deleteSmallEvent(id);
            if(smallEventResponse !=null){
                return new ResponseEntity<>("OK",HttpStatus.OK);
            }
            else{
                return new ResponseEntity<>("Not Found",HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            return new ResponseEntity<>("Error",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}