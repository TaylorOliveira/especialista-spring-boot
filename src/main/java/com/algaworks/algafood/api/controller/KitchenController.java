package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.domain.model.Kitchen;
import com.algaworks.algafood.domain.model.payload.KitchenXmlResponse;
import com.algaworks.algafood.domain.repository.KitchenRepository;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{kitchenId}")
    public ResponseEntity<Kitchen> search(@PathVariable Long kitchenId) {
        Kitchen kitchen =  kitchenRepository.search(kitchenId);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.LOCATION, "http://localhost:8080/kitchens");
        // return ResponseEntity.ok(kitchen);
        // return ResponseEntity.status(HttpStatus.OK).body(kitchen);
        return ResponseEntity
                .status(HttpStatus.OK)
                .headers(httpHeaders)
                .build();
    }
}
