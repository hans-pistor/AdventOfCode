package com.hanspistor.aoc.lib.y2020;

import org.junit.Assert;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.function.Function;
import java.util.stream.Stream;

public class DayTwo2020Test {
    @Test
    public void testPartOne() {
        DayTwo2020 day = new DayTwo2020();

        InputStream inputStream = this.getClass().getResourceAsStream(day.getInputPath());
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
        Stream<String> stringStream = new BufferedReader(inputStreamReader).lines();

        Function<Stream<String>, String> fn = day::solvePartOne;
        String result = fn.apply(stringStream);
        Assert.assertEquals(result, "2");
    }

    @Test public void testPartTwo() {
        DayTwo2020 day = new DayTwo2020();

        InputStream inputStream = this.getClass().getResourceAsStream(day.getInputPath());
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
        Stream<String> stringStream = new BufferedReader(inputStreamReader).lines();

        Function<Stream<String>, String> fn = day::solvePartTwo;
        String result = fn.apply(stringStream);
        Assert.assertEquals(result, "1");
    }
}
