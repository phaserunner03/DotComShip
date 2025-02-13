package com.game;

import java.util.ArrayList;
import java.util.List;

public class DotCom {
    private List<String> locationCells;
    private String name;
    private int size;

    public DotCom(String name, int size) {
        this.name = name;
        this.size = size;
        this.locationCells = new ArrayList<>();
    }

    public void setLocationCells(List<String> loc) {
        this.locationCells = loc;
    }

    public String checkYourself(String userInput) {
        if (locationCells.contains(userInput)) {
            locationCells.remove(userInput);
            if (locationCells.isEmpty()) {
                return "kill";
            }
            return "hit";
        }
        return "miss";
    }

    public String getName() {
        return name;
    }

    public int getSize() {
        return size;
    }
}
