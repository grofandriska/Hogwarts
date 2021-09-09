package com.codecool.hogwartshouses.service;

import com.codecool.hogwartshouses.model.DTO.SpellDTO;
import com.codecool.hogwartshouses.model.entity.Spell;
import com.codecool.hogwartshouses.repositories.SpellRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class SpellServiceImpl implements SpellService {

    private final SpellRepository spellRepository;
    private final ModelMapper modelMapper;

    public SpellServiceImpl(SpellRepository spellRepository, ModelMapper modelMapper) {
        this.spellRepository = spellRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Set<Spell> findAll() {
        return new HashSet<>(spellRepository.findAll());
    }

    @Override
    public SpellDTO findById(Long id) {
        Optional<Spell> temp = spellRepository.findById(id);

        if (temp.isEmpty()) {
            throw new RuntimeException("Spell not found!");
        }

        return convertEntityToDTO(temp);
    }

    public SpellDTO convertEntityToDTO(Optional<Spell> spell) {
        return modelMapper.map(spell.get(), SpellDTO.class);
    }

}
