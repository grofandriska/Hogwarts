package com.codecool.hogwartshouses.controller;

import com.codecool.hogwartshouses.converters.RoomDTOToRoom;
import com.codecool.hogwartshouses.converters.RoomToRoomDTO;
import com.codecool.hogwartshouses.model.DTO.RoomDTO;
import com.codecool.hogwartshouses.model.entity.Room;
import com.codecool.hogwartshouses.service.BuildingService;
import com.codecool.hogwartshouses.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/rooms")
public class RoomController {

    private final RoomService roomService;
    private final BuildingService buildingService;
    private final RoomToRoomDTO roomToRoomDTO;
    private final RoomDTOToRoom roomDTOToRoom;

    @Autowired
    public RoomController(RoomService roomService, BuildingService buildingService, RoomToRoomDTO roomToRoomDTO, RoomDTOToRoom roomDTOToRoom) {
        this.roomService = roomService;
        this.buildingService = buildingService;
        this.roomToRoomDTO = roomToRoomDTO;
        this.roomDTOToRoom = roomDTOToRoom;
    }

    @GetMapping
    public String getAllRoom(Model model) {
        model.addAttribute("rooms", roomService.getAll());
        model.addAttribute("room", new RoomDTO());
        model.addAttribute("buildings", buildingService.listBuildings());
        return "rooms";
    }

    @PostMapping
    public String saveOrUpdateRoom(@ModelAttribute RoomDTO roomDTO) {
        Room room = roomDTOToRoom.convert(roomDTO);

        roomService.save(room);
        return "redirect:/rooms";
    }

    @GetMapping("/{id}/update")
    public String updateRoom(@PathVariable Long id, Model model) {
        RoomDTO roomDTO = roomToRoomDTO.convert(roomService.getById(id));

        model.addAttribute("room", roomDTO);
        model.addAttribute("rooms", roomService.getAll());
        model.addAttribute("buildings", buildingService.listBuildings());
        return "rooms";
    }

    @GetMapping("{id}/delete")
    public String deleteRoomByID(@PathVariable Long id) {
        roomService.deleteById(id);
        return "redirect:/rooms";
    }

    @GetMapping("/all")
    @ResponseBody
    public List<Room> getAllRoom() {
        return roomService.getAll();
    }

    @PostMapping("/add")
    @ResponseBody
    public void createRoom(@RequestBody Room room) {
        roomService.save(room);
    }

    @GetMapping("/{id}/get")
    @ResponseBody
    public Room findRoomById(@PathVariable(value = "id") Long roomId) {
        return roomService.getById(roomId);
    }

    @DeleteMapping("/{id}/delete")
    @ResponseBody
    public void deleteRoomById(@PathVariable(value = "id") Long roomId) {
        roomService.deleteById(roomId);
    }

    @PutMapping("/{id}/update")
    @ResponseBody
    public void updateRoomById(@PathVariable(value = "id") Long roomId, @RequestBody Room room) {
        room.setId(roomId);
        roomService.save(room);
    }

    @GetMapping("/available")
    @ResponseBody
    public List<Room> getRoomWithEmptyBed() {
        return roomService.getAvailableRooms();
    }

    @GetMapping("/rat-owners")
    @ResponseBody
    public List<Room> getRoomWithRat() {
        return roomService.getNoCatOrOwlRooms();
    }

}
