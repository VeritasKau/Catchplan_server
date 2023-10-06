//package Sanhak.wakeUp.team.dto;
//
//import Sanhak.wakeUp.team.entity.User;
//import lombok.Builder;
//import lombok.Getter;
//
//import java.util.Map;
//
//@Getter
//@Builder
//public class OAuthAttributes {
//    private Map<String,Object>attributes;
//    private String nameAttributeKey;
//    private String name;
//    private String email;
//    private String phone;
//
//    public static OAuthAttributes of(String registrationId,String userNameAttributeName.Map<String,Object> attributes){
//        return ofNaver("id",attributes);
//    }
//    private static OAuthAttributes ofNaver(String userNameAttributeName,Map<String,Object> attributes){
//        Map<String ,Object> response=(Map<String,Object>)attributes.get("response");
//        return OAuthAttributes.builder()
//                .name((String) response.get("name"))
//                .email((String) response.get("email"))
//                .phone((String) response.get("phone"))
//                .attributes(response)
//                .nameAttributeKey(userNameAttributeName)
//                .build();
//    }
//
//    public User toEntity(){
//        return User.builder()
//                .name(name)
//                .email(email)
//                .phone(phone)
//                .build();
//    }
//}
