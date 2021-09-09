package com.codecool.hogwartshouses.controller;

import com.codecool.hogwartshouses.converters.StudentDTOToStudent;
import com.codecool.hogwartshouses.converters.StudentToStudentDTO;
import com.codecool.hogwartshouses.model.DTO.StudentDTO;
import com.codecool.hogwartshouses.model.entity.Building;
import com.codecool.hogwartshouses.model.entity.Room;
import com.codecool.hogwartshouses.model.entity.Student;
import com.codecool.hogwartshouses.model.entity.types.HouseType;
import com.codecool.hogwartshouses.model.entity.types.PetType;
import com.codecool.hogwartshouses.service.BuildingService;
import com.codecool.hogwartshouses.service.RoomService;
import com.codecool.hogwartshouses.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/students")
public class StudentController {
    private final BuildingService buildingService;
    private final StudentService studentService;
    private final RoomService roomService;
    private final StudentDTOToStudent studentDTOToStudent;
    private final StudentToStudentDTO studentToStudentDTO;

    @Autowired
    public StudentController(BuildingService buildingService,
                             StudentService studentService,
                             RoomService roomService,
                             StudentDTOToStudent studentDTOToStudent,
                             StudentToStudentDTO studentToStudentDTO) {
        this.buildingService = buildingService;
        this.studentService = studentService;
        this.roomService = roomService;
        this.studentDTOToStudent = studentDTOToStudent;
        this.studentToStudentDTO = studentToStudentDTO;
    }

    @GetMapping
    public String getStudentPage(Model model) {
        model.addAttribute("students", studentService.getAll());
        model.addAttribute("student", new StudentDTO());
        setModel(model);
        return "students";
    }

    @PostMapping
    public String saveOrUpdateStudent(@ModelAttribute StudentDTO studentDTO) {
        Student student = studentDTOToStudent.convert(studentDTO);

        studentService.save(student);
        return "redirect:students";
    }

    @GetMapping("/{id}/update")
    public String updateStudent(@PathVariable Long id, Model model) {
        StudentDTO studentDTO = studentToStudentDTO.convert(studentService.getById(id));

        model.addAttribute("students", studentService.getAll());
        model.addAttribute("student", studentDTO);
        setModel(model);
        return "students";
    }

    @GetMapping("/{id}/delete")
    public String deleteStudentById(@PathVariable Long id) {
        studentService.deleteById(id);
        return "redirect:/students";
    }

    @GetMapping("/all")
    @ResponseBody
    public List<Student> getAll() {
        return studentService.getAll();
    }

    @GetMapping("/{id}/get")
    @ResponseBody
    public Student getById(@PathVariable("id") Long id) {
        return studentService.getById(id);
    }

    @PostMapping("/add")
    @ResponseBody
    public void save(@RequestBody Student student) {
        System.out.println(student.toString());
        studentService.save(student);
    }

    @PutMapping("/{id}/update")
    @ResponseBody
    public void save(@PathVariable("id") Long id, @RequestBody Student student) {
        student.setId(id);
        studentService.save(student);
    }

    @DeleteMapping("{id}/delete")
    @ResponseBody
    public void deleteById(@PathVariable("id") Long id) {
        studentService.deleteById(id);
    }

    private void setModel(Model model) {
        model.addAttribute("houseTypes", HouseType.values());
        model.addAttribute("petTypes", PetType.values());
        model.addAttribute("buildings", buildingService.listBuildings().stream().filter(Building::hasEmptyRoom).collect(Collectors.toList()));
        model.addAttribute("rooms", roomService.getAll().stream().filter(Room::hasEmptyBed).collect(Collectors.toList()));
    }
}
