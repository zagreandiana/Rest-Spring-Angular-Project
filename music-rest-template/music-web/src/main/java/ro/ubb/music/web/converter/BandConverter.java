package ro.ubb.music.web.converter;

import org.springframework.stereotype.Component;
import ro.ubb.music.core.model.Band;
import ro.ubb.music.core.model.User;
import ro.ubb.music.core.utils.UserRoles;
import ro.ubb.music.core.utils.UserStatuses;
import ro.ubb.music.web.dto.BandDto;
import ro.ubb.music.web.dto.UserDto;

@Component
public class BandConverter extends BaseConverter<Band, BandDto> {


    @Override
    public Band convertDtoToModel(BandDto dto) {
        Band band = Band.builder()
                .name(dto.getName())
                .activityStartData(dto.getActivityStartData())
                .activityEndData(dto.getActivityEndData())
                .build();
        band.setId(dto.getId());

        return band;
    }

    @Override
    public BandDto convertModelToDto(Band band) {
        BandDto dto = BandDto.builder()
                .name(band.getName())
                .activityStartData(band.getActivityStartData())
                .activityEndData(band.getActivityEndData())
                .build();
        dto.setId(band.getId());

        return dto;
    }
}
