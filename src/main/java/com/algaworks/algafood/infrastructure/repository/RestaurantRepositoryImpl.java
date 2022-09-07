package com.algaworks.algafood.infrastructure.repository;

import com.algaworks.algafood.domain.model.Restaurant;
import com.algaworks.algafood.domain.repository.RestaurantRepositoryQueries;
import com.algaworks.algafood.domain.repository.RestaurantRepository;
import com.algaworks.algafood.infrastructure.repository.spec.RestaurantSpecs;
import org.springframework.beans.factory.annotation.Autowired;
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
public class RestaurantRepositoryImpl implements RestaurantRepositoryQueries {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    @Lazy
    private RestaurantRepository restaurantRepository;

    public List<Restaurant> find(String name, BigDecimal initialShippingFee,
                                 BigDecimal finalShippingFee) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Restaurant> criteria = builder.createQuery(Restaurant.class);
        Root<Restaurant> restaurantRoot = criteria.from(Restaurant.class);
        List<Predicate> predicates = new ArrayList<>();
        if(StringUtils.hasText(name)) {
            predicates.add(builder.like(restaurantRoot.get("name"), name));
        }
        if(Objects.nonNull(initialShippingFee)) {
            predicates.add(builder.lessThanOrEqualTo(restaurantRoot
                    .get("shippingFee"), initialShippingFee));
        }
        if(Objects.nonNull(finalShippingFee)) {
            predicates.add(builder.lessThanOrEqualTo(restaurantRoot
                    .get("shippingFee"), finalShippingFee));
        }
        criteria.where(predicates.toArray(new Predicate[0]));
        TypedQuery<Restaurant> restaurantTypedQuery = entityManager.createQuery(criteria);
        return restaurantTypedQuery.getResultList();
    }

    @Override
    public List<Restaurant> findWithFreeShipping(String name) {
        return restaurantRepository.findAll(RestaurantSpecs.withFreeShipping()
                .and(RestaurantSpecs.withSimilarName(name)));
    }
}
