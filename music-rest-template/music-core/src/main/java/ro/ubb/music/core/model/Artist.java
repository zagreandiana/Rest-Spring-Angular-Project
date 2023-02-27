package ro.ubb.music.core.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "artists")
public class Artist extends BaseEntity<Long> {
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "stage_name")
    private String stageName;
    @Column(name = "activity_start_date")
    private Date activityStartDate;
    @Column(name = "activity_end_date")
    private Date activityEndDate;


    @Override
    public String toString() {
        return "Artist{" +
                "id=" + id +
                ",first_name='" + firstName + '\'' +
                ", last_name='" + lastName + '\'' +
                ", stage_name='" + stageName + '\'' +
                ", activity_start_date=" + activityStartDate +
                ", activity_end_date=" + activityEndDate +
                '}';
    }
}
