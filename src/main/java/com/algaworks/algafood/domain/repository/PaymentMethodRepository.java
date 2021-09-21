package com.algaworks.algafood.domain.repository;

import com.algaworks.algafood.domain.model.PaymentMethod;
import java.util.List;

public interface PaymentMethodRepository {

    List<PaymentMethod> list();

    PaymentMethod search(Long id);

    PaymentMethod save(PaymentMethod city);

    void delete(PaymentMethod city);
}
