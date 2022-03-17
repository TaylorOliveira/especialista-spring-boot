package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.domain.model.Restaurant;
import com.algaworks.algafood.domain.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Optional;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @GetMapping("/restaurant")
    public Optional<Restaurant> searchFirst() {
        return restaurantRepository.findFirst();
    }
}
