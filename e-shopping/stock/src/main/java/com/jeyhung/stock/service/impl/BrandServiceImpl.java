package com.jeyhung.stock.service.impl;

import catalog.brands.application.dto.BrandCreateDto;
import catalog.brands.application.dto.BrandKeyValueDto;
import catalog.brands.application.dto.BrandListItemDto;
import catalog.brands.application.dto.BrandUpdateDto;
import catalog.brands.application.exception.BrandAlreadyExistsException;
import catalog.brands.application.exception.BrandNotFoundException;
import catalog.brands.domain.model.Brand;
import catalog.brands.domain.repository.BrandRepository;
import catalog.sequences.application.SequenceService;
import com.jeyhung.stock.service.BrandService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BrandServiceImpl implements BrandService {
    private final ModelMapper modelMapper;
    private final BrandRepository brandRepository;
    private final SequenceService sequenceService;

    public BrandServiceImpl(ModelMapper modelMapper,
                            BrandRepository brandRepository,
                            SequenceService sequenceService) {
        this.modelMapper = modelMapper;
        this.brandRepository = brandRepository;
        this.sequenceService = sequenceService;
    }

    @Override
    public List<BrandListItemDto> getAll(Pageable pageable) {
        return brandRepository
                .findAll(pageable)
                .stream()
                .map(brand -> modelMapper.map(brand, BrandListItemDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<BrandKeyValueDto> getAll() {
        return brandRepository
                .findAll()
                .stream()
                .map(brand -> modelMapper.map(brand, BrandKeyValueDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public BrandListItemDto getById(long id) {
        Brand brand = findById(id);
        return modelMapper.map(brand, BrandListItemDto.class);
    }

    @Override
    public long create(BrandCreateDto brandCreateDto) {
        validateNameNotExists(brandCreateDto.getName());
        Brand brand = modelMapper.map(brandCreateDto, Brand.class);
        brand.setId(sequenceService.next(Brand.SEQUENCE_NAME));
        brandRepository.save(brand);
        return brand.getId();
    }

    @Override
    public void update(BrandUpdateDto brandUpdateDto) {
        validateNameNotExists(brandUpdateDto.getId(), brandUpdateDto.getName());
        Brand brand = findById(brandUpdateDto.getId());
        modelMapper.map(brand, brandUpdateDto);
        brandRepository.save(brand);
    }

    @Override
    public void deleteById(long id) {
        Brand brand = findById(id);
        brandRepository.delete(brand);
    }

    private Brand findById(long id) {
        return brandRepository.findById(id)
                .orElseThrow(() -> new BrandNotFoundException());
    }

    private void validateNameNotExists(String name) {
        Optional<Brand> optionalBrand = brandRepository.findByName(name);
        if (optionalBrand.isPresent()) {
            throw new BrandAlreadyExistsException();
        }
    }

    private void validateNameNotExists(long id, String name) {
        Optional<Brand> optionalBrand = brandRepository.findByName(name);
        if (optionalBrand.isPresent() && optionalBrand.get().getId() != id) {
            throw new BrandAlreadyExistsException();
        }
    }
}