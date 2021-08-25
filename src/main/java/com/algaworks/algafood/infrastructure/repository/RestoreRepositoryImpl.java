package com.algaworks.algafood.infrastructure.repository;

import com.algaworks.algafood.domain.model.Restore;
import com.algaworks.algafood.domain.repository.RestoreRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
public class RestoreRepositoryImpl implements RestoreRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<Restore> list() {
        return entityManager.createQuery("from Restore", Restore.class)
                .getResultList();
    }

    @Override
    public Restore search(Long id) {
        return entityManager.find(Restore.class, id);
    }

    @Override
    @Transactional
    public Restore save(Restore restore) {
        return entityManager.merge(restore);
    }

    @Override
    @Transactional
    public void remove(Restore restore) {
        restore = search(restore.getId());
        entityManager.remove(restore);
    }
}
