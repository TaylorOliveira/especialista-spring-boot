package com.algaworks.algafood.jpa;

import com.algaworks.algafood.AlgafoodApiApplication;
import com.algaworks.algafood.domain.model.PaymentMethod;
import com.algaworks.algafood.domain.repository.PaymentMethodRepository;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import java.util.List;

public class PaymentMethodConsult {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        PaymentMethodRepository paymentMethodRepository = applicationContext.getBean(PaymentMethodRepository.class);
        List<PaymentMethod> paymentMethods = paymentMethodRepository.list();
        for (PaymentMethod paymentMethod : paymentMethods) {
            System.out.println(paymentMethod.getDescription());
        }
    }
}
