package ro.ubb.music.web.converter;

import org.springframework.stereotype.Component;
import ro.ubb.catalog.core.model.Album;
import ro.ubb.catalog.web.dto.AlbumDto;

@Component
public class AlbumConvertor extends BaseConverter<Album, AlbumDto> {

    @Override
    public Album convertDtoToModel(AlbumDto dto) {
        Album album = Album.builder()
                .title(dto.getTitle())
                .genre(dto.getGenre())
                .artist_id(dto.getArtist_id())
                .band_id(dto.getBand_id())
                .release_date(dto.getRelease_date())
                .build();
        album.setId(dto.getId());
        return album;
    }

    @Override
    public AlbumDto convertModelToDto(Album album) {
        AlbumDto albumDto = AlbumDto.builder()
                .title(album.getTitle())
                .genre(album.getGenre())
                .artist_id(album.getArtist_id())
                .band_id(album.getBand_id())
                .release_date(album.getRelease_date())
                .build();
        albumDto.setId(album.getId());
        return albumDto;
    }
}
