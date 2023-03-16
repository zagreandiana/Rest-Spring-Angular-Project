package ro.ubb.music.core.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.sql.Time;

@Getter
@Setter
@NoArgsConstructor
@Builder
@Entity(name = "songs")
@ToString
public class Song extends BaseEntity<Long> {

    private String title;
    @Column(name="album_id")
    private Integer albumId;
    @Column(name="duration")
    private Time time;

    public Song(Long id, String title, Integer albumId, Time time) {
        super(id);
        this.title = title;
        this.albumId = albumId;
        this.time = time;
    }

    public Song(String title, Integer albumId, Time time) {
        this.title = title;
        this.albumId = albumId;
        this.time = time;
    }
}
