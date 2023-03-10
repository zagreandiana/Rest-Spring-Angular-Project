package ro.ubb.music.core.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.ubb.music.core.exceptions.RepositoryException;
import ro.ubb.music.core.model.Album;
import ro.ubb.music.core.repository.AlbumRepository;
import ro.ubb.music.core.utils.ExceptionMessages;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class AlbumServiceImpl implements AlbumService {
    @Autowired
    private AlbumRepository albumRepository;

    @Override
    public Album create(Album album) {
        log.debug("### Entering create album method.");
        Album albumToBeSaved = albumRepository.save(album);
        log.debug("### Exiting create album method.");
        return albumToBeSaved;
    }

    @Override
    public Album readOne(Long id) {
        log.debug("### Entering readOne album method.");
        Optional<Album> album = albumRepository.findById(id);
        if (album.isEmpty()) {
            throw new RepositoryException(ExceptionMessages.ENTITY_WITH_GIVEN_ID_DOES_NOT_EXIST.message);
        }
        Album albumReturn = album.get();
        log.debug("### Exiting readOne album method.");
        return albumReturn;
    }

    @Override
    public List<Album> readAll() {
        log.debug("### Entering readAll albums method.");
        List<Album> albumList = albumRepository.findAll();
        log.debug("### Exiting readAll albums method.");
        return albumList;
    }

    @Override
    @Transactional
    public Album update(Album album) {
        log.debug("### Entering update album method.");
        Optional<Album> albumOptional = albumRepository.findById(album.getId());
        Album albumToBeUpdated = albumOptional.orElseThrow(() -> new RepositoryException(ExceptionMessages.ENTITY_WITH_GIVEN_ID_ALREADY_EXISTS.message));

        albumToBeUpdated.setTitle(album.getTitle());
        albumToBeUpdated.setGenre(album.getGenre());
        albumToBeUpdated.setArtist_id(album.getArtist_id());
        albumToBeUpdated.setBand_id(album.getBand_id());
        albumToBeUpdated.setRelease_date(album.getRelease_date());
        log.debug("### Exiting update album method.");

        return albumToBeUpdated;
    }

    @Transactional
    @Override
    public Album delete(Long id) {
        log.debug("### Entering delete album method.");
        Optional<Album> albumOptional = albumRepository.findById(id);
        Album albumToBeDeleted = albumOptional.orElseThrow(() -> new RepositoryException(ExceptionMessages.ENTITY_WITH_GIVEN_ID_DOES_NOT_EXIST.message));

        albumToBeDeleted.setArtist_id(null);
        albumToBeDeleted.setBand_id(null);
        albumRepository.prepareForDelete(id);

        albumRepository.deleteById(id);
        log.debug("### Exiting delete album method.");

        return albumToBeDeleted;
    }

    @Override
    public List<Album> filterByGenre(String genre) {
        return albumRepository.findAllByGenre(genre);
    }

    @Override
    public List<Album> sortAsc(String sortValue) {
        return sortValue.equals("z") ?
                albumRepository.findAllByOrderByTitleDesc() :
                albumRepository.findAllByOrderByTitleAsc();
    }
}
