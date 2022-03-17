package com.algaworks.algafood.api.controller;


import com.algaworks.algafood.domain.exception.EntityNotFoundException;
import com.algaworks.algafood.domain.model.Restaurant;
import com.algaworks.algafood.domain.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import com.algaworks.algafood.domain.service.RestaurantService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.*;
import org.springframework.util.ReflectionUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.beans.BeanUtils;
import java.lang.reflect.Field;
import java.util.Optional;
import java.util.Objects;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/restaurants")
public class RestaurantController {

    @Autowired
    private RestaurantRepository restaurantRepository;
    @Autowired
    private RestaurantService restaurantService;

    @GetMapping
    public List<Restaurant> list() {
        return restaurantService.findAll();
    }

    @GetMapping("/{restaurantId}")
    public ResponseEntity<Restaurant> search(@PathVariable Long restaurantId) {
        Optional<Restaurant> restaurant = restaurantRepository.findById(restaurantId);
        return restaurant.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{restaurantId}")
    public ResponseEntity<?> update(@PathVariable Long restaurantId,
                                          @RequestBody Restaurant restaurantRequest) {
        try {
            Optional<Restaurant> restaurant = restaurantRepository.findById(restaurantId);
            if(restaurant.isPresent()) {
                BeanUtils.copyProperties(restaurantRequest, restaurant.get(), "id");;
                return ResponseEntity.ok(restaurantService.save(restaurant.get()));
            }
            return ResponseEntity.notFound().build();
        } catch (EntityNotFoundException exception) {
            return ResponseEntity.badRequest()
                    .body(exception.getLocalizedMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody Restaurant restaurant) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(restaurantService.save(restaurant));
        } catch (EntityNotFoundException exception) {
            return ResponseEntity.badRequest()
                    .body(exception.getLocalizedMessage());
        }
    }

    @PatchMapping("/{restaurantId}")
    public ResponseEntity<?> updatePartial(@PathVariable Long restaurantId,
                                           @RequestBody Map<String, Object> fields) {
        Optional<Restaurant> restaurant = restaurantRepository.findById(restaurantId);
        if(restaurant.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        mergeFieldInRestaurant(fields, restaurant.get());
        return update(restaurantId, restaurant.get());
    }

    private void mergeFieldInRestaurant(Map<String, Object> fields, Restaurant restaurant) {
        ObjectMapper objectMapper = new ObjectMapper();
        Restaurant restaurantOrigen = objectMapper.convertValue(fields, Restaurant.class);
        fields.forEach((name, value) -> {
            Field field = ReflectionUtils.findField(Restaurant.class, name);
            if(Objects.nonNull(field)) {
                field.setAccessible(true);
                Object fieldOrigen = ReflectionUtils.getField(field, restaurantOrigen);
                ReflectionUtils.setField(field, restaurant, fieldOrigen);
            }
        });
    }

    @GetMapping("/restaurant/with-free-shipping")
    public List<Restaurant> restaurantWithFreeShipping(@RequestParam("name") String name) {
        return restaurantRepository.findWithFreeShipping(name);
    }
}
