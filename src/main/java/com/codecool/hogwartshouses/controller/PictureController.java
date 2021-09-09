package com.codecool.hogwartshouses.controller;

import com.codecool.hogwartshouses.converters.PictureDTOToPicture;
import com.codecool.hogwartshouses.converters.PictureToPictureDTO;
import com.codecool.hogwartshouses.model.DTO.PictureDTO;
import com.codecool.hogwartshouses.model.entity.Picture;
import com.codecool.hogwartshouses.service.BuildingService;
import com.codecool.hogwartshouses.service.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/pictures")
public class PictureController {

    private final PictureService pictureService;
    private final BuildingService buildingService;
    private final PictureToPictureDTO pictureToPictureDTO;
    private final PictureDTOToPicture pictureDTOToPicture;

    @Autowired
    public PictureController(PictureService pictureService,
                             BuildingService buildingService,
                             PictureToPictureDTO pictureToPictureDTO,
                             PictureDTOToPicture pictureDTOToPicture) {
        this.pictureService = pictureService;
        this.buildingService = buildingService;
        this.pictureToPictureDTO = pictureToPictureDTO;
        this.pictureDTOToPicture = pictureDTOToPicture;
    }

    @GetMapping
    public String getPicturePage(Model model) {
        model.addAttribute("pictures", pictureService.listPictures());
        model.addAttribute("picture", new PictureDTO());
        model.addAttribute("buildings", buildingService.listBuildings());
        return "pictures";
    }

    @PostMapping
    public String saveOrUpdatePicture(@ModelAttribute PictureDTO pictureDTO) {
        Picture picture = pictureDTOToPicture.convert(pictureDTO);

        pictureService.addPicture(picture);
        return "redirect:pictures";
    }

    @GetMapping("/{id}/update")
    public String updatePicture(@PathVariable Long id, Model model) {
        PictureDTO pictureDTO = pictureToPictureDTO.convert(pictureService.getPicture(id));

        model.addAttribute("picture", pictureDTO);
        model.addAttribute("pictures", pictureService.listPictures());
        model.addAttribute("buildings", buildingService.listBuildings());
        return "pictures";
    }

    @GetMapping("/{id}/delete")
    public String deletePictureById(@PathVariable Long id) {
        pictureService.deletePicture(id);
        return "redirect:/pictures";
    }

    @GetMapping("/all")
    @ResponseBody
    public List<Picture> listPictures() {
        return pictureService.listPictures();
    }

    @GetMapping("/{id}/get")
    @ResponseBody
    public Picture getPicture(@PathVariable("id") Long id) {
        return pictureService.getPicture(id);
    }

    @PostMapping("/add")
    @ResponseBody
    void addPicture(@RequestBody Picture picture) {
        pictureService.addPicture(picture);
    }

    @PutMapping("/{id}/update")
    @ResponseBody
    public void updatePicture(@PathVariable("id") Long id, @RequestBody Picture picture) {
        pictureService.updatePicture(picture, id);
    }

    @DeleteMapping("{id}/delete")
    @ResponseBody
    public void deletePicture(@PathVariable("id") Long id) {
        pictureService.deletePicture(id);
    }
}
