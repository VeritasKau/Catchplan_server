package Sanhak.wakeUp.team.small_event.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SmallEventResponse {
    private Long id;
    private String image;
    private String text;
    private String place;
    private String duration;
    private String url;
    private String detail;
    private String detail2;
    private Boolean status;

    public static SmallEventResponse of(Long id,String image,String text,String place,String duration
            ,String url,String detail,String detail2,Boolean status){
        return SmallEventResponse.builder()
                .id(id)
                .image(image)
                .text(text)
                .place(place)
                .duration(duration)
                .url(url)
                .detail(detail)
                .detail2(detail2)
                .status(status)
                .build();

    }
}
