package com.hanspistor.aoc.lib.y2020;


import com.hanspistor.aoc.common.AdventDay;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DayOne2020 extends AdventDay {

    public DayOne2020() {
        super(1, 2020);
    }

    public String solvePartOne(Stream<String> input) {
        List<Integer> ledger = input.map(Integer::parseInt).collect(Collectors.toList());
        for (int i = 0; i < ledger.size(); i++) {
            for (int j = i + 1; j < ledger.size(); j++) {
                if (ledger.get(i) + ledger.get(j) == 2020) {
                    return Long.toString(ledger.get(i) * ledger.get(j));
                }
            }
        }
        return "No solution found";
    }

    public String solvePartTwo(Stream<String> input) {
        List<Integer> ledger = input.map(Integer::parseInt).collect(Collectors.toList());
        for (int i = 0; i < ledger.size(); i++) {
            for (int j = i + 1; j < ledger.size(); j++) {
                for (int k = j+1; k < ledger.size(); k++) {
                    if (ledger.get(i) + ledger.get(j) + ledger.get(k) == 2020) {
                        return Long.toString(ledger.get(i) * ledger.get(j) * ledger.get(k));
                    }
                }
            }
        }
        return "No solution found";
    }
}
