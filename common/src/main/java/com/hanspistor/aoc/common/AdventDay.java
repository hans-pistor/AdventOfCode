package com.hanspistor.aoc.common;

import java.util.stream.Stream;

public abstract class AdventDay implements Comparable<AdventDay> {
    private int day;
    private int year;

    public AdventDay(int day, int year) {
        this.day = day;
        this.year = year;
    }

    public int getDay() {
        return day;
    }

    public int getYear() {
        return year;
    }

    public String getInputPath() {
        return String.format("/input/%d/%02d.txt", this.year, this.day);
    }

    public abstract String solvePartOne(Stream<String> input);
    public abstract String solvePartTwo(Stream<String> input);


    // 2020 > 2019
    // day 1 > day 2
    @Override
    public int compareTo(AdventDay o) {
        if (this.year > o.year) {
            return 1;
        } else if (this.year == o.year) {
            if (this.day > o.day) {
                return 1;
            } else {
                return -1;
            }
        } else {
            return -1;
        }
    }
}
