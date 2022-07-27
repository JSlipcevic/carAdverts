package com.car.carAdverts.dto;


import com.car.carAdverts.model.FuelType;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;


@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Advert {

    @NotNull
    private Long id;

    @NotNull
    private String title;

    @NotNull
    private FuelType fuelType;

    private Integer price;

    @NotNull
    private Boolean isNew;

    private Integer mileage;

    private String firstRegistration;
}
