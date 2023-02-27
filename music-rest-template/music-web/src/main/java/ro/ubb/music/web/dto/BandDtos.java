package ro.ubb.music.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class BandDtos {
    private Set<BandDto> bandDtos;
}
