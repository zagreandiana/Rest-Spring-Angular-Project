package ro.ubb.music.web.dto;

import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class BandDto extends BaseDto {

    private String name;

    private Date activityStartData;

    private Date activityEndData;

    @Override
    public String toString() {
        return "BandDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", activityStartData=" + activityStartData +
                ", activityEndData=" + activityEndData +
                '}';
    }
}
