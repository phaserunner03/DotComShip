package com.game;

import java.util.*;

public class DotComGame {
    private GameHelper helper = new GameHelper();
    private List<DotCom> dotComsList = new ArrayList<>();
    private int numOfGuesses = 0;
    private int maxMoves;
    private int score = 0;
    private int gridSize;
    private Set<String> previousGuesses = new HashSet<>();

    public void setUpGame() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Select grid size: 1) 5x5  2) 7x7  3) 10x10");
        int gridChoice = scanner.nextInt();

        switch (gridChoice) {
            case 1:
                gridSize = 5;
                break;
            case 2:
                gridSize = 7;
                break;
            case 3:
                gridSize = 10;
                break;
            default:
                gridSize = 7;
        }

        System.out.println("Select difficulty: 1) Easy  2) Medium  3) Hard");
        int difficultyChoice = scanner.nextInt();

        switch (difficultyChoice) {
            case 1:
                maxMoves = gridSize * 3;
                break;
            case 2:
                maxMoves = gridSize * 2;
                break;
            case 3:
                maxMoves = (int) (gridSize * 1.5);
                break;
            default:
                maxMoves = gridSize * 2;
        }

        System.out.println("Welcome to SinkADotCom on a " + gridSize + "x" + gridSize + " grid!");

        int shipSize1 = (gridSize >= 7) ? 4 : 3;
        int shipSize2 = 3;
        int shipSize3 = 2;

        DotCom ship1 = new DotCom("Battleship", shipSize1);
        DotCom ship2 = new DotCom("Destroyer", shipSize2);
        DotCom ship3 = new DotCom("Submarine", shipSize3);

        dotComsList.add(ship1);
        dotComsList.add(ship2);
        dotComsList.add(ship3);

        for (DotCom dotCom : dotComsList) {
            ArrayList<String> newLocation = helper.placeDotCom(dotCom.getSize(), gridSize);
            dotCom.setLocationCells(newLocation);
            System.out.println(dotCom.getName() + " is placed at: " + newLocation);
        }
    }

    public void startPlaying() {
        while (!dotComsList.isEmpty() && numOfGuesses < maxMoves) {
            printGrid();
            String userGuess = helper.getUserInput("Enter a guess (row,col): ");

            if (!isValidGuess(userGuess)) {
                System.out.println("Invalid move! Try again.");
                continue;
            }

            if (previousGuesses.contains(userGuess)) {
                System.out.println("You already guessed this location! Try a new one.");
                continue;
            }
            previousGuesses.add(userGuess);

            numOfGuesses++;
            checkUserGuess(userGuess);
        }
        finishGame();
    }

    private void checkUserGuess(String userGuess) {
        String result = "miss";

        for (DotCom dotCom : dotComsList) {
            result = dotCom.checkYourself(userGuess);
            if (result.equals("hit")) {
                score += 10;
                break;
            }
            if (result.equals("kill")) {
                System.out.println("You sunk " + dotCom.getName() + "!");
                dotComsList.remove(dotCom);
                score += 50;
                break;
            }
        }
        System.out.println("Result: " + result);
    }

    private void finishGame() {
        System.out.println("\nGame Over!");
        System.out.println("Your final score: " + score);
        System.out.println("Total moves used: " + numOfGuesses);
    }

    private boolean isValidGuess(String guess) {
        return guess.matches("[0-" + (gridSize - 1) + "],[0-" + (gridSize - 1) + "]");
    }

    private void printGrid() {
        System.out.println("\nGrid:");
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                System.out.print("[ ] ");
            }
            System.out.println();
        }
    }
}