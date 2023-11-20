package Sanhak.wakeUp.team.small_event.service;

import Sanhak.wakeUp.team.small_event.dto.SmallEventRequest;
import Sanhak.wakeUp.team.small_event.dto.SmallEventResponse;
import Sanhak.wakeUp.team.small_event.entity.SmallEvent;
import Sanhak.wakeUp.team.small_event.repository.SmallEventRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SmallEventService {
    private final SmallEventRepository smallEventRepository;

    //small event생성하기
    @Transactional
    public void createSmallEvent(SmallEventRequest smallEventRequest) {
        SmallEvent newSmallEvents = new SmallEvent(smallEventRequest.getImage(), smallEventRequest.getText()
                , smallEventRequest.getPlace(), smallEventRequest.getDuration(), smallEventRequest.getUrl(), smallEventRequest.getDetail()
                , smallEventRequest.getDetail2());
        smallEventRepository.save(newSmallEvents);
    }

    //모든 smallevent 가져오기
    @Transactional

    public List<SmallEventResponse> getAllSmallEvents() {
        List<SmallEvent> smallevents = smallEventRepository.findAll();
        List<SmallEventResponse> smallEventResponses = new ArrayList<>();

        for (SmallEvent smallEvent : smallevents) {
            SmallEventResponse smallEventResponse = SmallEventResponse.of(
                    smallEvent.getId(),
                    smallEvent.getImage(),
                    smallEvent.getText(),
                    smallEvent.getPlace(),
                    smallEvent.getDuration(),
                    smallEvent.getUrl(),
                    smallEvent.getDetail(),
                    smallEvent.getDetail2(),
                    smallEvent.getStatus()
            );
            smallEventResponses.add(smallEventResponse);
        }
        return smallEventResponses;
    }

    //id가지고 small_event 한개만 가져오는거 혹시 필요할수도 있으니까
    @Transactional
    public  SmallEventResponse getSmallEventById(Long id){
        SmallEvent smallEvent = smallEventRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("이벤트를 찾을수가 없습니다. Id: " +id));
        return SmallEventResponse.of(
                smallEvent.getId(),
                smallEvent.getImage(),
                smallEvent.getText(),
                smallEvent.getPlace(),
                smallEvent.getDuration(),
                smallEvent.getUrl(),
                smallEvent.getDetail(),
                smallEvent.getDetail2(),
                smallEvent.getStatus()
        );
    }

    //제목으로 event검색해주기
    @Transactional
    public List<SmallEventResponse> getSmallEventByText(String text){
        List<SmallEvent> smallEvents=smallEventRepository.findByText(text);
        List<SmallEventResponse> smallEventResponses=new ArrayList<>();

        for(SmallEvent smallEvent:smallEvents){
            SmallEventResponse smallEventResponse = SmallEventResponse.of(
                    smallEvent.getId(),
                    smallEvent.getImage(),
                    smallEvent.getText(),
                    smallEvent.getPlace(),
                    smallEvent.getDuration(),
                    smallEvent.getUrl(),
                    smallEvent.getDetail(),
                    smallEvent.getDetail2(),
                    smallEvent.getStatus()
            );
            smallEventResponses.add(smallEventResponse);
        }
        return smallEventResponses;
    }

    //small event 수정(update)
    @Transactional
    public SmallEventResponse editSmallEvent(SmallEventRequest smallEventRequest,Long id){
        SmallEvent smallEvent=smallEventRepository.findById(id)
                .orElseThrow(()->new EntityNotFoundException("사용자를 찾을 수 없습니다.Id: "+id));
        smallEvent.setImage(smallEventRequest.getImage());
        smallEvent.setText(smallEventRequest.getText());
        smallEvent.setPlace(smallEventRequest.getPlace());
        smallEvent.setDuration(smallEventRequest.getDuration());
        smallEvent.setUrl(smallEventRequest.getUrl());
        smallEvent.setDetail(smallEventRequest.getDetail());
        smallEvent.setDetail2(smallEventRequest.getDetail2());

        return SmallEventResponse.of(
                smallEvent.getId(),
                smallEvent.getImage(),
                smallEvent.getText(),
                smallEvent.getPlace(),
                smallEvent.getDuration(),
                smallEvent.getUrl(),
                smallEvent.getDetail(),
                smallEvent.getDetail2(),
                smallEvent.getStatus()
        );
    }

    //small event삭제
    @Transactional
    public SmallEventResponse deleteSmallEvent(Long id){
        SmallEvent smallEvent=smallEventRepository.findById(id)
                .orElseThrow(()->new EntityNotFoundException("사용자를 찾을 수 없습니다.Id: "+id));
        smallEventRepository.delete(smallEvent);

        return SmallEventResponse.of(
                smallEvent.getId(),
                smallEvent.getImage(),
                smallEvent.getText(),
                smallEvent.getPlace(),
                smallEvent.getDuration(),
                smallEvent.getUrl(),
                smallEvent.getDetail(),
                smallEvent.getDetail2(),
                smallEvent.getStatus()
        );
    }

}
