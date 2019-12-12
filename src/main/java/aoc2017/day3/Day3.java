package aoc2017.day3;

import common.Coords;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * --- Day 3: Spiral Memory ---
 * You come across an experimental new kind of memory stored on an infinite two-dimensional grid.
 * <p>
 * Each square on the grid is allocated in a spiral pattern starting at a location marked 1 and then counting up
 * while spiraling outward. For example, the first few squares are allocated like this:
 * <p>
 * 17  16  15  14  13
 * 18   5   4   3  12
 * 19   6   1   2  11
 * 20   7   8   9  10
 * 21  22  23---> ...
 * While this is very space-efficient (no squares are skipped), requested data must be carried back to square 1 (the
 * location of the only access port for this memory system) by programs that can only move up, down, left, or right.
 * They always take the shortest path: the Manhattan Distance between the location of the data and square 1.
 * <p>
 * For example:
 * <p>
 * Data from square 1 is carried 0 steps, since it's at the access port.
 * Data from square 12 is carried 3 steps, such as: down, left, left.
 * Data from square 23 is carried only 2 steps: up twice.
 * Data from square 1024 must be carried 31 steps.
 * How many steps are required to carry the data from the square identified in your puzzle input all the way to the
 * access port?
 * <p>
 * Your puzzle input is 368078.
 * <p>
 * --- Part Two ---
 * As a stress test on the system, the programs here clear the grid and then store the value 1 in square 1. Then, in
 * the same allocation order as shown above, they store the sum of the values in all adjacent squares, including
 * diagonals.
 * <p>
 * So, the first few squares' values are chosen as follows:
 * <p>
 * Square 1 starts with the value 1.
 * Square 2 has only one adjacent filled square (with value 1), so it also stores 1.
 * Square 3 has both of the above squares as neighbors and stores the sum of their values, 2.
 * Square 4 has all three of the aforementioned squares as neighbors and stores the sum of their values, 4.
 * Square 5 only has the first and fourth squares as neighbors, so it gets the value 5.
 * Once a square is written, its value does not change. Therefore, the first few squares would receive the following
 * values:
 * <p>
 * 147  142  133  122   59
 * 304    5    4    2   57
 * 330   10    1    1   54
 * 351   11   23   25   26
 * 362  747  806--->   ...
 * What is the first value written that is larger than your puzzle input?
 */
public class Day3 {

    private static final Logger log = LoggerFactory.getLogger("2017 Day 3");

    public static void main(String[] args) {

        int input = 368078;

        SpiralGenerator generator = new SpiralGenerator();

        List<Coords> spiral = generator.generateSpiral(input);
        int manhattanDistance = new Coords(0, 0).calculateManhattanDistance(spiral.get(input - 1));

        log.info("Part 1 - Manhattan Distance [{}]", manhattanDistance);
    }
}
