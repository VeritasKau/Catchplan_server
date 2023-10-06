package Sanhak.wakeUp.team.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
public class KakaoService {

    public User getUserInfo(String accessToken){
        log.info(accessToken);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        headers.add("Authorization", "Bearer " + accessToken);

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(params, headers);
        RestTemplate rt = new RestTemplate();

        ResponseEntity<String> response = rt.exchange(
                "https://kapi.kakao.com/v2/user/me", //{요청할 서버 주소}
                HttpMethod.POST, //{요청할 방식}
                entity,
                String.class
        );

        log.info("res: {}", response);
        return null;

    }
}
