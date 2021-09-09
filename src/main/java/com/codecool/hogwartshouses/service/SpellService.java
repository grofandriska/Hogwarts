package com.codecool.hogwartshouses.service;

import com.codecool.hogwartshouses.model.DTO.SpellDTO;
import com.codecool.hogwartshouses.model.entity.Spell;
import org.springframework.stereotype.Service;

import java.util.Set;

public interface SpellService {
    Set<Spell> findAll();

    SpellDTO findById(Long id);
}
