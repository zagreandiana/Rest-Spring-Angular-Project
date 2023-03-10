package ro.ubb.music.core.repository;


import ro.ubb.music.core.model.Band;

import java.sql.Date;
import java.util.List;

public interface BandRepository extends Repository<Band, Long> {
    List<Band> findAllByOrderByNameAsc();

    List<Band> findByActivityStartDataBetween(Date startDate, Date endDate);
}