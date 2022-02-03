package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.domain.model.Restore;
import com.algaworks.algafood.domain.repository.RestoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Optional;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private RestoreRepository restoreRepository;

    @GetMapping("/restore")
    public Optional<Restore> searchFirst() {
        return restoreRepository.findFirst();
    }
}
