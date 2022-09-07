package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.domain.exception.EntityInUseException;
import com.algaworks.algafood.domain.exception.EntityNotFoundException;
import com.algaworks.algafood.domain.model.Kitchen;
import com.algaworks.algafood.domain.repository.KitchenRepository;
import com.algaworks.algafood.domain.service.KitchenService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/kitchens")
public class KitchenController {

    private final KitchenRepository kitchenRepository;
    private final KitchenService kitchenService;

    public KitchenController(KitchenRepository kitchenRepository,
                             KitchenService kitchenService) {
        this.kitchenRepository = kitchenRepository;
        this.kitchenService = kitchenService;
    }

    @GetMapping
    public List<Kitchen> list() {
        return kitchenService.findAll();
    }

    @GetMapping("/{kitchenId}")
    public ResponseEntity<Kitchen> search(@PathVariable Long kitchenId) {
        Optional<Kitchen> kitchen = kitchenRepository.findById(kitchenId);
        return kitchen.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Kitchen save(@RequestBody Kitchen kitchen) {
        return kitchenService.save(kitchen);
    }

    @PutMapping("/{kitchenId}")
    public ResponseEntity<Kitchen> update(@PathVariable Long kitchenId,
                                          @RequestBody Kitchen kitchenRequest) {
        Optional<Kitchen> kitchen = kitchenRepository.findById(kitchenId);
        if(kitchen.isPresent()) {
            BeanUtils.copyProperties(kitchenRequest, kitchen.get(), "id");
            Kitchen kitchenSave = kitchenService.save(kitchen.get());
            return ResponseEntity.ok(kitchenSave);
        }
        return ResponseEntity.notFound().build();
    }

//    @DeleteMapping("/{kitchenId}")
//    public ResponseEntity<Kitchen> delete(@PathVariable Long kitchenId) {
//        try {
//            kitchenService.delete(kitchenId);
//            return ResponseEntity.noContent().build();
//        } catch (EntityNotFoundException exception) {
//            return ResponseEntity.notFound().build();
//        } catch (EntityInUseException exception) {
//            return ResponseEntity.status(HttpStatus.CONFLICT).build();
//        }
//    }

    @DeleteMapping("/{kitchenId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long kitchenId) {
        kitchenService.delete(kitchenId);
    }
}
