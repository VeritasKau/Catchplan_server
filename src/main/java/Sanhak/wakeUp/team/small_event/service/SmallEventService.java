package Sanhak.wakeUp.team.small_event.service;

import Sanhak.wakeUp.team.small_event.dto.SmallEventRequest;
import Sanhak.wakeUp.team.small_event.dto.SmallEventResponse;
import Sanhak.wakeUp.team.small_event.entity.SmallEvent;
import Sanhak.wakeUp.team.small_event.repository.SmallEventRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.Base64Util;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;

@Service
@RequiredArgsConstructor
public class SmallEventService {
    private final SmallEventRepository smallEventRepository;
    private final S3UploadService s3UploadService;


    //small event생성하기
    @Transactional
    public void createSmallEvent(SmallEventRequest smallEventRequest, MultipartFile image, MultipartFile detail1) throws IOException {
        // 이미지를 base64로 인코딩

        Long base64Image = Long.valueOf(encodeFileToBase64(image));

        // 이미지를 S3에 업로드하고 경로를 얻어옴
        //Long imagePath = s3UploadService.saveFile(image);

        // 상세 이미지도 동일하게 처리
        Long base64Detail = (detail1 != null && !detail1.isEmpty()) ? Long.valueOf(encodeFileToBase64(detail1)) : null;
        String detailPath = (detail1 != null && !detail1.isEmpty()) ? s3UploadService.saveFile(detail1) : null;

        // 나머지 로직은 변경하지 않음

        Long duration;
        try {
            duration = Long.parseLong(String.valueOf(smallEventRequest.getDuration()));
        } catch (NumberFormatException e) {
            System.out.println("여기에러");
            duration = null; // or set a default value, depending on your requirements
        }

        SmallEvent newSmallEvent = SmallEvent.builder()
                .image(base64Image)
                .text(smallEventRequest.getText())
                .place(smallEventRequest.getPlace())
                .duration(duration)
                .url(smallEventRequest.getUrl())
                .detail(base64Detail)
                .detail2(smallEventRequest.getDetail2())
                .status(true)
                .build();

        smallEventRepository.save(newSmallEvent);
    }
    private String encodeFileToBase64(MultipartFile file) throws IOException {
        byte[] bytes = file.getBytes();
        byte[] encodedBytes = Base64Util.encode(Arrays.toString(bytes)).getBytes();
        return new String(encodedBytes, StandardCharsets.UTF_8);
    }



    //모든 smallevent 가져오기
    @Transactional
    public List<SmallEventResponse> getAllSmallEvents() {
        List<SmallEvent> smallevents = smallEventRepository.findAll();
        List<SmallEventResponse> smallEventResponses = new ArrayList<>();

        for (SmallEvent smallEvent : smallevents) {
            Long imageUrl = Long.valueOf(Objects.requireNonNull(s3UploadService.downloadImage(String.valueOf(smallEvent.getImage())).getBody()).getURL().toString());
            Long detail1Url = Long.valueOf(Objects.requireNonNull(s3UploadService.downloadImage(String.valueOf(smallEvent.getDetail())).getBody()).getURL().toString());

            SmallEventResponse smallEventResponse = SmallEventResponse.of(
                    smallEvent.getId(),
                    imageUrl,
                    smallEvent.getText(),
                    smallEvent.getPlace(),
                    smallEvent.getDuration(),
                    smallEvent.getUrl(),
                    detail1Url,
                    smallEvent.getDetail2(),
                    smallEvent.getStatus()
            );
            smallEventResponses.add(smallEventResponse);
        }
        return smallEventResponses;
    }

    //id가지고 small_event 한개만 가져오는거 혹시 필요할수도 있으니까
    @Transactional
    public SmallEventResponse getSmallEventById(Long id) {
        SmallEvent smallEvent = smallEventRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("이벤트를 찾을수가 없습니다. Id: " + id));

