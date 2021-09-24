package com.algaworks.algafood.domain.service;

import com.algaworks.algafood.domain.exception.EntityNotFoundException;
import com.algaworks.algafood.domain.exception.EntityInUseException;
import com.algaworks.algafood.domain.repository.CityRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import com.algaworks.algafood.domain.model.City;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CityService {

    private final CityRepository cityRepository;

    public CityService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    public List<City> findAll() {
        return cityRepository.findAll();
    }

    public City save(City city) {
        return cityRepository.save(city);
    }

    public void delete(Long cityId) {
        try {
            cityRepository.deleteById(cityId);
        } catch (EmptyResultDataAccessException exception) {
            throw new EntityNotFoundException(
                    String.format("There is no city registration with code %d.", cityId));
        } catch (DataIntegrityViolationException exception) {
            throw new EntityInUseException(
                    String.format("City code %d cannot be removed as it is in use.", cityId)
            );
        }
    }
}
