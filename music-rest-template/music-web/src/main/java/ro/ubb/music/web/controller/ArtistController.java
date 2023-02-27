package ro.ubb.music.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.ubb.catalog.core.exceptions.ServiceException;
import ro.ubb.catalog.core.model.Artist;
import ro.ubb.catalog.core.service.ArtistService;
import ro.ubb.catalog.web.converter.ArtistConverter;
import ro.ubb.catalog.web.dto.*;

import java.util.List;
import java.util.Set;


@RestController
@RequestMapping("artists")
public class ArtistController {


    @Autowired
    private ArtistService artistService;
    @Autowired
    private ArtistConverter artistConverter;


    @GetMapping()
    ResponseEntity<ArtistResponse> getArtists() {
        List<Artist> artists = artistService.readAll();
        Set<ArtistDto> dtoSet = artistConverter.convertModelsToDtos(artists);

        ArtistResponse artistResponse = new ArtistResponse();
        artistResponse.setArtists(dtoSet);

        return new ResponseEntity<>(artistResponse, HttpStatus.OK);
    }

    @PostMapping()
    ResponseEntity<ArtistResponse> saveArtist(@RequestBody ArtistDto artistDto) {
        Artist artist = artistConverter.convertDtoToModel(artistDto);

        try {
            Artist savedArtist = artistService.create(artist);
            ArtistDto savedDto = artistConverter.convertModelToDto(savedArtist);

            ArtistResponse artistResponse = new ArtistResponse();
            artistResponse.setArtists(Set.of(savedDto));

            return new ResponseEntity<>(artistResponse, HttpStatus.OK);
        } catch (ServiceException e) {
            ArtistResponse artistResponse = new ArtistResponse();
            artistResponse.setErrorMessage(e.getMessage());

            return new ResponseEntity<>(artistResponse, HttpStatus.OK);
        }
    }

    @PutMapping(value = "/{id}")
    ResponseEntity<ArtistResponse> updateArtist(@PathVariable Long id, @RequestBody ArtistDto artistDto) {
        Artist artist = artistConverter.convertDtoToModel(artistDto);

        try {
            Artist updatedArtist = artistService.update(id, artist);
            ArtistDto updatedDto = artistConverter.convertModelToDto(updatedArtist);

            ArtistResponse artistResponse = new ArtistResponse();
            artistResponse.setArtists(Set.of(updatedDto));

            return new ResponseEntity<>(artistResponse, HttpStatus.OK);
        } catch (ServiceException e) {
            ArtistResponse artistResponse = new ArtistResponse();
            artistResponse.setErrorMessage(e.getMessage());

            return new ResponseEntity<>(artistResponse, HttpStatus.OK);
        }
    }

    @DeleteMapping(value = "/{id}")
    ResponseEntity<ArtistResponse> deleteArtistById(@PathVariable Long id) {
        try {
            Artist deletedArtist = artistService.delete(id);
            ArtistDto deletedDto = artistConverter.convertModelToDto(deletedArtist);

            ArtistResponse artistResponse = new ArtistResponse();
            artistResponse.setArtists(Set.of(deletedDto));

            return new ResponseEntity<>(artistResponse, HttpStatus.OK);
        } catch (ServiceException e) {
            ArtistResponse artistResponse = new ArtistResponse();
            artistResponse.setErrorMessage(e.getMessage());

            return new ResponseEntity<>(artistResponse, HttpStatus.OK);
        }
    }


//    @DeleteMapping(value = "/{id}")
//    ResponseEntity<?> deleteArtistById(@PathVariable Long id) {
//        artistService.delete(id);
//        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//    }


    @GetMapping(value = "/{id}")
    ResponseEntity<ArtistResponse> getArtistById(@PathVariable Long id) {
        try {
            Artist artist = artistService.readOne(id);
            ArtistResponse artistResponse = new ArtistResponse();
            artistResponse.setArtists(Set.of(artistConverter.convertModelToDto(artist)));

            return new ResponseEntity<>(artistResponse, HttpStatus.OK);
        } catch (ServiceException e) {
            ArtistResponse artistResponse = new ArtistResponse();
            artistResponse.setErrorMessage(e.getMessage());

            return new ResponseEntity<>(artistResponse, HttpStatus.OK);
        }

    }

    @GetMapping(value = "/alpha")
    ResponseEntity<ArtistDtos>  getartistDtosAlpha() {
        List<Artist> artistsSorted = artistService.alphabeticalSortByFirstName();
        Set<ArtistDto> dtoSet = artistConverter.convertModelsToDtos(artistsSorted);


        return new ResponseEntity<>(new ArtistDtos(dtoSet), HttpStatus.OK);
    }

    @GetMapping(value = "/date")
    ArtistDtos getartistDtosDate() {
        List<Artist> artists = artistService.sortArtistsByStartDateActivity();
        Set<ArtistDto> dtoSet = artistConverter.convertModelsToDtos(artists);

        return new ArtistDtos(dtoSet);
    }



}
