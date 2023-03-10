package ro.ubb.music.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.ubb.music.core.exceptions.ServiceException;
import ro.ubb.music.core.model.Band;
import ro.ubb.music.core.service.BandService;
import ro.ubb.music.web.converter.BandConverter;
import ro.ubb.music.web.dto.BandDto;
import ro.ubb.music.web.dto.BandDtos;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("bands")
public class BandController {

    @Autowired
    private BandService bandService;
    @Autowired
    private BandConverter bandConverter;


    @GetMapping(value = "/{id}")
    ResponseEntity<BandDto> getBandById(@PathVariable Long id) {
        try {
            Band band = bandService.readOne(id);

            return new ResponseEntity<>(bandConverter.convertModelToDto(band), HttpStatus.OK);
        } catch (ServiceException e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping()
    ResponseEntity<BandDtos> getBands() {
        List<Band> bands = bandService.readAll();
        Set<BandDto> dtoSet = bandConverter.convertModelsToDtos(bands);

        return new ResponseEntity<>(new BandDtos(dtoSet), HttpStatus.OK);
    }

    @GetMapping(value = "/sortare")
    ResponseEntity<BandDtos> sortBands() {
        List<Band> bandsSortedAlf = bandService.sortareAlfabetica();
        Set<BandDto> bandDtos = bandConverter.convertModelsToDtos(bandsSortedAlf);

        return new ResponseEntity<>(new BandDtos(bandDtos), HttpStatus.OK);
    }

//
//    @GetMapping(value = "/status={status}")
//    ResponseEntity<UserDtos> getUsersByStatus(@PathVariable String status) {
//        UserStatuses userStatus = UserStatuses.of(status);
//        List<User> users = userService.readAll(userStatus);
//
//        Set<UserDto> dtoSet = userConverter.convertModelsToDtos(users);
//
//        return new ResponseEntity<>(new UserDtos(dtoSet), HttpStatus.OK);
//    }

    @PostMapping()
    ResponseEntity<BandDto> saveBand(@RequestBody BandDto bandDto) {
        Band band = bandConverter.convertDtoToModel(bandDto);

        try {
            Band savedBand = bandService.create(band);

            return new ResponseEntity<>(bandConverter.convertModelToDto(savedBand), HttpStatus.OK);
        } catch (ServiceException e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }


    @PutMapping(value = "/{id}")
    ResponseEntity<BandDto> updateBand(@PathVariable Long id, @RequestBody BandDto bandDto) {
        Band band = bandConverter.convertDtoToModel(bandDto);


        try {
            Band updated = bandService.update(id, band);

            return new ResponseEntity<>(bandConverter.convertModelToDto(updated), HttpStatus.OK);
        } catch (ServiceException e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @DeleteMapping(value = "/{id}")
    ResponseEntity<BandDto> deleteBandById(@PathVariable Long id) {
        try {
            Band deleted = bandService.delete(id);

            return new ResponseEntity<>(bandConverter.convertModelToDto(deleted), HttpStatus.OK);
        } catch (ServiceException e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }


}
