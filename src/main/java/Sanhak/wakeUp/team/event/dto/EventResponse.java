package Sanhak.wakeUp.team.event.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EventResponse {
    private Long id;
    private String image;
    private String dtype;
    private String text;
    private String place;
    private String duration;
    private String url;
    private String detail;
    private String detail2;
    private Boolean status;

    public static EventResponse of(Long id,String image,String dtype,String text,String place,String duration,String url,String detail
    ,String detail2,Boolean status){
        return EventResponse.builder()
                .id(id)
                .image(image)
                .dtype(dtype)
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
