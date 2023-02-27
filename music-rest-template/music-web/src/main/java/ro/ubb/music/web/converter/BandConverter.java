package ro.ubb.music.web.converter;

import org.springframework.stereotype.Component;
import ro.ubb.catalog.core.model.Band;
import ro.ubb.catalog.core.model.User;
import ro.ubb.catalog.core.utils.UserRoles;
import ro.ubb.catalog.core.utils.UserStatuses;
import ro.ubb.catalog.web.dto.BandDto;
import ro.ubb.catalog.web.dto.UserDto;

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
