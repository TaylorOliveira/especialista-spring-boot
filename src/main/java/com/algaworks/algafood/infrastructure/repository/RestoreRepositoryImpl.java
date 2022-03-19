package com.algaworks.algafood.infrastructure.repository;

import com.algaworks.algafood.domain.repository.RestoreRepositoryQueries;
import com.algaworks.algafood.domain.repository.RestoreRepository;
import com.algaworks.algafood.infrastructure.repository.spec.RestoreSpecs;
import org.springframework.beans.factory.annotation.Autowired;
import com.algaworks.algafood.domain.model.Restore;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import org.springframework.util.StringUtils;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.Root;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.math.BigDecimal;
import java.util.*;

@Repository
public class RestoreRepositoryImpl implements RestoreRepositoryQueries {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    @Lazy
    private RestoreRepository restoreRepository;

    public List<Restore> find(String name, BigDecimal initialShippingFee,
                              BigDecimal finalShippingFee) {

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Restore> criteria = builder.createQuery(Restore.class);
        Root<Restore> rootRestore = criteria.from(Restore.class);

        List<Predicate> predicates = new ArrayList<Predicate>();
        if(StringUtils.hasText(name)) {
            predicates.add(builder.like(rootRestore.get("name"), name));
        }
        if(Objects.nonNull(initialShippingFee)) {
            predicates.add(builder.lessThanOrEqualTo(rootRestore
                    .get("shippingFee"), initialShippingFee));
        }
        if(Objects.nonNull(finalShippingFee)) {
            predicates.add(builder.lessThanOrEqualTo(rootRestore
                    .get("shippingFee"), finalShippingFee));
        }

        criteria.where(predicates.toArray(new Predicate[0]));

        TypedQuery<Restore> restoreTypedQuery = entityManager.createQuery(criteria);
        return restoreTypedQuery.getResultList();
    }

    @Override
    public List<Restore> findWithFreeShipping(String name) {
        return restoreRepository.findAll(RestoreSpecs.withFreeShipping().and(RestoreSpecs.withSimilarName(name)));
    }
}
