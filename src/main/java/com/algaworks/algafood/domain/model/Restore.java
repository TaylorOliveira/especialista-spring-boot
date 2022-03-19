package com.algaworks.algafood.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity(name = "tbl_entity")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Restore {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(name = "shipping_fee", nullable = false)
    private BigDecimal shippingFee;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "kitchen_id", nullable = false)
    private Kitchen kitchen;

    @JsonIgnore
    @Embedded
    private Address address;

    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "tbl_restore_payment_method",
        joinColumns = @JoinColumn(name = "restore_id"),
        inverseJoinColumns = @JoinColumn(name = "payment_method_id"))
    private List<PaymentMethod> paymentMethodList = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "restore")
    private List<Product> products;

    @JsonIgnore
    @CreationTimestamp
    @Column(nullable = false, columnDefinition = "dateTime")
    private LocalDateTime creationDateTime;

    @JsonIgnore
    @UpdateTimestamp
    @Column(nullable = false, columnDefinition = "dateTime")
    private LocalDateTime updatedDateTime;
}
