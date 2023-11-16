package Sanhak.wakeUp.team.member.service;

import Sanhak.wakeUp.team.event.entity.Event;
import Sanhak.wakeUp.team.event.repository.EventRepository;
import Sanhak.wakeUp.team.member.dto.ScrapInfoResponse;
import Sanhak.wakeUp.team.member.entity.Member;
import Sanhak.wakeUp.team.member.entity.Scrap;
import Sanhak.wakeUp.team.member.exception.DuplicateScrapException;
import Sanhak.wakeUp.team.member.exception.ScrapNotFoundException;
import Sanhak.wakeUp.team.member.exception.UserNotFoundException;
import Sanhak.wakeUp.team.member.repository.MemberRepository;
import Sanhak.wakeUp.team.member.repository.ScrapRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
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

    public void addScrap(String uniqueUserInfo, Long eventId) throws DuplicateScrapException {
        try {
            // unique_user_info를 사용하여 해당 회원의 member_id를 찾음
            Long memberId = findMemberIdByUniqueUserInfo(uniqueUserInfo);

            // event_id로 Event를 찾음
            Event event = eventRepository.findById(eventId)
                    .orElseThrow(() -> new EntityNotFoundException("이벤트를 찾을 수 없습니다. ID: " + eventId));

            // memberId와 eventId가 같은 Scrap이 이미 존재하는지 확인
            if (scrapRepository.existsByMemberIdAndEventId(memberId, eventId)) {
                throw new DuplicateScrapException("이미 스크랩된 이벤트입니다.");
            }
            // Scrap 엔티티 생성 및 저장
            Scrap scrap = new Scrap();
            Member member = memberRepository.findById(memberId)
                    .orElseThrow(() -> new UserNotFoundException("멤버를 찾을 수 없습니다."));
            scrap.setMember(member);
            scrap.setEvent(event);

            scrapRepository.save(scrap);
        } catch (Exception e) {
            // 필요한 경우 다른 예외를 처리하고
            throw new RuntimeException("스크랩 추가 실패", e);
        }
    }


    @Transactional
    public ScrapInfoResponse getScrapInfo(Long scrapId) {
        Scrap scrap = scrapRepository.findById(scrapId)
                .orElseThrow(() -> new ScrapNotFoundException("Scrap not found with id: " + scrapId));

        Member member = scrap.getMember();
        Event event = scrap.getEvent();

        return ScrapInfoResponse.builder()
                .uniqueUserInfo(member.getUniqueUserInfo())
                .genre1(member.getGenre1())
                .genre2(member.getGenre2())
                .genre3(member.getGenre3())
                .mbti(member.getMbti())
                .dtype(event.getDtype())
                .text(event.getText())
                .place(event.getPlace())
                .duration(event.getDuration())
                .url(event.getUrl())
                .detail(event.getDetail())
                .detail2(event.getDetail2())
                .build();
    }
}
