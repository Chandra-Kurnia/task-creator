package com.example.taskcreator.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class ListOfNumberPayload {
    private List<Integer> listOfNumber;
}
