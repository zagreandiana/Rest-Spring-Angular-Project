package ro.ubb.music.core.model;

import lombok.*;

import javax.persistence.Entity;
import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Entity(name = "albums")
public class Album extends BaseEntity<Long> {

    private String title;
    private String genre;
    private Long artist_id;
    private Long band_id;
    private Date release_date;


}
