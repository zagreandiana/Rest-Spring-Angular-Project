package ro.ubb.music.core.service;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.ubb.music.core.exceptions.ServiceException;
import ro.ubb.music.core.model.Band;
import ro.ubb.music.core.model.User;
import ro.ubb.music.core.repository.BandRepository;
import ro.ubb.music.core.utils.ExceptionMessages;


import java.beans.Transient;
import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Service
public class BandServiceImpl implements BandService {

    @Autowired
    private BandRepository bandRepository;

    @Override
    public Band create(Band band) {
        log.debug("### Entering create band method.");

        try {
            Band savedBand = bandRepository.save(band);
            log.debug("### Exiting create band method.");

            return savedBand;
        } catch (DataIntegrityViolationException e) {
            e.printStackTrace();
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public Band readOne(Long id) {
        log.debug("### Entering read band method.");
        Optional<Band> optional = bandRepository.findById(id);


        Band band = optional.orElseThrow(
                () -> new ServiceException(ExceptionMessages.ENTITY_WITH_GIVEN_ID_DOES_NOT_EXIST.message));
        log.debug("### Exiting read band method.");

        return band;
    }

    @Override
    public List<Band> readAll() {
        log.debug("### Entering read bands method.");
        List<Band> bands = bandRepository.findAll();
        log.debug("### Exiting read bands method.");

        return bands;
    }

    @Override
    @Transactional
    public Band update(Long id, Band band) {
        log.debug("### Entering update band method.");
        Optional<Band> optional = bandRepository.findById(id);

        Band bandToBeUpdated = optional.orElseThrow(
                () -> new ServiceException(ExceptionMessages.ENTITY_WITH_GIVEN_ID_DOES_NOT_EXIST.message));

        bandToBeUpdated.setName(band.getName());
        bandToBeUpdated.setActivityStartData(band.getActivityStartData());
        bandToBeUpdated.setActivityEndData(band.getActivityEndData());

        log.debug("### Exiting update band method.");

        return bandToBeUpdated;
    }

    @Override
    public Band delete(Long id) {
        log.debug("### Entering delete band method.");
        Optional<Band> optional = bandRepository.findById(id);

        Band bandToBeDeleted = optional.orElseThrow(
                () -> new ServiceException(ExceptionMessages.ENTITY_WITH_GIVEN_ID_DOES_NOT_EXIST.message));

        bandRepository.deleteById(bandToBeDeleted.getId());
        log.debug("### Exiting delete band method.");

        return bandToBeDeleted;
    }

    @Override
    public List<Band> sortareAlfabetica() {
        return bandRepository.findAllByOrderByNameAsc();
    }

    @Override
    public List<Band> activitateInceputaIntre(Date data1, Date data2) {
        return bandRepository.findByActivityStartDataBetween(data1, data2);
    }
}