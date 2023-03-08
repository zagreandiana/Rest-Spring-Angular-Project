package ro.ubb.music.web.dto;

import lombok.*;

import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString

public class AlbumDto extends BaseDto {
    private String title;
    private String genre;
    private Long artist_id;
    private Long band_id;
    private Date release_date;


}
