package com.hanspistor.aoc.lib.y2020;


import com.hanspistor.aoc.common.AdventDay;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DayFour2020 extends AdventDay {
    public DayFour2020() {
        super(4, 2020);
    }

    @Override
    public String solvePartOne(Stream<String> input) {
        String allTogether = input.collect(Collectors.joining("\n"));
        List<String> passports = Arrays.asList(allTogether.split("\n\n"));

        long validPassports = passports.stream()
                .map(getMapPassportToMap())
                .filter(passport -> (passport.size() == 8) || (passport.size() == 7 && !passport.containsKey("cid")))
                .count();

        return Long.toString(validPassports);
    }


    public Predicate<Map<String, String>> validateByr() {
        return passport -> {
            String byrString = passport.get("byr");
            if (byrString == null) {
                return false;
            }

            int byr = Integer.parseInt(byrString);
            return byr >= 1920 && byr <= 2002;
        };
    }

    public Predicate<Map<String, String>> validateIyr() {
        return passport -> {
            String iyrString = passport.get("iyr");
            if (iyrString == null) {
                return false;
            }

            int iyr = Integer.parseInt(iyrString);
            return iyr >= 2010 && iyr <= 2020;
        };
    }

    public Predicate<Map<String, String>> validateEyr() {
        return passport -> {
            String eyrString = passport.get("eyr");
            if (eyrString == null) {
                return false;
            }

            int eyr = Integer.parseInt(eyrString);
            return eyr >= 2020 && eyr <= 2030;
        };
    }

    public Predicate<Map<String, String>> validateHgt() {
        return passport -> {
            String hgtString = passport.get("hgt");
            if (hgtString == null || hgtString.length() < 3) {
                return false;
            }

            List<String> validMeasurements = Arrays.asList("in cm".split(" "));

            String measureString = hgtString.substring(hgtString.length() - 2);
            if (!validMeasurements.contains(measureString)) {
                return false;
            }


            String valString = hgtString.substring(0, hgtString.length() - 2);

            int hgt = Integer.parseInt(valString);
            if (measureString.equals("in")) {
                return hgt >= 59 && hgt <= 76;
            } else if (measureString.equals("cm")) {
                return hgt >= 150 && hgt <= 193;
            }

            return false;
        };
    }

    public Predicate<Map<String, String>> validateHcl() {

        return passport -> {
            String hclString = passport.get("hcl");
            if (hclString == null || hclString.length() != 7) {
                return false;
            }

            List<String> validChars = Arrays.asList("0123456789abcdef".split(""));
            if (!(hclString.charAt(0) == '#')) {
                return false;
            }
            return hclString.substring(1).matches("^[0-9a-f]*$");
        };
    }

    public Predicate<Map<String, String>> validateEcl() {
        return passport -> {
            String eclString = passport.get("ecl");
            if (eclString == null) {
                return false;
            }
            List<String> validColors = Arrays.asList("amb", "blu", "brn", "gry", "grn", "hzl", "oth");

            return validColors.contains(eclString);
        };
    }

    public Predicate<Map<String, String>> validatePid() {
        return passport -> {
            String pidString = passport.get("pid");
            if (pidString == null || pidString.length() != 9) {
                return false;
            }
            return pidString.matches("^[0-9]*$");
        };
    }

    public Predicate<Map<String, String>> validateCid() {
        return passport -> passport.size() == 8 || passport.size() == 7 && !passport.containsKey("cid");
    }

    public Predicate<Map<String, String>> chainedFilters() {
        return validateByr().and(validateIyr()).and(validateEyr()).and(validateHgt()).and(validateHcl()).and(validateEcl()).and(validatePid()).and(validateCid());
    }

    @Override
    public String solvePartTwo(Stream<String> input) {
        String allTogether = input.collect(Collectors.joining("\n"));
        List<String> passports = Arrays.asList(allTogether.split("\n\n"));


        long validPassports = passports.stream()
                .map(getMapPassportToMap())
                .filter(chainedFilters())
                .count();

        return Long.toString(validPassports);
    }

    private Function<String, Map<String, String>> getMapPassportToMap() {
        Function<String, Map<String, String>> fn = passport -> {
            Map<String, String> values = new HashMap();
            String[] entries = passport.replace("\n", " ").split(" ");
            for (String entry : entries) {
                String key = entry.split(":")[0];
                String val = entry.split(":")[1];
                values.put(key, val);
            }

            return values;
        };

        return fn;
    }
}
