package ro.ubb.music.web.dto;

import lombok.*;

import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class AlbumDto extends BaseDto {
    private String title;
    private String genre;
    private Long artist_id;
    private Long band_id;
    private Date release_date;

    @Override
    public String toString() {
        return "Albums{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", genre=" + genre +
                ", artist_id=" + artist_id +
                ", band_id=" + band_id +
                ", release_date=" + release_date +
                '}';
    }
}
