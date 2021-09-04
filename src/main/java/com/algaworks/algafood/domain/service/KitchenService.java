package com.algaworks.algafood.domain.service;

import com.algaworks.algafood.domain.model.Kitchen;
import com.algaworks.algafood.domain.repository.KitchenRepository;
import org.springframework.stereotype.Service;

@Service
public class KitchenService {

    private final KitchenRepository kitchenRepository;

    public KitchenService(KitchenRepository kitchenRepository) {
        this.kitchenRepository = kitchenRepository;
    }

    public Kitchen save(Kitchen kitchen) {
        return kitchenRepository.save(kitchen);
    }
}
