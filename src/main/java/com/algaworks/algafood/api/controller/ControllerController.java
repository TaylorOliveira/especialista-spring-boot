package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.domain.model.Restore;
import com.algaworks.algafood.domain.repository.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/test")
public class ControllerController {

    @Autowired
    private TestRepository testRepository;

    @GetMapping("/name")
    public List<Restore> byName(String name) {
        return testRepository.find(name);
    }
}
