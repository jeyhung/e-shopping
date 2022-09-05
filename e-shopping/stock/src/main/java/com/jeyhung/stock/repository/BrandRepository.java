package com.jeyhung.stock.repository;

import catalog.brands.domain.model.Brand;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface BrandRepository extends MongoRepository<Brand, Long> {
    Optional<Brand> findByName(@Param("name") String name);
}
