package ro.ubb.music.core.service;


import ro.ubb.music.core.model.Band;

import java.sql.Date;
import java.util.List;

public interface BandService {
    Band create(Band band);

    Band readOne(Long id);

    List<Band> readAll();

    Band update(Long id,Band band);

    Band delete(Long id);

    List<Band> sortareAlfabetica();

    List<Band> activitateInceputaIntre(Date data1, Date data2);
}
