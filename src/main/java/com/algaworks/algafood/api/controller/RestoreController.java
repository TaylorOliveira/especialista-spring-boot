package com.algaworks.algafood.api.controller;

import static infrastructure.repository.spec.RestoreSpecs.*;

import com.algaworks.algafood.domain.exception.EntityNotFoundException;
import com.algaworks.algafood.domain.repository.RestoreRepository;
import com.algaworks.algafood.domain.service.RestoreService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.algaworks.algafood.domain.model.Restore;
import org.springframework.web.bind.annotation.*;
import org.springframework.util.ReflectionUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.beans.BeanUtils;
import java.lang.reflect.Field;
import java.util.Optional;
import java.util.Objects;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/restaurants")
public class RestoreController {

    private final RestoreRepository restoreRepository;
    private final RestoreService restoreService;

    public RestoreController(RestoreRepository restoreRepository, RestoreService restoreService) {
        this.restoreRepository = restoreRepository;
        this.restoreService = restoreService;
    }

    @GetMapping
    public List<Restore> list() {
        return restoreService.findAll();
    }

    @GetMapping("/{restoreId}")
    public ResponseEntity<Restore> search(@PathVariable Long restoreId) {
        Optional<Restore> restore = restoreRepository.findById(restoreId);
        return restore.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{restoreId}")
    public ResponseEntity<?> update(@PathVariable Long restoreId,
                                          @RequestBody Restore restoreRequest) {
        try {
            Optional<Restore> restore = restoreRepository.findById(restoreId);
            if(restore.isPresent()) {
                BeanUtils.copyProperties(restoreRequest, restore.get(), "id");;
                return ResponseEntity.ok(restoreService.save(restore.get()));
            }
            return ResponseEntity.notFound().build();
        } catch (EntityNotFoundException exception) {
            return ResponseEntity.badRequest()
                    .body(exception.getLocalizedMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody Restore restore) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(restoreService.save(restore));
        } catch (EntityNotFoundException exception) {
            return ResponseEntity.badRequest()
                    .body(exception.getLocalizedMessage());
        }
    }

    @PatchMapping("/{restoreId}")
    public ResponseEntity<?> updatePartial(@PathVariable Long restoreId,
                                           @RequestBody Map<String, Object> fields) {
        Optional<Restore> restore = restoreRepository.findById(restoreId);
        if(restore.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        mergeFieldInRestore(fields, restore.get());
        return update(restoreId, restore.get());
    }

    private void mergeFieldInRestore(Map<String, Object> fields, Restore restore) {
        ObjectMapper objectMapper = new ObjectMapper();
        Restore restoreOrigen = objectMapper.convertValue(fields, Restore.class);
        fields.forEach((name, value) -> {
            Field field = ReflectionUtils.findField(Restore.class, name);
            if(Objects.nonNull(field)) {
                field.setAccessible(true);
                Object fieldOrigen = ReflectionUtils.getField(field, restoreOrigen);
                ReflectionUtils.setField(field, restore, fieldOrigen);
            }
        });
    }

    @GetMapping("/restore/with-free-shipping")
    public List<Restore> restaurantWithFreeShipping(@RequestParam("name") String name) {
        return restoreRepository.findAll(withFreeShipping().and(withSimilarName(name)));
    }
}
