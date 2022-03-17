package com.algaworks.algafood.domain.service;

import com.algaworks.algafood.domain.exception.EntityNotFoundException;
import com.algaworks.algafood.domain.model.Restaurant;
import com.algaworks.algafood.domain.repository.KitchenRepository;
import com.algaworks.algafood.domain.repository.RestaurantRepository;
import com.algaworks.algafood.domain.model.Kitchen;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;
    private final KitchenRepository kitchenRepository;

    public RestaurantService(RestaurantRepository restaurantRepository, KitchenRepository kitchenRepository) {
        this.restaurantRepository = restaurantRepository;
        this.kitchenRepository = kitchenRepository;
    }

    public List<Restaurant> findAll() {
        return restaurantRepository.findAll();
    }

    public Restaurant save(Restaurant restaurant) {
        Long kitchenId = restaurant.getKitchen().getId();
        Kitchen kitchen = kitchenRepository.findById(kitchenId)
                .orElseThrow(()->new EntityNotFoundException(
                        String.format("There is no kitchen registration with code %d.", kitchenId))
                );
        restaurant.setKitchen(kitchen);
        return restaurantRepository.save(restaurant);
    }
}
