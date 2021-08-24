package com.algaworks.algafood.domain.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "restore")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Restore {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(name = "shipping_fee")
    private BigDecimal shippingFee;

    @ManyToOne
    @JoinColumn(name = "kitchen_id")
    private Kitchen kitchen;
}
