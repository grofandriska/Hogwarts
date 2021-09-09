package com.codecool.hogwartshouses.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class MyControllAdvice {
    private Logger logger = LoggerFactory.getLogger(MyControllAdvice.class);

    @ExceptionHandler(StudentNotFoundException.class)
    public ResponseEntity<String> handleStudentException(StudentNotFoundException studentNotFoundException) {
        logger.error(studentNotFoundException.getMessage());
        return new ResponseEntity<>(studentNotFoundException.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RoomNotFoundException.class)
    public ResponseEntity<String> handleRoomException(RoomNotFoundException roomNotFoundException) {
        return new ResponseEntity<>(roomNotFoundException.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RecipeNotFoundException.class)
    public ResponseEntity<String> handleRecipeException(RecipeNotFoundException recipeNotFoundException) {
        return new ResponseEntity<>(recipeNotFoundException.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(TeacherNotFoundException.class)
    public ResponseEntity<String> handleTeacherException(TeacherNotFoundException teacherNotFoundException) {
        return new ResponseEntity<>(teacherNotFoundException.getMessage(), HttpStatus.BAD_REQUEST);
    }


}
