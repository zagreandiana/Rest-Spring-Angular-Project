package ro.ubb.music.web.dto;

import lombok.*;

import java.sql.Time;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString

public class SongDto extends BaseDto {
    private String title;
    private Integer albumId;
    private Time time;


}
