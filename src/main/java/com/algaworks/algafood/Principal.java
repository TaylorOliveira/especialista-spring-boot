package com.algaworks.algafood;

import lombok.Builder;
import lombok.Data;

import java.util.Objects;

public class Principal {

    public static void main(String[] args) {

        Car car = Car.builder().build();

        try {
            if (Objects.isNull(car.getColor()) || Objects.isNull(car.getColor().getRfb())) {
                System.out.println();
            }
        } catch (Exception exception) {
            System.out.println(exception.getLocalizedMessage());
        }
    }

    @Data
    @Builder
    public static class Car {
        private Color color;
    }

    @Data
    @Builder
    public static class Color {
        private String rfb;
        private Type type;
    }

    @Data
    @Builder
    public static class Type {
        private String origin;
    }
}
