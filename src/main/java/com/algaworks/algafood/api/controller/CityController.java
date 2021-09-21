package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.domain.exception.EntityInUseException;
import com.algaworks.algafood.domain.exception.EntityNotFoundException;
import com.algaworks.algafood.domain.model.City;
import com.algaworks.algafood.domain.repository.CityRepository;
import com.algaworks.algafood.domain.service.CityService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/cities")
public class CityController {

    private final CityRepository cityRepository;
    private final CityService cityService;

    public CityController(CityRepository cityRepository, CityService cityService) {
        this.cityRepository = cityRepository;
        this.cityService = cityService;
    }

    @GetMapping
    public List<City> list() {
        return cityService.list();
    }

    @GetMapping("/{cityId}")
    public ResponseEntity<City> search(@PathVariable Long cityId) {
        City city = cityRepository.search(cityId);
        if(Objects.nonNull(city)) {
            return ResponseEntity.ok(city);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public City save(@RequestBody City city) {
        return cityService.save(city);
    }

    @PutMapping("/{cityId}")
    public ResponseEntity<City> update(@PathVariable Long cityId,
                                          @RequestBody City city) {
        City cityCurrent = cityRepository.search(cityId);
        if(Objects.nonNull(cityCurrent)) {
            BeanUtils.copyProperties(city, cityCurrent, "id");
            cityService.save(cityCurrent);
            return ResponseEntity.ok(cityCurrent);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{cityId}")
    public ResponseEntity<City> delete(@PathVariable Long cityId) {
        try {
            cityService.delete(cityId);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException exception) {
            return ResponseEntity.notFound().build();
        } catch (EntityInUseException exception) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }
}
