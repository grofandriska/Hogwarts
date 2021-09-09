package com.codecool.hogwartshouses.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@NoArgsConstructor
@ToString
public class Picture {
    private Long id;
    private String name;
    private Building building;
}
