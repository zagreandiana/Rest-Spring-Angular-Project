package ro.ubb.music.core.repository;

import ro.ubb.catalog.core.model.Song;

import java.util.List;

public interface SongRepository extends Repository<Song, Long> {
    List<Song> findByOrderByTimeDesc();

    Song findAllByTitle(String title);

    List<Song> findAllByAlbumId(Integer id);
}
