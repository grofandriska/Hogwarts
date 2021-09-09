package com.codecool.hogwartshouses.exception;

public class TeacherNotFoundException extends RuntimeException{
    public TeacherNotFoundException(String message) {
        super(message);
    }
}
