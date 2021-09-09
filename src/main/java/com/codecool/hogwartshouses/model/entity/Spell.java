package com.codecool.hogwartshouses.model.entity;

import com.codecool.hogwartshouses.model.entity.types.SpellType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Getter
@Setter
@Entity
@EqualsAndHashCode(exclude = "id")
@JsonIgnoreProperties("wands")
public class Spell {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private SpellType name;
    private int power;
    private boolean banned;

    @ManyToMany(mappedBy = "spells")
    private Set<Wand> wands = new HashSet<>();

    public boolean isBanned() {
        return banned;
    }
}
