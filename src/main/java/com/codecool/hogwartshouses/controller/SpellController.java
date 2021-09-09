package com.codecool.hogwartshouses.controller;

import com.codecool.hogwartshouses.model.DTO.SpellDTO;
import com.codecool.hogwartshouses.model.entity.Spell;
import com.codecool.hogwartshouses.service.SpellService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Set;

@Controller
@RequestMapping("/spells")
public class SpellController {

    private final SpellService service;

    public SpellController(SpellService service) {
        this.service = service;
    }

    @GetMapping("/all")
    @ResponseBody
    public Set<Spell> getSpells() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    @ResponseBody
    public SpellDTO getSpell(@PathVariable("id") Long id) {
        return service.findById(id);
    }

}
