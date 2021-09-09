package com.codecool.hogwartshouses.model.entity;

import com.codecool.hogwartshouses.model.entity.types.ColorType;
import com.codecool.hogwartshouses.model.entity.types.WoodType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@JsonIgnoreProperties("spells")
public class Wand {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private WoodType woodType;
    private ColorType colorType;

    @ManyToMany
    @JoinTable(name = "wand_spell",
            joinColumns = @JoinColumn(name = "wand_id"),
            inverseJoinColumns = @JoinColumn(name = "spell_id"))
    private Set<Spell> spells = new HashSet<>();
}
