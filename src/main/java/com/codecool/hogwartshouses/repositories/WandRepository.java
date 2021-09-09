package com.codecool.hogwartshouses.repositories;

import com.codecool.hogwartshouses.model.entity.Wand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WandRepository extends JpaRepository<Wand, Long> {
}
