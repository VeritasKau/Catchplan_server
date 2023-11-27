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
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.*;

@Service
@RequiredArgsConstructor
public class SmallEventService {
    private final SmallEventRepository smallEventRepository;
    private final S3UploadService s3UploadService;


    //small event생성하기
    @Transactional
    public void createSmallEvent(SmallEventRequest smallEventRequest, MultipartFile image,MultipartFile detail) throws IOException {
        // 이미지를 base64로 인코딩
        String base64Image = encodeFileToBase64(image);

        // 이미지를 S3에 업로드하고 경로를 얻어옴
        String imagePath = s3UploadService.saveFile(image);

        // 상세 이미지도 동일하게 처리
        //String base64Detail = (detail1 != null && !detail1.isEmpty()) ? encodeFileToBase64(detail1) : null;

        String detailPath = (detail != null && !detail.isEmpty()) ? s3UploadService.saveFile(detail) : null;

        // 나머지 로직은 변경하지 않음



        SmallEvent newSmallEvent = SmallEvent.builder()
                .image(imagePath)
                .text(smallEventRequest.getText())
                .place(smallEventRequest.getPlace())
                .duration(smallEventRequest.getDuration())
                .url(smallEventRequest.getUrl())
                .detail(detailPath)
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
        List<SmallEvent> smallEvents = smallEventRepository.findAll();
        List<SmallEventResponse> smallEventResponses = new ArrayList<>();

        for (SmallEvent smallEvent : smallEvents) {
            try {
                // Download image and get URL as String
                String imageUrl = Objects.requireNonNull(
                        s3UploadService.downloadImage(smallEvent.getImage()).getBody().getURL().toString()
                );

                // Decode URL
                String decodedUrl = URLDecoder.decode(imageUrl, StandardCharsets.UTF_8.toString());

                // Remove "%25" from URL
                String cleanedUrl = decodedUrl.replace("%25", "");
                String cleanedUrl2= cleanedUrl.replace("https://catchplan.s3.ap-northeast-2.amazonaws.com/https://catchplan.s3.ap-northeast-2.amazonaws.com/", "https://catchplan.s3.ap-northeast-2.amazonaws.com/");
                // Similarly process detail1Url as needed
                String detail1Url = Objects.requireNonNull(s3UploadService.downloadImage(smallEvent.getDetail()).getBody()).getURL().toString();
                String decodedDetailUrl = URLDecoder.decode(detail1Url, StandardCharsets.UTF_8.toString());
                String cleanedDetailUrl = decodedDetailUrl.replace("%25", "");
                String cleanedDetailUrl2= cleanedDetailUrl.replace("https://catchplan.s3.ap-northeast-2.amazonaws.com/https://catchplan.s3.ap-northeast-2.amazonaws.com/", "https://catchplan.s3.ap-northeast-2.amazonaws.com/");


                // Create SmallEventResponse and add to the list
                SmallEventResponse smallEventResponse = SmallEventResponse.of(
                        smallEvent.getId(),
                        cleanedUrl2,
                        smallEvent.getText(),
                        smallEvent.getPlace(),
                        smallEvent.getDuration(),
                        smallEvent.getUrl(),
                        cleanedDetailUrl2,
                        smallEvent.getDetail2(),
                        smallEvent.getStatus()
                );
                smallEventResponses.add(smallEventResponse);

            } catch (Exception e) {
                // Handle exceptions appropriately
                e.printStackTrace();
            }
        }
        return smallEventResponses;
    }

    //id가지고 small_event 한개만 가져오는거 혹시 필요할수도 있으니까
    @Transactional
    public SmallEventResponse getSmallEventById(Long id) {
        SmallEvent smallEvent = smallEventRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("이벤트를 찾을수가 없습니다. Id: " + id));

        return createSmallEventResponse(smallEvent);
    }

    //제목으로 event검색해주기
    @Transactional
    public List<SmallEventResponse> getSmallEventByText(String text) {
        List<SmallEvent> smallEvents = smallEventRepository.findByText(text);
        List<SmallEventResponse> smallEventResponses = new ArrayList<>();

        for (SmallEvent smallEvent : smallEvents) {
            SmallEventResponse smallEventResponse = createSmallEventResponse(smallEvent);
            smallEventResponses.add(smallEventResponse);
        }

        return smallEventResponses;
    }

    private SmallEventResponse createSmallEventResponse(SmallEvent smallEvent) {
        try {
            String imageUrl = getImageUrl(smallEvent.getImage());
            String detail1Url = getImageUrl(smallEvent.getDetail());

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
        } catch (Exception e) {
            // Handle exceptions appropriately
            e.printStackTrace();
            return null; // Or throw a specific exception indicating failure
        }
    }

    private String getImageUrl(String image) throws UnsupportedEncodingException {
        String imageUrl = Objects.requireNonNull(s3UploadService.downloadImage(image).getBody().getURL().toString());
        String decodedUrl = URLDecoder.decode(imageUrl, StandardCharsets.UTF_8.toString());
        String cleanedUrl = decodedUrl.replace("%25", "").replace("https://catchplan.s3.ap-northeast-2.amazonaws.com/https://catchplan.s3.ap-northeast-2.amazonaws.com/", "https://catchplan.s3.ap-northeast-2.amazonaws.com/");
        return cleanedUrl;
    }





    //small event 수정(update)
    //먼가 기존 수정파일을 불러와서 교체를 하고싶은데 나는  우리 LMS처럼 들어가면 원래 내가 쓴정보가 있어서 그정보에서 수정할수있는 그런느낌
    @Transactional
    public SmallEventResponse editSmallEvent(
            SmallEventRequest smallEventRequest,
            Long id,
            MultipartFile image,
            MultipartFile detail
    ) throws IOException {
        SmallEvent smallEvent = smallEventRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("이벤트를 찾을 수 없습니다. Id: " + id));

        // 이미지가 필수로 들어가야 함
        if (image == null || image.isEmpty()) {
            // 이미지가 없으면 예외 처리 또는 다른 로직을 추가할 수 있음
            throw new IllegalArgumentException("이미지는 필수 입력 항목입니다.");
        }

        String newImagePath = s3UploadService.saveFile(image);

        // 디테일이 제공되면 업데이트
        if (detail != null && !detail.isEmpty()) {
            String newDetailPath = s3UploadService.saveFile(detail);
            smallEvent.setDetail(newDetailPath);
        }

        // 나머지 필드 업데이트
        smallEvent.setImage(newImagePath);
        smallEvent.setText(smallEventRequest.getText());
        smallEvent.setPlace(smallEventRequest.getPlace());
        smallEvent.setDuration(smallEventRequest.getDuration());
        smallEvent.setUrl(smallEventRequest.getUrl());
        smallEvent.setDetail2(smallEventRequest.getDetail2());

        // 업데이트된 smallEvent 저장
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

        String imageUrl = s3UploadService.downloadImage(smallEvent.getImage()).getBody().getURL().toString();
        String detail1Url = null;

        if (smallEvent.getDetail() != null) {
            detail1Url = s3UploadService.downloadImage(smallEvent.getDetail()).getBody().getURL().toString();
            s3UploadService.deleteImage(smallEvent.getDetail());
        }

        s3UploadService.deleteImage(smallEvent.getImage());

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