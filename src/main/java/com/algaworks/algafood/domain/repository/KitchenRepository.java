package com.algaworks.algafood.domain.repository;

import com.algaworks.algafood.domain.model.Kitchen;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public interface KitchenRepository {

    List<Kitchen> list();

    Kitchen search(Long id);

    List<Kitchen> searchKitchen(String name);

    Kitchen save(Kitchen kitchen);

    void delete(Long kitchenId);
}
