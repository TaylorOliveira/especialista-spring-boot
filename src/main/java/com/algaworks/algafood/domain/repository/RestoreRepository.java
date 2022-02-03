package com.algaworks.algafood.domain.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import com.algaworks.algafood.domain.model.Restore;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.math.BigDecimal;
import java.util.Optional;
import java.util.List;

@Repository
public interface RestoreRepository extends CustomJpaRepository<Restore, Long>,
        RestoreRepositoryQueries, JpaSpecificationExecutor<Restore> {

    List<Restore> consultByName(@Param("name") String name, @Param("id") Long kitchenId);

    List<Restore> findByShippingFeeBetween(BigDecimal first, BigDecimal last);

    List<Restore> findByNameContainingAndKitchenId(String name, Long kitchen);

    Optional<Restore> findFirstByNameContaining(String name);

    List<Restore> findTop10ByNameContaining(String name);

    int countByKitchenId(Long kitchenId);

    List<Restore> find(String name, BigDecimal initialShippingFee, BigDecimal finalShippingFee);
}
