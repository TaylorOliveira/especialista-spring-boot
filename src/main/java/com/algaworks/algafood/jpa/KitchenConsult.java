package com.algaworks.algafood.jpa;

import com.algaworks.algafood.AlgafoodApiApplication;
import com.algaworks.algafood.domain.model.Kitchen;
import com.algaworks.algafood.domain.repository.KitchenRepository;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import java.util.List;

public class KitchenConsult {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        KitchenRepository kitchenRepository = applicationContext.getBean(KitchenRepository.class);
        List<Kitchen> kitchens = kitchenRepository.list();
        for (Kitchen kitchen : kitchens) {
            System.out.println(kitchen.getName());
        }
    }
}
