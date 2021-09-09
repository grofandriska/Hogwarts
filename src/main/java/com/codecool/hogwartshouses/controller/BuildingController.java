package com.codecool.hogwartshouses.controller;

import com.codecool.hogwartshouses.converters.BuildingDTOToBuilding;
import com.codecool.hogwartshouses.converters.BuildingToBuildingDTO;
import com.codecool.hogwartshouses.model.DTO.BuildingDTO;
import com.codecool.hogwartshouses.model.entity.Building;
import com.codecool.hogwartshouses.service.BuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/buildings")
public class BuildingController {

    private final BuildingService buildingService;
    private final BuildingDTOToBuilding buildingDTOToBuilding;
    private final BuildingToBuildingDTO buildingToBuildingDTO;

    @Autowired
    public BuildingController(BuildingService buildingService, BuildingDTOToBuilding buildingDTOToBuilding, BuildingToBuildingDTO buildingToBuildingDTO) {
        this.buildingService = buildingService;
        this.buildingDTOToBuilding = buildingDTOToBuilding;
        this.buildingToBuildingDTO = buildingToBuildingDTO;
    }

    @GetMapping
    public String getBuildingPage(Model model) {
        model.addAttribute("buildings", buildingService.listBuildings());
        model.addAttribute("building", new BuildingDTO());
        return "buildings";
    }

    @PostMapping
    public String saveOrUpdateBuilding(@ModelAttribute BuildingDTO buildingDTO) {
        Building building = buildingDTOToBuilding.convert(buildingDTO);
        buildingService.addBuilding(building);
        return "redirect:/buildings";
    }

    @GetMapping("{id}/update")
    public String updateBuilding(@PathVariable Long id, Model model) {
        BuildingDTO buildingDTO = buildingToBuildingDTO.convert(buildingService.getBuilding(id));

        model.addAttribute("building", buildingDTO);
        model.addAttribute("buildings", buildingService.listBuildings());
        return "buildings";
    }

    @GetMapping("/{id}/delete")
    public String deleteBuildingById(@PathVariable Long id) {
        buildingService.deleteBuilding(id);
        return "redirect:/buildings";
    }

    @GetMapping("/all")
    @ResponseBody
    public List<Building> listBuildings() {
        return buildingService.listBuildings();
    }

    @GetMapping("/{id}/get")
    @ResponseBody
    public Building getBuilding(@PathVariable("id") Long id) {
        return buildingService.getBuilding(id);
    }

    @PostMapping("/add")
    @ResponseBody
    void addBuilding(@RequestBody Building building) {
        buildingService.addBuilding(building);
    }

    @PutMapping("/{id}/update")
    @ResponseBody
    public void updateBuilding(@PathVariable("id") Long id, @RequestBody Building building) {
        buildingService.updateBuilding(building, id);
    }

    @DeleteMapping("{id}/delete")
    @ResponseBody
    public void deleteById(@PathVariable("id") Long id) {
        buildingService.deleteBuilding(id);
    }
}
