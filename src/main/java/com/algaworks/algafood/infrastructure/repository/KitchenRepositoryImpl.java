package com.algaworks.algafood.infrastructure.repository;

import com.algaworks.algafood.domain.model.Kitchen;
import com.algaworks.algafood.domain.repository.KitchenRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Objects;

@Component
public class KitchenRepositoryImpl implements KitchenRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<Kitchen> list() {
        return entityManager.createQuery("from Kitchen", Kitchen.class)
                .getResultList();
    }

    @Override
    public Kitchen search(Long id) {
        return entityManager.find(Kitchen.class, id);
    }

    @Override
    @Transactional
    public Kitchen save(Kitchen kitchen) {
        return entityManager.merge(kitchen);
    }

    @Override
    @Transactional
    public void delete(Long kitchenId) {
        Kitchen kitchen = search(kitchenId);
        if(Objects.nonNull(kitchen)) {
            throw new EmptyResultDataAccessException(1);
        }
        entityManager.remove(kitchenId);
    }
}
