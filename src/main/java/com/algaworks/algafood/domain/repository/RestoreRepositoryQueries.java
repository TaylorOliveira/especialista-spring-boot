package com.algaworks.algafood.domain.repository;

import com.algaworks.algafood.domain.model.Restore;
import java.math.BigDecimal;
import java.util.List;

public interface RestoreRepositoryQueries {

    List<Restore> find(String name,
                       BigDecimal initialShippingFee, BigDecimal finalShippingFee);

    List<Restore> findWithFreeShipping(String name);
}
