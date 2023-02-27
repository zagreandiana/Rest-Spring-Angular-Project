package ro.ubb.music.web.dto;


import lombok.*;

import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class ArtistDto extends BaseDto{

    private String firstName;
    private String lastName;
    private String stageName;
    private Date activityStartDate;
    private Date activityEndDate;






}