        // Move the lines below to after obtaining a non-null SmallEvent instance
        Long imageUrl = Long.valueOf(Objects.requireNonNull(s3UploadService.downloadImage(String.valueOf(smallEvent.getImage())).getBody()).getURL().toString());
        Long detail1Url = Long.valueOf(Objects.requireNonNull(s3UploadService.downloadImage(String.valueOf(smallEvent.getDetail())).getBody()).getURL().toString());

        return SmallEventResponse.of(
                smallEvent.getId(),
                imageUrl,
                smallEvent.getText(),
                smallEvent.getPlace(),
                smallEvent.getDuration(),
                smallEvent.getUrl(),
                detail1Url,
                smallEvent.getDetail2(),
                smallEvent.getStatus()
        );
    }


    //제목으로 event검색해주기
    @Transactional
    public List<SmallEventResponse> getSmallEventByText(String text) {
        List<SmallEvent> smallEvents = smallEventRepository.findByText(text);
        List<SmallEventResponse> smallEventResponses = new ArrayList<>();

        for (SmallEvent smallEvent : smallEvents) {
            Long imageUrl = Long.valueOf(Objects.requireNonNull(s3UploadService.downloadImage(String.valueOf(smallEvent.getImage())).getBody()).getURL().toString());
            Long detail1Url = Long.valueOf(Objects.requireNonNull(s3UploadService.downloadImage(String.valueOf(smallEvent.getDetail())).getBody()).getURL().toString());
            SmallEventResponse smallEventResponse = SmallEventResponse.of(
                    smallEvent.getId(),
                    imageUrl,
                    smallEvent.getText(),
                    smallEvent.getPlace(),
                    smallEvent.getDuration(),
                    smallEvent.getUrl(),
                    detail1Url,
                    smallEvent.getDetail2(),
                    smallEvent.getStatus()
            );
            smallEventResponses.add(smallEventResponse);
        }
        return smallEventResponses;
    }


    //small event 수정(update)
    //먼가 기존 수정파일을 불러와서 교체를 하고싶은데 나는  우리 LMS처럼 들어가면 원래 내가 쓴정보가 있어서 그정보에서 수정할수있는 그런느낌
    @Transactional
    public SmallEventResponse editSmallEvent(SmallEventRequest smallEventRequest, Long id, MultipartFile image, MultipartFile detail1) throws IOException {
        SmallEvent smallEvent = smallEventRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("이벤트를 찾을 수 없습니다. Id: " + id));


        //String newImagePath = s3UploadService.saveFile(image);
        //String newDetailPath = s3UploadService.saveFile(detail1);

        //smallEvent.setImage(image);
        smallEvent.setImage(Long.valueOf(image.getOriginalFilename()));
        smallEvent.setText(smallEventRequest.getText());
        smallEvent.setPlace(smallEventRequest.getPlace());
        smallEvent.setDuration(smallEventRequest.getDuration());
        smallEvent.setUrl(smallEventRequest.getUrl());
        smallEvent.setDetail(Long.valueOf(detail1.getOriginalFilename()));
        smallEvent.setDetail2(smallEventRequest.getDetail2());

        // Save the updated smallEvent
        smallEventRepository.save(smallEvent);

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
    public SmallEventResponse deleteSmallEvent(Long id) {
        SmallEvent smallEvent = smallEventRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("사용자를 찾을 수 없습니다.Id: " + id));

        Long imageUrl = Long.valueOf(s3UploadService.downloadImage(String.valueOf(smallEvent.getImage())).getBody().getURL().toString());
        Long detail1Url = Long.valueOf(s3UploadService.downloadImage(String.valueOf(smallEvent.getDetail())).getBody().getURL().toString());

        s3UploadService.deleteImage(String.valueOf(Long.valueOf(smallEvent.getImage())));
        s3UploadService.deleteImage(String.valueOf(smallEvent.getDetail()));

        smallEventRepository.delete(smallEvent);
        return SmallEventResponse.of(
                smallEvent.getId(),
                imageUrl,
                smallEvent.getText(),
                smallEvent.getPlace(),
                smallEvent.getDuration(),
                smallEvent.getUrl(),
                detail1Url,
                smallEvent.getDetail2(),
                smallEvent.getStatus()
        );
    }

}
