package com.hanspistor.aoc.lib.y2020;


import com.hanspistor.aoc.common.AdventDay;

import java.util.function.Predicate;
import java.util.stream.Stream;

public class DayTwo2020 extends AdventDay {
    public DayTwo2020() {
        super(2, 2020);
    }

    @Override
    public String solvePartOne(Stream<String> input) {
        Predicate<String> isValidPassword = (line) -> {
            String[] parts = line.split(" ");
            String range = parts[0];
            int minNum = Integer.parseInt(range.split("-")[0]);
            int maxNum = Integer.parseInt(range.split("-")[1]);

            // Chop off the semicolon
            char policyLetter = parts[1].substring(0, parts[1].length() - 1).charAt(0);

            String password = parts[2];

            long numOfLetter = password.chars().filter(c -> c == policyLetter).count();

            return minNum <= numOfLetter && numOfLetter <= maxNum;
        };

        return Long.toString(input.filter(isValidPassword).count());
    }

    @Override
    public String solvePartTwo(Stream<String> input) {
        Predicate<String> isValidPassword = (line) -> {
            String[] parts = line.split(" ");
            String range = parts[0];

            // - 1 for 0-indexing
            int firstPos = Integer.parseInt(range.split("-")[0]) - 1;
            int lastPos = Integer.parseInt(range.split("-")[1]) - 1;

            // Chop off the semicolon
            char policyLetter = parts[1].substring(0, parts[1].length() - 1).charAt(0);

            String password = parts[2];

            return (password.charAt(firstPos) == policyLetter) ^ (password.charAt(lastPos) == policyLetter);
        };

        return Long.toString(input.filter(isValidPassword).count());
    }
}
