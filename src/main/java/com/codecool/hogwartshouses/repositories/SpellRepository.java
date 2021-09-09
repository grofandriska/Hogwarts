package com.codecool.hogwartshouses.repositories;

import com.codecool.hogwartshouses.model.entity.Spell;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpellRepository extends JpaRepository<Spell,Long> {
}
