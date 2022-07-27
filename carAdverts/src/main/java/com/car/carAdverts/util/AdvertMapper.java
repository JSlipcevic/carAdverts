package com.car.carAdverts.util;

import com.car.carAdverts.dto.Advert;
import com.car.carAdverts.entity.AdvertEntity;


import static com.car.carAdverts.util.DateUtil.instantToString;
import static com.car.carAdverts.util.DateUtil.stringToInstant;

public class AdvertMapper {

    public static AdvertEntity toEntity(Advert advertData) {
        return new AdvertEntity(advertData.getId(), advertData.getTitle(), advertData.getFuelType(), advertData.getPrice(), advertData.getIsNew(),
                advertData.getMileage(), stringToInstant(advertData.getFirstRegistration()));
    }

    public static Advert toDto(AdvertEntity advertEntity) {
        return new Advert(advertEntity.getId(), advertEntity.getTitle(), advertEntity.getFuelType(), advertEntity.getPrice(),
                advertEntity.getIsNew(), advertEntity.getMileage(), instantToString(advertEntity.getFirstRegistration()));
    }
}
