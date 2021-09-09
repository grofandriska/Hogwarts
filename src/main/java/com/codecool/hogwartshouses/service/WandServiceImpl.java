package com.codecool.hogwartshouses.service;

import com.codecool.hogwartshouses.model.DTO.WandDTO;
import com.codecool.hogwartshouses.model.entity.Wand;
import com.codecool.hogwartshouses.repositories.WandRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class WandServiceImpl implements WandService {

    private final WandRepository wandRepository;

    private final ModelMapper modelMapper;

    public WandServiceImpl(WandRepository wandRepository, ModelMapper modelMapper) {
        this.wandRepository = wandRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Set<Wand> findAll() {
        return new HashSet<>(wandRepository.findAll());
    }

    @Override
    public WandDTO findById(Long id) {
        Optional<Wand> wand = wandRepository.findById(id);

        if (wand.isEmpty()) {
            throw new RuntimeException("Wand not found");
        }

        return modelMapper.map(wand.get(), WandDTO.class);
    }
}
