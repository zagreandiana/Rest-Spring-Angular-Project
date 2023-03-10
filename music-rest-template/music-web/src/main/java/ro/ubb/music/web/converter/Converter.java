package ro.ubb.music.web.converter;

import ro.ubb.music.core.model.BaseEntity;
import ro.ubb.music.web.dto.BaseDto;



public interface Converter<Model extends BaseEntity<Long>, Dto extends BaseDto> {

    Model convertDtoToModel(Dto dto);

    Dto convertModelToDto(Model model);

}

