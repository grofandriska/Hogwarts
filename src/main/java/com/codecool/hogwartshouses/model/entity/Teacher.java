package com.codecool.hogwartshouses.model.entity;

import com.codecool.hogwartshouses.model.entity.types.SubjectType;
import lombok.*;

import javax.persistence.*;


@Entity
@Getter
@Setter
@EqualsAndHashCode(exclude = "id")
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private SubjectType subject;

    private Boolean witch;

    private Integer age;

    @OneToOne
    private Wand wand;

    @Override
    public String toString() {
        return "Teacher{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", subject=" + subject +
                ", witch=" + witch +
                ", age=" + age +
                ", wand=" + wand +
                '}';
    }
}
