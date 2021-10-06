package com.algaworks.algafood.domain.repository;

import com.algaworks.algafood.domain.model.Restore;
import java.math.BigDecimal;
import java.util.List;

public interface CustomizeRestoreRepository {

    List<Restore> find(String name, BigDecimal startShippingFee, BigDecimal lastShippingFee);
}
