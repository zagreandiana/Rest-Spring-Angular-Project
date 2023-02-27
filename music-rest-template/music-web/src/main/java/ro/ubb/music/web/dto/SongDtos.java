package ro.ubb.music.web.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class SongDtos {
    private Set<SongDto> songDtoSet;
}
