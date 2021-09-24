package com.algaworks.algafood.domain.service;

import com.algaworks.algafood.domain.exception.EntityNotFoundException;
import com.algaworks.algafood.domain.repository.KitchenRepository;
import com.algaworks.algafood.domain.repository.RestoreRepository;
import com.algaworks.algafood.domain.model.Restore;
import com.algaworks.algafood.domain.model.Kitchen;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class RestoreService {

    private final RestoreRepository restoreRepository;
    private final KitchenRepository kitchenRepository;

    public RestoreService(RestoreRepository restoreRepository, KitchenRepository kitchenRepository) {
        this.restoreRepository = restoreRepository;
        this.kitchenRepository = kitchenRepository;
    }

    public List<Restore> list() {
        return restoreRepository.list();
    }

    public Restore save(Restore restore) {
        Long kitchenId = restore.getKitchen().getId();
        Kitchen kitchen = kitchenRepository.findById(kitchenId)
                .orElseThrow(()->new EntityNotFoundException(
                        String.format("There is no kitchen registration with code %d.", kitchenId))
                );
        restore.setKitchen(kitchen);
        return restoreRepository.save(restore);
    }
}
