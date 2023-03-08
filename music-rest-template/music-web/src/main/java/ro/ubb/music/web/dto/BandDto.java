package ro.ubb.music.web.dto;

import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString

public class BandDto extends BaseDto {

    private String name;

    private Date activityStartData;

    private Date activityEndData;


}
