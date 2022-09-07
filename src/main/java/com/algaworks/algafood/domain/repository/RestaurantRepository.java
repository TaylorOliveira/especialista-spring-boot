package com.algaworks.algafood.domain.repository;

import com.algaworks.algafood.domain.model.Restaurant;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import com.algaworks.algafood.domain.model.Restaurant;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.math.BigDecimal;
import java.util.Optional;
import java.util.List;

@Repository
public interface RestaurantRepository extends CustomJpaRepository<Restaurant, Long>,
        RestaurantRepositoryQueries, JpaSpecificationExecutor<Restaurant> {

    @Query("from tbl_restaurant r join fetch r.kitchen left join fetch r.paymentMethods")
    List<Restaurant> findAll();

    List<Restaurant> consultByName(@Param("name") String name, @Param("id") Long kitchenId);

    List<Restaurant> findByShippingFeeBetween(BigDecimal first, BigDecimal last);

    List<Restaurant> findByNameContainingAndKitchenId(String name, Long kitchen);

    Optional<Restaurant> findFirstByNameContaining(String name);

    List<Restaurant> findTop10ByNameContaining(String name);

    int countByKitchenId(Long kitchenId);

    List<Restaurant> find(String name, BigDecimal initialShippingFee, BigDecimal finalShippingFee);
}
