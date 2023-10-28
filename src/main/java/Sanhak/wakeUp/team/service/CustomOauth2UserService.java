package Sanhak.wakeUp.team.service;//package Sanhak.wakeUp.team.service;
//
//import Sanhak.wakeUp.team.dto.OAuthAttributes;
//import Sanhak.wakeUp.team.entity.Users;
//import Sanhak.wakeUp.team.repository.UserRepository;
//import jakarta.servlet.http.HttpSession;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
//import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
//import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
//import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
//import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
//import org.springframework.security.oauth2.core.user.OAuth2User;
//import org.springframework.stereotype.Service;
//
//import java.util.Collections;
//
//@Service
//public class CustomOauth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {
//    private final UserRepository userRepository;
//    private final HttpSession httpSession;
//
//    public CustomOauth2UserService(UserRepository userRepository,HttpSession httpSession){
//        this.userRepository=userRepository;
//        this.httpSession=httpSession;
//    }
//
//    @Override
//    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException{
//        OAuth2UserService<OAuth2UserRequest,OAuth2User>delegate =new DefaultOAuth2UserService();
//        OAuth2User oAuth2User = delegate.loadUser(userRequest);
//
//        String registrationId =userRequest.getClientRegistration().getRegistrationId();
//        String userNameAttributeName=userRequest.getClientRegistration().getProviderDetails().getUserInfoEndpoint().getUserNameAttributeName();
//
//        OAuthAttributes attributes=OAuthAttributes.of(registrationId,userNameAttributeName,oAuth2User.getAttributes());
//
//        Users user=saveOrUpdate(attributes);
//        httpSession.setAttribute("user",new SessionUser(user));
//
//        return new DefaultOAuth2User(
//                Collections.singletonList(new SimpleGrantedAuthority(user.getRoleKey())),
//                attributes.getAttributes(),
//                attributes.getNameAttributeKey()
//        );
//    }
//    private  Users saveOrUpdate(OAuthAttributesAttributes attributes){
//        Users user=userRepository.findByemail(attributes.getEmail())
//                .map(entity -> entity.update(attributes.getName()))
//                .orElse(attributes.toEntity());
//        return userRepository.save(user);
//    }
//}
