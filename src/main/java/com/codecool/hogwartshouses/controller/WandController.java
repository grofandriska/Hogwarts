package com.codecool.hogwartshouses.controller;

import com.codecool.hogwartshouses.model.DTO.WandDTO;
import com.codecool.hogwartshouses.model.entity.Wand;
import com.codecool.hogwartshouses.service.WandService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@Controller
@RequestMapping("/wands")
public class WandController {

    private final WandService wandService;

    public WandController(WandService wandService) {
        this.wandService = wandService;
    }

    @GetMapping("/all")
    @ResponseBody
    public Set<Wand> getWands() {
        return wandService.findAll();
    }

    @GetMapping("/{id}")
    @ResponseBody
    public WandDTO getWand(@PathVariable("id") Long id) {
        return wandService.findById(id);
    }

}
