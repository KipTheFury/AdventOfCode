package aoc2015.day3;

import common.Coords;
import common.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * --- Day 3: Perfectly Spherical Houses in a Vacuum ---
 * Santa is delivering presents to an infinite two-dimensional grid of houses.
 * <p>
 * He begins by delivering a present to the house at his starting location, and then an elf at the North Pole calls
 * him via radio and tells him where to move next. Moves are always exactly one house to the north (^), south (v),
 * east (>), or west (<). After each move, he delivers another present to the house at his new location.
 * <p>
 * However, the elf back at the north pole has had a little too much eggnog, and so his directions are a little off,
 * and Santa ends up visiting some houses more than once. How many houses receive at least one present?
 * <p>
 * For example:
 * <p>
 * > delivers presents to 2 houses: one at the starting location, and one to the east.
 * ^>v< delivers presents to 4 houses in a square, including twice to the house at his starting/ending location.
 * ^v^v^v^v^v delivers a bunch of presents to some very lucky children at only 2 houses.
 */
public class Day3 {

    private static final Logger log = LoggerFactory.getLogger("2015 - Day 3");

    public static void main(String[] args) {

        String directions = FileUtils.getLinesAsArray("src/main/resources/2015/day3-directions")[0];

        SantaTracker tracker = new SantaTracker();

        Map<Coords, Integer> part1 = tracker.parseDirections(directions, false);

        log.info("Part 1 - Houses delivered to [{}]", part1.values().size());

        Map<Coords, Integer> part2 = tracker.parseDirections(directions, true);

        log.info("Part 2 - Houses delivered to with RoboSanta [{}]", part2.values().size());
    }

}
