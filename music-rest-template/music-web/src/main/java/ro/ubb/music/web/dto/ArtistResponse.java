package ro.ubb.music.web.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ArtistResponse {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Set<ArtistDto> artists;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String errorMessage;


}
