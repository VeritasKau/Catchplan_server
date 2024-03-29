
package Sanhak.wakeUp.team.member.service;

import Sanhak.wakeUp.team.member.entity.Member;
import Sanhak.wakeUp.team.member.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
@Service
public class MemberService {
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
    public Member findOrCreateMemberByUniqueUserInfo(String uniqueUserInfo) {
        Member existingMember = memberRepository.findByUniqueUserInfo(uniqueUserInfo).orElse(null);
        if (existingMember == null) {
            Member newMember = new Member();
            newMember.setUniqueUserInfo(uniqueUserInfo);
            return memberRepository.save(newMember);
        }
        return existingMember;
    }
    public boolean isDuplicateUser(String uniqueUserInfo) {
        Optional<Member> existingMember = memberRepository.findByUniqueUserInfo(uniqueUserInfo);

        return existingMember.isPresent();
    }

    public Member findByUniqueUserInfo(String uniqueUserInfo) {
        Optional<Member> existingMember = memberRepository.findByUniqueUserInfo(uniqueUserInfo);
        return existingMember.orElse(null);
    }

    public void deleteMember(Long memberId) {
        memberRepository.deleteById(memberId);
    }

}
