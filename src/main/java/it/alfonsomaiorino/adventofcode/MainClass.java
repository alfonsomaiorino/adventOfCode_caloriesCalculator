package it.alfonsomaiorino.adventofcode;

import it.alfonsomaiorino.adventofcode.service.CaloriesCalculationService;

import java.io.IOException;

public class MainClass {

    public static void main(String[] args) throws IOException {
        var listOfBags = CaloriesCalculationService.createElfCarryBags();
        System.out.println("Max Calories " + CaloriesCalculationService.getMaxCaloriesBag(listOfBags));
        System.out.println("Total Calories of top three elves: " + CaloriesCalculationService.getSumOfTopElementsInListOfBag(listOfBags, 3));
    }

}
