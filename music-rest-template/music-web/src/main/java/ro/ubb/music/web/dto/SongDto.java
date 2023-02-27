package ro.ubb.music.web.dto;

import lombok.*;

import java.sql.Time;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class SongDto extends BaseDto {
    private String title;
    private Integer albumId;
    private Time time;

    @Override
    public String toString() {
        return "SongDto{" +
                "title='" + title + '\'' +
                ", albumId=" + albumId +
                ", time=" + time +
                ", id=" + id +
                '}';
    }
}
