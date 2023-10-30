//package Sanhak.wakeUp.team.service;
//
//import Sanhak.wakeUp.team.dto.UserInfoUpdateRequest;
//import Sanhak.wakeUp.team.entity.Member;
//import Sanhak.wakeUp.team.entity.UserInfo;
//import Sanhak.wakeUp.team.repository.MemberRepository;
//import Sanhak.wakeUp.team.repository.UserInfoRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.Optional;
//
//@Service
//public class UserInfoService {
//
//    private final UserInfoRepository userInfoRepository;
//    private final MemberRepository memberRepository;
//
//    @Autowired
//    public UserInfoService(UserInfoRepository userInfoRepository, MemberRepository memberRepository) {
//        this.userInfoRepository = userInfoRepository;
//        this.memberRepository = memberRepository;
//    }
//
//    public void saveOrUpdateUserInfo(UserInfoUpdateRequest request) {
//        // 고유 사용자 정보로 Member를 찾습니다.
//        Optional<Member> memberOptional = memberRepository.findByUniqueUserInfo(request.getUniqueUserInfo());
//
//        if (memberOptional.isPresent()) {
//            Member member = memberOptional.get(); // Member가 존재하면 가져옵니다.
//
//            // Member의 UserInfo를 가져옵니다. 없을 경우 생성합니다.
//            UserInfo userInfo = member.getUserInfo();
//            if (userInfo == null) {
//                userInfo = new UserInfo();
//                userInfo.setMember(member);
//            }
//
//            // 나머지 필드를 업데이트합니다.
//            userInfo.setName(request.getName());
//            userInfo.setSex(request.getSex());
//            userInfo.setGenre(request.getGenre());
//            userInfo.setMbti(request.getMbti());
//            userInfo.setUniqueUserInfo(request.getUniqueUserInfo());
//            // UserInfo를 저장합니다.
//            userInfoRepository.save(userInfo);
//
//        } else {
//            // Member가 존재하지 않을 경우 예외 처리 또는 다른 작업을 수행합니다.
//            throw new IllegalArgumentException("해당 고유 사용자 정보에 해당하는 Member가 존재하지 않습니다.");
//        }
//    }
//}