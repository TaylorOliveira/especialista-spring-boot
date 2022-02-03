package com.algaworks.algafood.domain.service;

import com.algaworks.algafood.domain.exception.EntityNotFoundException;
import com.algaworks.algafood.domain.exception.EntityInUseException;
import com.algaworks.algafood.domain.repository.StateRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import com.algaworks.algafood.domain.model.State;
import org.springframework.stereotype.Service;

@Service
public class StateService {

    private final StateRepository stateRepository;

    public StateService(StateRepository stateRepository) {
        this.stateRepository = stateRepository;
    }

    public State save(State state) {
        return stateRepository.save(state);
    }

    public void delete(Long stateId) {
        try {
            stateRepository.deleteById(stateId);
        } catch (EmptyResultDataAccessException exception) {
            throw new EntityNotFoundException(
                    String.format("There is no state registration with code %d.", stateId));
        } catch (DataIntegrityViolationException exception) {
            throw new EntityInUseException(
                    String.format("State code %d cannot be removed as it is in use.", stateId)
            );
        }
    }
}
