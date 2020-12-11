package com.hanspistor.aoc.lib.y2020;

import com.hanspistor.aoc.common.AdventDay;
import com.hanspistor.aoc.lib.utils.Point;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DayThree2020 extends AdventDay {
    public DayThree2020() {
        super(3, 2020);
    }

    @Override
    public String solvePartOne(Stream<String> input) {
        List<String> map = input.collect(Collectors.toList());
        Point slope = new Point(3, 1);
        int treesHit = getTreesHitForSlope(map, slope);
        return Integer.toString(treesHit);
    }

    @Override
    public String solvePartTwo(Stream<String> input) {
        List<String> map = input.collect(Collectors.toList());
        Point[] slopes = new Point[] {
                new Point(1, 1),
                new Point(3, 1),
                new Point(5, 1),
                new Point(7, 1),
                new Point(1, 2)
        };
        int treesEncounteredMultiplier = 1;
        for (Point slope: slopes) {
            treesEncounteredMultiplier *= getTreesHitForSlope(map, slope);

        }
        return Integer.toString(treesEncounteredMultiplier);
    }

    private int getTreesHitForSlope(List<String> map, Point slope) {
        int horizontalLength = map.get(0).length();
        Point pos = new Point(0, 0);

        int treesHit = 0;
        while (pos.y < map.size()) {
            if (map.get(pos.y).charAt(pos.x) == '#') {
                treesHit++;
            }

            pos.x = (pos.x + slope.x) % horizontalLength;
            pos.y += slope.y;
        }

        return treesHit;
    }
}

