package com.omega.service.impl;

import com.omega.mapper.ManufacturerMapper;
import com.omega.model.dto.ManufacturerDTO;
import com.omega.repositories.ManufacturerRepository;
import com.omega.service.ManufacturerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ManufacturerServiceImpl implements ManufacturerService {

    private final ManufacturerRepository manufacturerRepository;

    private final ManufacturerMapper manufacturerMapper;

    @Autowired
    public ManufacturerServiceImpl(ManufacturerRepository manufacturerRepository, ManufacturerMapper manufacturerMapper) {
        this.manufacturerRepository = manufacturerRepository;
        this.manufacturerMapper = manufacturerMapper;
    }

    @Override
    @Transactional
    public List<ManufacturerDTO> getManufacturerList() {
        return this.manufacturerRepository.findAll()
                .stream()
                .map(this.manufacturerMapper::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public Optional<ManufacturerDTO> getManufacturerById(Long id) {
        return Optional.ofNullable(this.manufacturerMapper
                        .entityToDto(this.manufacturerRepository.findById(id).orElse(null)));
    }

    @Override
    @Transactional
    public void saveManufacturer(ManufacturerDTO manufacturerDTO) {
        this.manufacturerRepository.save(this.manufacturerMapper.dtoToEntity(manufacturerDTO));
    }

    @Override
    @Transactional
    public void deleteManufacturer(Long id) {
        this.manufacturerRepository.deleteById(id);
    }
}
