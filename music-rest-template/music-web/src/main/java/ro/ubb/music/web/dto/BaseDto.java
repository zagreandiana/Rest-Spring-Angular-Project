package ro.ubb.music.web.dto;

import lombok.*;

import java.io.Serializable;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class BaseDto implements Serializable {
    protected Long id;
}
