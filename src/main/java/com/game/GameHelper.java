package com.game;

import java.util.*;

public class GameHelper {
    private Random rand = new Random();

    public String getUserInput(String prompt) {
        System.out.print(prompt);
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public ArrayList<String> placeDotCom(int shipSize, int gridSize) {
        ArrayList<String> locations = new ArrayList<>();
        int row, col;
        boolean horizontal = rand.nextBoolean();
        boolean fits;

        do {
            locations.clear();
            row = rand.nextInt(gridSize);
            col = rand.nextInt(gridSize);
            fits = true;

            for (int i = 0; i < shipSize; i++) {
                if (horizontal) {
                    if (col + i >= gridSize) {
                        fits = false;
                        break;
                    }
                    locations.add(row + "," + (col + i));
                } else {
                    if (row + i >= gridSize) {
                        fits = false;
                        break;
                    }
                    locations.add((row + i) + "," + col);
                }
            }
        } while (!fits);

        return locations;
    }
}
