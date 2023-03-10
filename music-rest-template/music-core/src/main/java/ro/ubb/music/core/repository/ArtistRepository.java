package ro.ubb.music.core.repository;


import ro.ubb.music.core.model.Artist;

import java.util.List;

public interface ArtistRepository extends Repository<Artist, Long>{


    List<Artist> findAllByOrderByActivityStartDateAsc();

    List<Artist> findAllByOrderByFirstNameAsc();


}
