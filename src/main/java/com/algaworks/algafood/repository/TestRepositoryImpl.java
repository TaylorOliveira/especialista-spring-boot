package com.algaworks.algafood.repository;

import com.algaworks.algafood.domain.repository.TestRepositoryQueries;
import com.algaworks.algafood.domain.model.Restore;
import org.springframework.stereotype.Repository;
import javax.persistence.PersistenceContext;
import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class TestRepositoryImpl implements TestRepositoryQueries {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Restore> find(String name) {
        var jpql = "FROM Restore WHERE name LIKE :name";

        return entityManager.createQuery(jpql, Restore.class)
                .setParameter("name", "%"+name+"%")
                .getResultList();
    }
}
