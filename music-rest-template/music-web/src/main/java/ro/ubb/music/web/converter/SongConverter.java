package ro.ubb.music.web.converter;

import org.springframework.stereotype.Component;
import ro.ubb.music.core.model.Song;
import ro.ubb.music.web.dto.SongDto;

@Component
public class SongConverter extends BaseConverter<Song, SongDto> {
    @Override
    public Song convertDtoToModel(SongDto dto) {
        Song song = Song.builder()
                .title(dto.getTitle())
                .albumId(dto.getAlbumId())
                .time(dto.getTime())
                .build();
        song.setId(dto.getId());

        return song;
    }

    @Override
    public SongDto convertModelToDto(Song song) {
        SongDto dto = SongDto.builder()
                .title(song.getTitle())
                .albumId(song.getAlbumId())
                .time(song.getTime())
                .build();
        dto.setId(song.getId());

        return dto;
    }
}
