package ro.ubb.music.core.service;


import ro.ubb.catalog.core.model.Artist;

import java.util.List;

public interface ArtistService {
    Artist create(Artist artist);

    Artist readOne(Long id);

    List<Artist> readAll();

    Artist update(Long id, Artist artist);

    Artist delete(Long id);

    List<Artist> alphabeticalSortByFirstName();

    List<Artist> sortArtistsByStartDateActivity();
}
