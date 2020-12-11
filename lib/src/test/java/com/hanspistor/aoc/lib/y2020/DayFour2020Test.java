package com.hanspistor.aoc.lib.y2020;

import org.junit.Assert;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Stream;

public class DayFour2020Test {
    @Test
    public void testPartOne() {
        DayFour2020 day = new DayFour2020();

        InputStream inputStream = this.getClass().getResourceAsStream(day.getInputPath());
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
        Stream<String> stringStream = new BufferedReader(inputStreamReader).lines();

        Function<Stream<String>, String> fn = day::solvePartOne;
        String result = fn.apply(stringStream);
        Assert.assertEquals("2", result);
    }

    @Test public void testPartTwo() {
        DayFour2020 day = new DayFour2020();
        Map<String, String> values = new HashMap<>();

        values.put("byr", "2002");
        Assert.assertEquals(1, Stream.of(values).filter(day.validateByr()).count());

        values.put("byr", "2003");
        Assert.assertEquals(0, Stream.of(values).filter(day.validateByr()).count());

        values.put("hgt", "60in");
        Assert.assertEquals(1, Stream.of(values).filter(day.validateHgt()).count());
        values.put("hgt", "190cm");
        Assert.assertEquals(1, Stream.of(values).filter(day.validateHgt()).count());
        values.put("hgt", "190in");
        Assert.assertEquals(0, Stream.of(values).filter(day.validateHgt()).count());
        values.put("hgt", "190");
        Assert.assertEquals(0, Stream.of(values).filter(day.validateHgt()).count());

        values.put("hcl", "#123abc");
        Assert.assertEquals(1, Stream.of(values).filter(day.validateHcl()).count());
        values.put("hcl", "#123abz");
        Assert.assertEquals(0, Stream.of(values).filter(day.validateHcl()).count());
        values.put("hcl", "123abc");
        Assert.assertEquals(0, Stream.of(values).filter(day.validateHcl()).count());

        values.put("ecl", "brn");
        Assert.assertEquals(1, Stream.of(values).filter(day.validateEcl()).count());
        values.put("ecl", "wat");
        Assert.assertEquals(0, Stream.of(values).filter(day.validateEcl()).count());

        values.put("pid", "000000001");
        Assert.assertEquals(1, Stream.of(values).filter(day.validatePid()).count());
        values.put("pid", "0123456789");
        Assert.assertEquals(0, Stream.of(values).filter(day.validatePid()).count());

        values.clear();
        values.put("eyr", "1972");
        values.put("cid", "100");
        values.put("hcl", "#18171d");
        values.put("ecl", "amb");
        values.put("hgt", "170");
        values.put("pid", "186cm");
        values.put("iyr", "2018");
        values.put("byr", "1926");
        Assert.assertEquals(0, Stream.of(values).filter(day.chainedFilters()).count());

        values.clear();
        values.put("eyr", "2030");
        values.put("hcl", "#623a2f");
        values.put("ecl", "grn");
        values.put("hgt", "74in");
        values.put("pid", "087499704");
        values.put("iyr", "2012");
        values.put("byr", "1980");
        Assert.assertEquals(1, Stream.of(values).filter(day.chainedFilters()).count());


        values.clear();
        values.put("eyr", "2029");
        values.put("cid", "129");
        values.put("hcl", "#a97842");
        values.put("ecl", "blu");
        values.put("hgt", "165cm");
        values.put("pid", "896056539");
        values.put("iyr", "2014");
        values.put("byr", "1989");
        Assert.assertEquals(1, Stream.of(values).filter(day.chainedFilters()).count());

        values.clear();
        values.put("eyr", "2022");
        values.put("cid", "88");
        values.put("hcl", "#888785");
        values.put("ecl", "hzl");
        values.put("hgt", "164cm");
        values.put("pid", "545766238");
        values.put("iyr", "2015");
        values.put("byr", "2001");
        Assert.assertEquals(1, Stream.of(values).filter(day.chainedFilters()).count());

    }
}
