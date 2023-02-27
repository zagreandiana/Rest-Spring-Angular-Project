package ro.ubb.music.core.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.ubb.catalog.core.exceptions.ServiceException;
import ro.ubb.catalog.core.model.Song;
import ro.ubb.catalog.core.repository.SongRepository;
import ro.ubb.catalog.core.utils.ExceptionMessages;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class SongServiceImpl implements SongService {

    @Autowired
    private SongRepository songRepository;

    @Override
    public Song create(Song entity) {
        log.debug("### Entering create song method.");
        Song song = songRepository.save(entity);
        log.debug("### Exiting create song method.");

        return song;
    }

    @Override
    public Song readOne(Long id) {
        Optional<Song> songOptional = songRepository.findById(id);

        log.debug("### Entering readOne song method.");
        if (songOptional.isEmpty()) {
            throw new ServiceException(ExceptionMessages.ENTITY_WITH_GIVEN_ID_DOES_NOT_EXIST.message);
        }
        Song song = songOptional.get();
        log.debug("### Exiting readOne song method.");

        return song;
    }

    @Override
    public List<Song> readAll() {
        log.debug("### Entering read songs method.");
        List<Song> songs = songRepository.findAll();
        log.debug("### Exiting read songs method.");

        return songs;
    }
    @Override
    @Transactional
    public Song update(Long id, Song song) {
        log.debug("### Entering update song method.");
        Optional<Song> optional = songRepository.findById(id);

        Song songUpdate = optional.orElseThrow(() -> new ServiceException(ExceptionMessages.ENTITY_WITH_GIVEN_ID_DOES_NOT_EXIST.message));

        songUpdate.setTitle(song.getTitle());
        songUpdate.setAlbumId(songUpdate.getAlbumId());
        songUpdate.setTime(song.getTime());
        log.debug("### Exiting update song method.");

        return songUpdate;
    }

    @Override
    public void delete(Long id) {
        log.debug("### Entering delete song method.");
        songRepository.deleteById(id);
        log.debug("### Exiting delete song method.");
    }

    @Override
    public List<Song> sortByLength() {
        log.debug("### Entering sort by length");
        List<Song> songs = songRepository.findByOrderByTimeDesc();
        log.debug("### Exiting sort by length");

        return songs;
    }

    @Override
    public Song findByTitle(String title) {
        return songRepository.findAllByTitle(title);
    }

    @Override
    public List<Song> groupSongsByAlbumId(Integer id) {
        return songRepository.findAllByAlbumId(id);
    }

    @Override
    public List<Song> findByOrderByTimeDesc() {
        return songRepository.findByOrderByTimeDesc();
    }
}
