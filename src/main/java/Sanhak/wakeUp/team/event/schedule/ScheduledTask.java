package Sanhak.wakeUp.team.event.schedule;
import Sanhak.wakeUp.team.event.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTask {

    @Autowired
    private EventService smallEventService;

    @Scheduled(cron = "0 0 0 * * *") // 매일 자정에 실행
    public void updateEventStatus() {
        smallEventService.updateEventStatus();
    }
}