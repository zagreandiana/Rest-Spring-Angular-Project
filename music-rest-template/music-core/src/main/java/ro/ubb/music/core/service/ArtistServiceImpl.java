package ro.ubb.music.core.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.ubb.music.core.exceptions.ServiceException;
import ro.ubb.music.core.model.Artist;
import ro.ubb.music.core.model.User;
import ro.ubb.music.core.repository.ArtistRepository;
import ro.ubb.music.core.utils.ExceptionMessages;

import java.util.List;
import java.util.Optional;

@Slf4j
// @Slf4j is used for logs (lombok)

@Service
public class ArtistServiceImpl implements ArtistService {
    @Autowired
    // @Autowired allows Spring to resolve and inject collaborating beans into our bean
    // after enabling annotation injection, we can use autowiring on properties, setters, and constructors

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

        // if an Optional object contains a value => is present
        // if it doesn't contain a value => is empty
        // in this case if an artist is empty I throw my own exception with a specific message

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

    // the transactional annotation itself defines the scope of a single database transaction
    // the database transaction happens inside the scope of a persistence context
    // transaction propagation are handled automatically
    // unfortunately is hard to debug
    // save in db (after update)

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

        // here I used Optional because the artist that I wanna delete
        // may or may not be present
        // thus, just in case if the artist is present I will delete it
        // in addition, calling the "orElseThrow" method helps me manage exceptions

        Artist artistToBeDeleted = optional.orElseThrow(
                () -> new ServiceException(ExceptionMessages.ENTITY_WITH_GIVEN_ID_DOES_NOT_EXIST.message));

        artistRepository.deleteById(artistToBeDeleted.getId());
        log.debug("### Exiting delete artist method.");

        return artistToBeDeleted;

    }


    @Override
    public List<Artist> alphabeticalSortByFirstName() {
        // sorting done by spring without any implementation
        //  based on the name of the called method
            return artistRepository.findAllByOrderByFirstNameAsc();

    }

    @Override
    public List<Artist> sortArtistsByStartDateActivity() {
        // sorting done by spring without any implementation
        // based on the name of the called method

        return artistRepository.findAllByOrderByActivityStartDateAsc();
    }
}
