package com.hanspistor.aoc.driver;


import com.hanspistor.aoc.common.AdventDay;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Driver {

    public static void printSolutionForDay(AdventDay day) {
        InputStream inputStream = day.getClass().getResourceAsStream(day.getInputPath());
        if (inputStream == null) {
            throw new IllegalStateException(String.format("The input file for Advent Day %02d in %d does not exist", day.getDay(), day.getYear()));
        }
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
        List<String> input = new BufferedReader(inputStreamReader).lines().collect(Collectors.toList());

        Function<Stream<String>, String> fn = day::solvePartOne;
        String result = fn.apply(input.stream());
        System.out.printf("Solution for 12/%02d/%d, part %d: %s\n", day.getDay(), day.getYear(), 1, result);

        fn = day::solvePartTwo;
        result = fn.apply(input.stream());
        System.out.printf("Solution for 12/%02d/%d, part %d: %s\n", day.getDay(), day.getYear(), 2, result);

        System.out.println();
    }

    public static void printSolutionForAllDays(Stream<AdventDay> days) {
        days.forEach(Driver::printSolutionForDay);
    }
}
