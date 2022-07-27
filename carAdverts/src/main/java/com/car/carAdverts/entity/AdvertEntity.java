package com.car.carAdverts.entity;

import com.car.carAdverts.model.FuelType;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Instant;

@Table(name = "advert")
@Entity
@Data
@NoArgsConstructor
public class AdvertEntity {

    @Id
    /*@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "advert_generator")
    @SequenceGenerator(name = "advert_generator", sequenceName = "advert_generator", initialValue = 50, allocationSize = 1)
    @Column(name = "id", updatable = false, nullable = false)*/
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "fuel_type", nullable = false)
    private FuelType fuelType;

    @Column(name = "price")
    private Integer price;

    @Column(name = "is_new", nullable = false)
    private Boolean isNew;

    @Column(name = "mileage")
    private Integer mileage;

    @Column(name = "first_registration")
    private Instant firstRegistration;

    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;

    @PrePersist
    public void prePersist() {
        createdAt = Instant.now();
        updatedAt = Instant.now();
    }

    @PreUpdate
    public void preUpdate() {
        updatedAt = Instant.now();
    }

    public AdvertEntity(Long id, String title, FuelType fuelType, Integer price, Boolean isNew, Integer mileage,
                        Instant firstRegistration) {
        this.id = id;
        this.title = title;
        this.fuelType = fuelType;
        this.price = price;
        this.isNew = isNew;
        this.mileage = mileage;
        this.firstRegistration = firstRegistration;
    }
}
