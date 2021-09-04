package com.algaworks.algafood.jpa;

import com.algaworks.algafood.domain.model.Kitchen;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
public class KitchenRegistration {

    @PersistenceContext
    private EntityManager manager;

    public Kitchen search(Long id) {
        return manager.find(Kitchen.class, id);
    }

    public List<Kitchen> list() {
        return manager.createQuery("from Kitchen", Kitchen.class)
                .getResultList();
    }

    @Transactional
    public void add(Kitchen kitchen) {
        manager.merge(kitchen);
    }

    @Transactional
    public void delete(Kitchen kitchen) {
        kitchen = search(kitchen.getId());
        manager.remove(kitchen);
    }
}
