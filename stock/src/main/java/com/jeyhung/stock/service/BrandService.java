package com.jeyhung.stock.service;

import com.jeyhung.stock.dto.BrandCreateDto;
import com.jeyhung.stock.dto.BrandKeyValueDto;
import com.jeyhung.stock.dto.BrandListItemDto;
import com.jeyhung.stock.dto.BrandUpdateDto;
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
