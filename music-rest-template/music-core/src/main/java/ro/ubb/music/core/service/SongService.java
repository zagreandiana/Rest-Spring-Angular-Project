package ro.ubb.music.core.service;


import ro.ubb.catalog.core.model.Song;

import java.util.List;

public interface SongService {
    Song create(Song song);

    Song readOne(Long id);

    List<Song> readAll();

    Song update(Long id, Song song);

    void delete(Long id);

    List<Song> sortByLength();

    Song findByTitle(String title);

    List<Song> groupSongsByAlbumId(Integer id);
    List<Song> findByOrderByTimeDesc();

}
