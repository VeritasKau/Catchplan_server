package Sanhak.wakeUp.team.small_event.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class SmallEventRequest {
    private String text;
    private String place;
    private String duration;
    private String url;
    private String detail2;
    private MultipartFile image;
    private MultipartFile detail;

//    public String getImage() {
//        return null;
//    };
//
//    public String getDetail() {
//        return null;
//    };

    //private MultipartFile image;
    //private MultipartFile detail1;
}
