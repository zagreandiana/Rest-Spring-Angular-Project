package ro.ubb.music.web.converter;

import org.springframework.stereotype.Component;
import ro.ubb.music.core.model.Artist;
import ro.ubb.music.core.model.User;
import ro.ubb.music.web.dto.ArtistDto;
import ro.ubb.music.web.dto.UserDto;

@Component
public class ArtistConverter  extends BaseConverter<Artist, ArtistDto>{
    @Override
    public Artist convertDtoToModel(ArtistDto dto) {
        Artist artist = Artist.builder()
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .stageName(dto.getStageName())
                .activityStartDate(dto.getActivityStartDate())
                .activityEndDate(dto.getActivityEndDate())
                .build();
        artist.setId(dto.getId());

        return artist;
    }

    @Override
    public ArtistDto convertModelToDto(Artist artist) {
        ArtistDto dto = ArtistDto.builder()
                .firstName(artist.getFirstName())
                .lastName(artist.getLastName())
                .stageName(artist.getStageName())
                .activityStartDate(artist.getActivityStartDate())
                .activityEndDate(artist.getActivityEndDate())
                .build();
        dto.setId(artist.getId());
        return dto;
    }
}
