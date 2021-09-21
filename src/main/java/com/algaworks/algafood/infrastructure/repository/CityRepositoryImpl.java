package com.algaworks.algafood.infrastructure.repository;

import com.algaworks.algafood.domain.model.City;
import com.algaworks.algafood.domain.model.Kitchen;
import com.algaworks.algafood.domain.model.State;
import com.algaworks.algafood.domain.repository.CityRepository;
import com.algaworks.algafood.domain.repository.StateRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Objects;

@Component
public class CityRepositoryImpl implements CityRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<City> list() {
        return entityManager.createQuery("from City", City.class)
                .getResultList();
    }

    @Override
    public City search(Long id) {
        return entityManager.find(City.class, id);
    }

    @Override
    public City save(City city) {
        return entityManager.merge(city);
    }

    @Override
    @Transactional
    public void delete(Long cityId) {
        City city = search(cityId);
        if(Objects.isNull(city)) {
            throw new EmptyResultDataAccessException(1);
        }
        entityManager.remove(city);
    }
}
