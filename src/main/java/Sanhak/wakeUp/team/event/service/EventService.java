package Sanhak.wakeUp.team.event.service;

import Sanhak.wakeUp.team.event.dto.EventResponse;
import Sanhak.wakeUp.team.event.entity.Event;
import Sanhak.wakeUp.team.event.repository.EventRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor

public class EventService {
    @Autowired
    private final EventRepository eventRepository;

    //모든 event 가져오기
    @Transactional
    public List<EventResponse> getAllEvents(){
        List<Event> events= eventRepository.findAll();
        List<EventResponse> eventResponses = new ArrayList<>();

        for(Event event:events){
            EventResponse eventResponse = EventResponse.of(
                    event.getId(),
                    event.getImage(),
                    event.getDtype(),
                    event.getText(),
                    event.getPlace(),
                    event.getDuration(),
                    event.getUrl(),
                    event.getDetail(),
                    event.getDetail2(),
                    event.getStatus()
            );
            eventResponses.add(eventResponse);
        }
        return eventResponses;
    };


    //category별로 event가져오기
    @Transactional
    public List<EventResponse> getEventByDtype(String dtype ) {
        List<Event> events = eventRepository.findByDtype(dtype);
        List<EventResponse> eventResponses = new ArrayList<>();

        for(Event event:events){
            EventResponse eventResponse = EventResponse.of(
                    event.getId(),
                    event.getImage(),
                    event.getDtype(),
                    event.getText(),
                    event.getPlace(),
                    event.getDuration(),
                    event.getUrl(),
                    event.getDetail(),
                    event.getDetail2(),
                    event.getStatus()
            );
            eventResponses.add(eventResponse);
        }
        return eventResponses;
    };

    //id가지고 event 한개만 가져오는거 혹시 필요할수도 있으니까
    @Transactional
    public EventResponse getEventById(Long id) {
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("이벤트를 찾을 수 없습니다. ID: " + id));

        return EventResponse.of(
                event.getId(),
                event.getImage(),
                event.getDtype(),
                event.getText(),
                event.getPlace(),
                event.getDuration(),
                event.getUrl(),
                event.getDetail(),
                event.getDetail2(),
                event.getStatus()
        );
    }

    //제목으로 event검색해주기
    public List<EventResponse> getEventByText(String text ) {
        List<Event> events = eventRepository.findByText(text);
        List<EventResponse> eventResponses = new ArrayList<>();

        for(Event event:events){
            EventResponse eventResponse = EventResponse.of(
                    event.getId(),
                    event.getImage(),
                    event.getDtype(),
                    event.getText(),
                    event.getPlace(),
                    event.getDuration(),
                    event.getUrl(),
                    event.getDetail(),
                    event.getDetail2(),
                    event.getStatus()
            );
            eventResponses.add(eventResponse);
        }
        return eventResponses;
    };

    //장소로 event검색해주기
//    public List<EventResponse> getEventByPlace(String place ) {
//        List<Event> events = eventRepository.findByPlace(place);
//        List<EventResponse> eventResponses = new ArrayList<>();
//
//        for(Event event:events){
//            EventResponse eventResponse = EventResponse.of(
//                    event.getId(),
//                    event.getImage(),
//                    event.getDtype(),
//                    event.getText(),
//                    event.getPlace(),
//                    event.getDuration(),
//                    event.getUrl(),
//                    event.getDetail(),
//                    event.getDetail2(),
//                    event.getStatus()
//            );
//            eventResponses.add(eventResponse);
//        }
//        return eventResponses;
//    };

    public List<EventResponse> getEventByPlace(String place) {
        List<Event> events = eventRepository.findByPlaceContaining(place); // 'place'가 포함된 항목을 검색
        List<EventResponse> eventResponses = new ArrayList<>();

        if (events != null && !events.isEmpty()) {
            for (Event event : events) {
                EventResponse eventResponse = EventResponse.of(
                        event.getId(),
                        event.getImage(),
                        event.getDtype(),
                        event.getText(),
                        event.getPlace(),
                        event.getDuration(),
                        event.getUrl(),
                        event.getDetail(),
                        event.getDetail2(),
                        event.getStatus()
                );
                eventResponses.add(eventResponse);
            }
        } else {
            EventResponse noEventResponse = new EventResponse();
            noEventResponse.setText("No events found for the specified place: " + place);
            eventResponses.add(noEventResponse);
        }

        return eventResponses;
    }

    public void updateEventStatus() {
        List<Event> events = eventRepository.findAll();

        for (Event event : events) {
            if (isEventExpired(event)) {
                event.setStatus(0);
                eventRepository.save(event);
            }
        }
    }

    private boolean isEventExpired(Event event) {
        String duration = event.getDuration();
        LocalDate endDate;
        if (duration.contains("~")) {
            endDate = LocalDate.parse(duration.split("~")[1], DateTimeFormatter.ofPattern("yyyy.MM.dd"));
        } else {
            endDate = LocalDate.parse(duration, DateTimeFormatter.ofPattern("yyyy.MM.dd"));
        }

        return LocalDate.now().isAfter(endDate);
    }
}
