package ro.ubb.music.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.ubb.catalog.core.exceptions.ServiceException;
import ro.ubb.catalog.core.model.Song;
import ro.ubb.catalog.core.service.SongService;
import ro.ubb.catalog.web.converter.SongConverter;
import ro.ubb.catalog.web.dto.SongDto;
import ro.ubb.catalog.web.dto.SongDtos;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("songs")
public class SongController {
    @Autowired
    private SongService songService;
    @Autowired
    private SongConverter songConverter;

    //@GetMapping(value = "/songs")
    @GetMapping()
    ResponseEntity<SongDtos> getSongs() {
        List<Song> songs = songService.readAll();
        Set<SongDto> dtoSet = songConverter.convertModelsToDtos(songs);

        return new ResponseEntity<>(new SongDtos(dtoSet), HttpStatus.OK);
    }

    @PutMapping(value = "/{id}")
    ResponseEntity<SongDtos> updateSong(@PathVariable Long id, @RequestBody SongDto songDto) {
        Song song = songConverter.convertDtoToModel(songDto);
        try {
            Song updatedSong = songService.update(id, song);
            SongDto songDtoUpdate = songConverter.convertModelToDto(updatedSong);

            SongDtos songDtos = new SongDtos();
            songDtos.setSongDtoSet(Set.of(songDtoUpdate));

            return new ResponseEntity<>(songDtos, HttpStatus.OK);
        } catch (ServiceException e) {
            SongDtos songDtos = new SongDtos();

            return new ResponseEntity<>(songDtos, HttpStatus.OK);
        }
    }
/*    @PostMapping()
    ResponseEntity<SongDtos> saveSong(@RequestBody SongDto songDto) {
        Song song = songConverter.convertDtoToModel(songDto);
        try {
            Song savedSong = songService.create(song);
            SongDto savedDto = songConverter.convertModelToDto(savedSong);
            SongDtos songResponse = new SongDtos();
            songResponse.setSongDtoSet(Set.of(savedDto));

            return new ResponseEntity<>(songResponse, HttpStatus.OK);
        } catch (ServiceException e) {
            SongDtos songResponse = new SongDtos();
            return new ResponseEntity<>(songResponse, HttpStatus.OK);
        }
    }*/

    @PostMapping()
    SongDto saveSong(@RequestBody SongDto songDto) {
        Song song = songConverter.convertDtoToModel(songDto);
        songService.create(song);
        return songConverter.convertModelToDto(song);
    }

    @DeleteMapping(value = "/{id}")
    ResponseEntity<?> deleteSongById(@PathVariable Long id) {
        songService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(value = "/{id}")
    ResponseEntity<SongDtos> getSongById(@PathVariable Long id) {
        try {
            Song song = songService.readOne(id);
            SongDtos songDtos = new SongDtos();
            songDtos.setSongDtoSet(Set.of(songConverter.convertModelToDto(song)));

            return new ResponseEntity<>(songDtos, HttpStatus.OK);
        } catch (ServiceException e) {
            SongDtos songDtos = new SongDtos();

            return new ResponseEntity<>(songDtos, HttpStatus.OK);
        }
    }

    @GetMapping(value = "/title={title}")
    ResponseEntity<SongDto> findSongsByTitle(@PathVariable String title) {
        Song songByTitle = songService.findByTitle(title);
        SongDto songDto = songConverter.convertModelToDto(songByTitle);

        return new ResponseEntity<>(songDto, HttpStatus.OK);
    }

    @GetMapping(value = "/id={id}")
    ResponseEntity<SongDtos> groupSongsByAlbumId(@PathVariable Integer id) {
        List<Song> songs = songService.groupSongsByAlbumId(id);
        Set<SongDto> songSet = songConverter.convertModelsToDtos(songs);

        return new ResponseEntity<>(new SongDtos(songSet), HttpStatus.OK);
    }

    @GetMapping(value = "/sort")
    ResponseEntity<SongDtos> orderSongsByDuration() {
        List<Song> songs = songService.findByOrderByTimeDesc();
        Set<SongDto> songDtos = songConverter.convertModelsToDtos(songs);

        return new ResponseEntity<>(new SongDtos(songDtos), HttpStatus.OK);
    }
}
