package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.domain.exception.EntityNotFoundException;
import com.algaworks.algafood.domain.model.Restore;
import com.algaworks.algafood.domain.repository.RestoreRepository;
import com.algaworks.algafood.domain.service.RestoreService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/restaurants")
public class RestoreController {

    private final RestoreRepository restoreRepository;
    private final RestoreService restoreService;

    public RestoreController(RestoreRepository restoreRepository, RestoreService restoreService) {
        this.restoreRepository = restoreRepository;
        this.restoreService = restoreService;
    }

    public List<Restore> list() {
        return restoreService.list();
    }

    @GetMapping("/{restoreId}")
    public ResponseEntity<Restore> search(@PathVariable Long restoreId) {
        Restore restore = restoreRepository.search(restoreId);
        if(Objects.nonNull(restore)) {
            return ResponseEntity.ok(restore);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{restoreId}")
    public ResponseEntity<?> update(@PathVariable Long restoreId,
                                          @RequestBody Restore restore) {
        try {
            Restore restoreCurrent = restoreRepository.search(restoreId);
            if(Objects.nonNull(restoreCurrent)) {
                BeanUtils.copyProperties(restore, restoreCurrent, "id");;
                return ResponseEntity.ok(restoreService.save(restoreCurrent));
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
        Restore restore = restoreRepository.search(restoreId);
        if(Objects.isNull(restore)) {
            return ResponseEntity.notFound().build();
        }
        mergeFieldInRestore(fields, restore);
        return update(restoreId, restore);
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
}
