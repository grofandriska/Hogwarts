package com.codecool.hogwartshouses.model.DTO;

import com.codecool.hogwartshouses.model.entity.types.SubjectType;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TeacherDTO {
    private Long id;
    private String name;
    private SubjectType subject;
    private Boolean witch;
    private Integer age;
    private Long wandId;

    @Override
    public String toString() {
        return "TeacherDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", subject=" + subject +
                ", witch=" + witch +
                ", age=" + age +
                ", wandId=" + wandId +
                '}';
    }
}
