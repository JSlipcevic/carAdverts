package com.car.carAdverts.repository;

import com.car.carAdverts.entity.AdvertEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AdvertRepository extends JpaRepository <AdvertEntity, Long> {
}
