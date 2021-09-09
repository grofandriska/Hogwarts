package com.codecool.hogwartshouses.service;

import com.codecool.hogwartshouses.model.DTO.WandDTO;
import com.codecool.hogwartshouses.model.entity.Wand;

import java.util.Set;

public interface WandService {

    Set<Wand> findAll();

    WandDTO findById(Long id);
}
