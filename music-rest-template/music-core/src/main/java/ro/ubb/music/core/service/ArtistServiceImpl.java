package ro.ubb.music.core.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.ubb.catalog.core.exceptions.ServiceException;
import ro.ubb.catalog.core.model.Artist;
import ro.ubb.catalog.core.model.User;
import ro.ubb.catalog.core.repository.ArtistRepository;
import ro.ubb.catalog.core.utils.ExceptionMessages;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class ArtistServiceImpl implements ArtistService {
    @Autowired
    private ArtistRepository artistRepository;
    @Override
    public Artist create(Artist artist) {
        log.debug("### Entering create artist method.");
        Artist savedArtist= artistRepository.save(artist);
        log.debug("### Exiting create artist method.");

        return savedArtist;

    }

    @Override
    public Artist readOne(Long id) {
        log.debug("### Entering read artist method.");
        Optional<Artist> optional = artistRepository.findById(id);

        if (optional.isEmpty()) {
            throw new ServiceException(ExceptionMessages.ENTITY_WITH_GIVEN_ID_DOES_NOT_EXIST.message);
        }

        Artist artist = optional.get();
        log.debug("### Exiting read artist method.");

        return artist;
    }

    @Override
    public List<Artist> readAll() {
        log.debug("### Entering read artists method.");
        List<Artist> artists = artistRepository.findAll();
        log.debug("### Exiting read artists method.");

        return artists;
    }

    @Override
    @Transactional
    public Artist update(Long id, Artist artist) {
        log.debug("### Entering update artist method.");
        Optional<Artist> optional = artistRepository.findById(id);

        Artist artistToBeUpdated = optional.orElseThrow(() -> new ServiceException(ExceptionMessages.ENTITY_WITH_GIVEN_ID_DOES_NOT_EXIST.message));

        artistToBeUpdated.setFirstName(artist.getFirstName());
        artistToBeUpdated.setLastName(artist.getLastName());
        artistToBeUpdated.setStageName(artist.getStageName());
        artistToBeUpdated.setActivityStartDate(artist.getActivityStartDate());
        artistToBeUpdated.setActivityEndDate(artist.getActivityEndDate());

        log.debug("### Exiting update artist method.");

        return artistToBeUpdated;
    }

    @Override
    public Artist delete(Long id) {
        log.debug("### Entering delete artist method.");
        Optional<Artist> optional = artistRepository.findById(id);

        Artist artistToBeDeleted = optional.orElseThrow(
                () -> new ServiceException(ExceptionMessages.ENTITY_WITH_GIVEN_ID_DOES_NOT_EXIST.message));

        artistRepository.deleteById(artistToBeDeleted.getId());
        log.debug("### Exiting delete artist method.");

        return artistToBeDeleted;

    }


    @Override
    public List<Artist> alphabeticalSortByFirstName() {

            return artistRepository.findAllByOrderByFirstNameAsc();

    }

    @Override
    public List<Artist> sortArtistsByStartDateActivity() {
        return artistRepository.findAllByOrderByActivityStartDateAsc();
    }
}
