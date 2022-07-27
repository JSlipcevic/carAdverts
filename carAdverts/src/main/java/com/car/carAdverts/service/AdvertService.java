package com.car.carAdverts.service;

import com.car.carAdverts.dto.Advert;
import com.car.carAdverts.entity.AdvertEntity;
import com.car.carAdverts.exception.AdvertNotFoundException;
import com.car.carAdverts.exception.UnprocessableEntityException;
import com.car.carAdverts.repository.AdvertRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.stream.Collectors;

import static com.car.carAdverts.util.AdvertMapper.toDto;
import static com.car.carAdverts.util.AdvertMapper.toEntity;
import static com.car.carAdverts.util.DateUtil.stringToInstant;
import static java.util.Objects.isNull;

@Service
@AllArgsConstructor
public class AdvertService {

    private final AdvertRepository advertRepository;

    public Advert createAdvert(Advert advertData) {
        validate(advertData);
        AdvertEntity advert = toEntity(advertData);
        advertRepository.save(advert);
        return toDto(advert);
    }

    public void deleteAdvert(Long advertId) {
        try {
            advertRepository.deleteById(advertId);
        } catch (Exception e) {
            throw new AdvertNotFoundException("Explanation: This is returned if a car advert with given id is not found");
        }
    }

    public Advert modifyAdvert(Long advertId, Advert advertData) {
        AdvertEntity advert = advertRepository.findById(advertId)
                .orElseThrow(() -> new AdvertNotFoundException("Explanation: This is returned if a car advert with given id is not found"));

        validate(advertData);

        advert.setTitle(advertData.getTitle());
        advert.setFuelType(advertData.getFuelType());
        advert.setPrice(advertData.getPrice());
        advert.setMileage(advertData.getMileage());
        advert.setIsNew(advertData.getIsNew());
        advert.setFirstRegistration(stringToInstant(advertData.getFirstRegistration()));
        advertRepository.save(advert);
        return toDto(advert);
    }

    public Advert getAdvert(Long advertId) {
        AdvertEntity advert = advertRepository.findById(advertId)
                .orElseThrow(() -> new AdvertNotFoundException("Explanation: No car advert with given id was found."));
        return toDto(advert);
    }

    public List<Advert> getAdverts(String sortBy) {
        if (sortBy != null) {
            if (checkIfFieldExist(AdvertEntity.class, sortBy)) {
                List<AdvertEntity> allAdverts = advertRepository.findAll(Sort.by(Sort.Direction.ASC, sortBy));
                return allAdverts.stream()
                        .map(advert -> toDto(advert))
                        .collect(Collectors.toList());
            }
        }

        List<AdvertEntity> allAdverts = advertRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
        return allAdverts.stream()
                .map(advert -> toDto(advert))
                .collect(Collectors.toList());

    }

    private boolean checkIfFieldExist(Class<AdvertEntity> entityClass, String sortingField) {
        for (Field field : entityClass.getDeclaredFields()) {
            if (field.getName().equals(sortingField)) {
                return true;
            }
        }
        return false;
    }

    private void validate(Advert advertData) {


        String error = "";

        if (!isNull(advertData.getId()) && advertData.getId() < 0 || !isNull(advertData.getPrice()) && advertData.getPrice() < 0) {
            error += "Id must be a positive number";
        }

        if (!isNull(advertData.getPrice()) && advertData.getPrice() < 0) {
            error += "Price cannot be negative";
        }

        if (!error.equals("")) {
            throw new UnprocessableEntityException(error);
        }
    }

}
