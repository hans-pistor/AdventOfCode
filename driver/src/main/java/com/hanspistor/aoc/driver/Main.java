package com.hanspistor.aoc.driver;

import com.hanspistor.aoc.common.AdventDay;
import io.github.classgraph.ClassGraph;
import io.github.classgraph.ClassInfo;
import io.github.classgraph.ClassInfoList;
import io.github.classgraph.ScanResult;
import picocli.CommandLine;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.Function;
import java.util.stream.Stream;

@CommandLine.Command(name = "AdventCode", subcommands = {DayCommand.class, YearCommand.class})
public class Main {
    protected static Set<String> targets = new HashSet<String>();

    static {
        try {
            ScanResult scanResult = new ClassGraph()
                    .addClassLoader(ClassLoader.getSystemClassLoader())
                    .enableAnnotationInfo()
                    .enableMethodInfo()
                    .initializeLoadedClasses()
                    .scan();
            ClassInfoList dayClasses = scanResult.getSubclasses(AdventDay.class.getName()).getStandardClasses();
            for (ClassInfo info : dayClasses) {
                targets.add(info.getName());
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }
    }

    public static void main(String[] args) {
        int exitCode = new CommandLine(new Main()).execute(args);
    }

    public static Stream<AdventDay> getDaysStream() {
        Set<AdventDay> days = new TreeSet<>();
        for (String className : targets) {
            try {
                Class<AdventDay> clazz = (Class<AdventDay>) Class.forName(className);
                AdventDay day = clazz.getDeclaredConstructor().newInstance();

                days.add(day);
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }

        return days.stream();
    }
}

@CommandLine.Command(name = "year", description = "Runs all days found for year")
class YearCommand implements Runnable {
    @CommandLine.Parameters(index = "0") private int year;

    @Override
    public void run() {

        Stream<AdventDay> yearStream = Main.getDaysStream().filter(day -> day.getYear() == this.year);

        Driver.printSolutionForAllDays(yearStream);
    }

}

@CommandLine.Command(name = "day", description = "Runs one day")
class DayCommand implements Runnable {

    @CommandLine.Option(names = {"-y", "--year"}, description = "Year")
    private int year;

    @CommandLine.Option(names = {"-d", "--day"}, description = "Day")
    private int day;

    @Override
    public void run() {
        Optional<AdventDay> adventDay = Main.getDaysStream().filter(day -> day.getDay() == this.day).filter(day -> day.getYear() == this.year).findFirst();

        if (!adventDay.isPresent()) {
            throw new IllegalStateException(String.format("Advent Day %02d in %d does not exist", this.day, this.year));
        }

        Driver.printSolutionForDay(adventDay.get());
    }
}