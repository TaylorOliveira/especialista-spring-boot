package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.domain.model.Kitchen;
import com.algaworks.algafood.domain.model.payload.KitchenXmlResponse;
import com.algaworks.algafood.domain.repository.KitchenRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping(value = "/kitchens", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
public class KitchenController {

    private final KitchenRepository kitchenRepository;

    public KitchenController(KitchenRepository kitchenRepository) {
        this.kitchenRepository = kitchenRepository;
    }

    public List<Kitchen> list() {
        return kitchenRepository.list();
    }

    @GetMapping(produces = MediaType.APPLICATION_XML_VALUE)
    public KitchenXmlResponse listXml() {
        return new KitchenXmlResponse(kitchenRepository.list());
    }

    @GetMapping("/{kitchenId}")
    public ResponseEntity<Kitchen> search(@PathVariable Long kitchenId) {
        Kitchen kitchen = kitchenRepository.search(kitchenId);
        if(Objects.nonNull(kitchen)) {
            return ResponseEntity.ok(kitchen);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Kitchen save(@RequestBody Kitchen kitchen) {
        return kitchenRepository.save(kitchen);
    }
}
