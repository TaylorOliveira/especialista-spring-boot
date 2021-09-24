package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.domain.exception.EntityNotFoundException;
import com.algaworks.algafood.domain.exception.EntityInUseException;
import com.algaworks.algafood.domain.repository.StateRepository;
import com.algaworks.algafood.domain.service.StateService;
import org.springframework.web.bind.annotation.*;
import com.algaworks.algafood.domain.model.State;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.beans.BeanUtils;
import java.util.Optional;
import java.util.List;

@RestController
@RequestMapping("/states")
public class StateController {

    private final StateRepository stateRepository;
    private final StateService stateService;

    public StateController(StateRepository stateRepository, StateService stateService) {
        this.stateRepository = stateRepository;
        this.stateService = stateService;
    }

    @GetMapping
    public List<State> list() {
        return stateRepository.findAll();
    }

    @GetMapping("/{stateId}")
    public ResponseEntity<State> search(@PathVariable Long stateId) {
        Optional<State> state = stateRepository.findById(stateId);
        return state.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public State save(@RequestBody State state) {
        return stateService.save(state);
    }

    @PutMapping("/{stateId}")
    public ResponseEntity<State> update(@PathVariable Long stateId,
                                          @RequestBody State stateRequest) {
        Optional<State> state = stateRepository.findById(stateId);
        if(state.isPresent()) {
            BeanUtils.copyProperties(stateRequest, state.get(), "id");
            State stateSave = stateService.save(state.get());
            return ResponseEntity.ok(stateSave);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{stateId}")
    public ResponseEntity<State> delete(@PathVariable Long stateId) {
        try {
            stateService.delete(stateId);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException exception) {
            return ResponseEntity.notFound().build();
        } catch (EntityInUseException exception) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }
}
