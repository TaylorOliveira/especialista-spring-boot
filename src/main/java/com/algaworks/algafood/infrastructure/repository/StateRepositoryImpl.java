package com.algaworks.algafood.infrastructure.repository;

import com.algaworks.algafood.domain.model.State;
import com.algaworks.algafood.domain.repository.StateRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Objects;

@Component
public class StateRepositoryImpl implements StateRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<State> list() {
        return entityManager.createQuery("from State", State.class)
                .getResultList();
    }

    @Override
    public State search(Long id) {
        return entityManager.find(State.class, id);
    }

    @Override
    @Transactional
    public State save(State state) {
        return entityManager.merge(state);
    }

    @Override
    @Transactional
    public void delete(Long stateId) {
        State state = search(stateId);
        if(Objects.isNull(state)) {
            throw new EmptyResultDataAccessException(1);
        }
        entityManager.remove(state);
    }
}
