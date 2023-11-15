package Sanhak.wakeUp.team.member.service;

import Sanhak.wakeUp.team.event.entity.Event;
import Sanhak.wakeUp.team.event.repository.EventRepository;
import Sanhak.wakeUp.team.member.entity.Member;
import Sanhak.wakeUp.team.member.entity.Scrap;
import Sanhak.wakeUp.team.member.exception.UserNotFoundException;
import Sanhak.wakeUp.team.member.repository.MemberRepository;
import Sanhak.wakeUp.team.member.repository.ScrapRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class ScrapService {

    private final MemberRepository memberRepository;
    private final ScrapRepository scrapRepository;
    private final EventRepository eventRepository;

    @Autowired
    public ScrapService(MemberRepository memberRepository, ScrapRepository scrapRepository, EventRepository eventRepository) {
        this.memberRepository = memberRepository;
        this.scrapRepository = scrapRepository;
        this.eventRepository = eventRepository;
    }

    public Long findMemberIdByUniqueUserInfo(String uniqueUserInfo) {
        Member member = memberRepository.findByUniqueUserInfo(uniqueUserInfo)
                .orElseThrow(() -> new UserNotFoundException("멤버를 찾을 수 없습니다."));
        return member.getId();
    }

    public void addScrap(String uniqueUserInfo, Long eventId) {
        // unique_user_info를 사용하여 해당 회원의 member_id를 찾음
        Long memberId = findMemberIdByUniqueUserInfo(uniqueUserInfo);

        // event_id로 Event를 찾음
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new EntityNotFoundException("이벤트를 찾을 수 없습니다. ID: " + eventId));

        // Scrap 엔티티 생성 및 저장
        Scrap scrap = new Scrap();
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new UserNotFoundException("멤버를 찾을 수 없습니다."));
        scrap.setMember(member);
        scrap.setEvent(event);

        scrapRepository.save(scrap);
    }
}
