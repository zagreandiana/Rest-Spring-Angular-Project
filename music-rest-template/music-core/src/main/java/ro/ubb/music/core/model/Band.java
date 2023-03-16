package ro.ubb.music.core.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Entity(name = "bands")
public class Band extends BaseEntity<Long> {

    private String name;
    @Column(name = "activity_start_date")
    private Date activityStartData;
    @Column(name = "activity_end_date")
    private Date activityEndData;


}
