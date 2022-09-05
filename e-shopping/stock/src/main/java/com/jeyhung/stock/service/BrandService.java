package com.jeyhung.stock.service;

import catalog.brands.application.dto.BrandCreateDto;
import catalog.brands.application.dto.BrandKeyValueDto;
import catalog.brands.application.dto.BrandListItemDto;
import catalog.brands.application.dto.BrandUpdateDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BrandService {
    List<BrandListItemDto> getAll(Pageable pageable);

    List<BrandKeyValueDto> getAll();

    BrandListItemDto getById(long id);

    long create(BrandCreateDto brandCreateDto);

    void update(BrandUpdateDto brandUpdateDto);

    void deleteById(long id);
}
