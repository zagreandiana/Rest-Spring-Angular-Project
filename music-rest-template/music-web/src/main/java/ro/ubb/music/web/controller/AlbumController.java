package ro.ubb.music.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.ubb.music.core.exceptions.ServiceException;
import ro.ubb.music.core.model.Album;
import ro.ubb.music.core.service.AlbumService;
import ro.ubb.music.web.converter.AlbumConvertor;
import ro.ubb.music.web.dto.AlbumDto;
import ro.ubb.music.web.dto.AlbumDtos;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("albums")
public class AlbumController {
    @Autowired
    private AlbumService albumService;
    @Autowired
    private AlbumConvertor albumConvertor;

    @GetMapping
    ResponseEntity<AlbumDtos> getAlbums() {
        List<Album> albums = albumService.readAll();
        Set<AlbumDto> albumsDtoSet = albumConvertor.convertModelsToDtos(albums);

        return new ResponseEntity<>(new AlbumDtos(albumsDtoSet), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    ResponseEntity<AlbumDto> getAlbumById(@PathVariable Long id) {
        try {
            Album album = albumService.readOne(id);
            return new ResponseEntity<>(albumConvertor.convertModelToDto(album), HttpStatus.OK);
        } catch (ServiceException e) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    @PostMapping
    ResponseEntity<AlbumDto> saveAlbum(@RequestBody AlbumDto albumDto) {
        Album album = albumConvertor.convertDtoToModel(albumDto);
        Album savedAlbum = albumService.create(album);

        return new ResponseEntity<>(albumConvertor.convertModelToDto(savedAlbum), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<AlbumDto> deleteAlbum(@PathVariable Long id) {
        try {
            Album albumDeleted = albumService.delete(id);
            return new ResponseEntity<>(albumConvertor.convertModelToDto(albumDeleted), HttpStatus.OK);
        } catch (ServiceException e) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    @PutMapping("/{id}")
    ResponseEntity<AlbumDto> updateAlbum(@RequestBody AlbumDto albumDto) {
        Album album = albumConvertor.convertDtoToModel(albumDto);
        try {
            Album updatedAlbum = albumService.update(album);
            return new ResponseEntity<>(albumConvertor.convertModelToDto(updatedAlbum), HttpStatus.OK);
        } catch (ServiceException e) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    @GetMapping(value = "/filter={genre}")
    ResponseEntity<AlbumDtos> filterAlbumsByGenre(@PathVariable String genre) {
        List<Album> albumsByGenre = albumService.filterByGenre(genre);
        Set<AlbumDto> albumDtoSet = albumConvertor.convertModelsToDtos(albumsByGenre);

        return new ResponseEntity<>(new AlbumDtos(albumDtoSet), HttpStatus.OK);
    }

    @GetMapping(value = "/sort={sortValue}")
    ResponseEntity<AlbumDtos> sortAlbums(@PathVariable String sortValue) {
        List<Album> albumsListSorted = albumService.sortAsc(sortValue);
        Set<AlbumDto> albumDtos = albumConvertor.convertModelsToDtos(albumsListSorted);

        return new ResponseEntity<>(new AlbumDtos(albumDtos), HttpStatus.OK);
    }
}
