package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.domain.exception.EntityNotFoundException;
import com.algaworks.algafood.domain.model.Restore;
import com.algaworks.algafood.domain.repository.RestoreRepository;
import com.algaworks.algafood.domain.service.RestoreService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
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
}
