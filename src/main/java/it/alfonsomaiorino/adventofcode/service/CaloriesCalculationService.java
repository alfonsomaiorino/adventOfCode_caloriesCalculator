package it.alfonsomaiorino.adventofcode.service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

public class CaloriesCalculationService {
    
    private static Long calculateTotalCalories(List<Long> elfCarryBagCalories) {
        return elfCarryBagCalories.stream().reduce(0L, (a, b) -> a + b);
    }

    public static List<List<Long>> createElfCarryBags() throws IOException {
        var classLoader = Thread.currentThread().getContextClassLoader();
        var url = classLoader.getResource("elf_bags.txt");
        var path = Paths.get(Optional.ofNullable(url).map(u -> {
            try {
                return u.toURI();
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
            return null;
        }).orElseThrow());

        var lines = Files.readAllLines(path, StandardCharsets.UTF_8);

        var listOfBags = new ArrayList<List<Long>>();
        var listOfEatingCalories = new AtomicReference<>(new ArrayList<Long>());
        lines.forEach(line -> checkLineOfFile(line, listOfBags, listOfEatingCalories));
        return listOfBags;
    }

    private static void checkLineOfFile(String line, List<List<Long>> listOfBags, AtomicReference<ArrayList<Long>> listOfEatingCalories) {
        if("".equals(line)) {
            listOfBags.add(listOfEatingCalories.get());
            listOfEatingCalories.set(new ArrayList<>());
        } else {
            listOfEatingCalories.get().add(Long.parseLong(line));
        }
    }

    public static Long getMaxCaloriesBag(List<List<Long>> bags) {
        return bags.stream()
                .map(CaloriesCalculationService::calculateTotalCalories)
                .mapToLong(l -> l)
                .max()
                .orElseThrow();
    }

    public static Long getSumOfTopElementsInListOfBag(List<List<Long>> bags, Integer numberOfValues) {
        return bags.stream()
                .map(CaloriesCalculationService::calculateTotalCalories)
                .sorted(Comparator.reverseOrder())
                .limit(numberOfValues)
                .reduce(0L, (a, b) -> a + b);
    }
    
}
