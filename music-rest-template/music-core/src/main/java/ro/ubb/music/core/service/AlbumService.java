package ro.ubb.music.core.service;


import ro.ubb.music.core.model.Album;

import java.util.List;

public interface AlbumService {

    Album create(Album album);

    Album readOne(Long id);

    List<Album> readAll();

    Album update(Album album);

    Album delete(Long id);

    List<Album> filterByGenre(String genre);

    List<Album> sortAsc(String sortValue);
}
