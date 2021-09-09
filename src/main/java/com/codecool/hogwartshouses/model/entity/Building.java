package com.codecool.hogwartshouses.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties({"pictures", "rooms"})
public class Building {
    private Long id;
    private String name;
    private Integer numberOfRooms;
    private List<Picture> pictures;
    private List<Room> rooms;

    public boolean hasEmptyRoom() {
        return rooms.stream().filter(Room::hasEmptyBed).count() > 0;
    }
}
