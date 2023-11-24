package Sanhak.wakeUp.team.small_event.controller;

import Sanhak.wakeUp.team.event.dto.EventResponse;
import Sanhak.wakeUp.team.small_event.dto.SmallEventRequest;
import Sanhak.wakeUp.team.small_event.dto.SmallEventResponse;
import Sanhak.wakeUp.team.small_event.service.SmallEventService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/small_events")
@Slf4j
public class SmallEventController {
    private final SmallEventService smallEventService;




    //새로운 small event 생성 api
    @PostMapping(value = "/create", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<String> createSmallEvent(
            @ModelAttribute SmallEventRequest smallEventRequest,
            @RequestPart MultipartFile image,
            @RequestPart MultipartFile detail1
    ) {
        try {
            smallEventService.createSmallEvent(smallEventRequest, image, detail1);
            return new ResponseEntity<>("OK", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    //모든 small event 조회 api
    @GetMapping("")
    public List<SmallEventResponse> getAllSmallEvents(){
        return smallEventService.getAllSmallEvents();
    }

    //small event 제목으로 가져오기
    @GetMapping("/text/{text}")
    public ResponseEntity<List<SmallEventResponse>> getSmallEventByText(@PathVariable("text") String text) {
        try {
            List<SmallEventResponse> smallEventByText = smallEventService.getSmallEventByText(text);
            return ResponseEntity.ok(smallEventByText);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    //small event 1개가져오기
    @GetMapping("/{id}")
    public ResponseEntity<SmallEventResponse> getSmallEventById(@PathVariable("id") Long id) {
        try {
            SmallEventResponse getSmallEvent = smallEventService.getSmallEventById(id);
            return ResponseEntity.ok(getSmallEvent);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    //작성한 small event 업데이트
    //path는 그냥쓰고 requestparm은 ?붙히고
    @PutMapping(value = "/{id}", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<String> editSmallEvent(@ModelAttribute SmallEventRequest smallEventRequest,
                                                 @RequestPart MultipartFile image,
                                                 @RequestPart MultipartFile detail1,
                                                 @PathVariable("id")Long id){
        try {
            SmallEventResponse editSmallEvent = smallEventService.editSmallEvent(smallEventRequest, id,image,detail1);
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
    public ResponseEntity<SmallEventResponse> deleteSmallEvent(@PathVariable("id") Long id) {
        try {
            SmallEventResponse smallEventResponse = smallEventService.deleteSmallEvent(id);
            if (smallEventResponse != null) {
                return new ResponseEntity<>(smallEventResponse, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
