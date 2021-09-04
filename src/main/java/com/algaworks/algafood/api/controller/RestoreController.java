package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.domain.model.Restore;
import com.algaworks.algafood.domain.repository.RestoreRepository;
import com.algaworks.algafood.domain.service.RestoreService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
}
