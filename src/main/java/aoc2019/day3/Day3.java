package aoc2019.day3;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.FileUtils;

import java.util.Map;

/**
 * --- Day 3: Crossed Wires ---
 * The gravity assist was successful, and you're well on your way to the Venus refuelling station. During the rush
 * back on Earth, the fuel management system wasn't completely installed, so that's next on the priority list.
 * <p>
 * Opening the front panel reveals a jumble of wires. Specifically, two wires are connected to a central port and
 * extend outward on a grid. You trace the path each wire takes as it leaves the central port, one wire per line of
 * text (your puzzle input).
 * <p>
 * The wires twist and turn, but the two wires occasionally cross paths. To fix the circuit, you need to find the
 * intersection point closest to the central port. Because the wires are on a grid, use the Manhattan distance for
 * this measurement. While the wires do technically cross right at the central port where they both start, this point
 * does not count, nor does a wire count as crossing with itself.
 * <p>
 * For example, if the first wire's path is R8,U5,L5,D3, then starting from the central port (o), it goes right 8, up
 * 5, left 5, and finally down 3:
 * <p>
 * ...........
 * ...........
 * ...........
 * ....+----+.
 * ....|....|.
 * ....|....|.
 * ....|....|.
 * .........|.
 * .o-------+.
 * ...........
 * Then, if the second wire's path is U7,R6,D4,L4, it goes up 7, right 6, down 4, and left 4:
 * <p>
 * ...........
 * .+-----+...
 * .|.....|...
 * .|..+--X-+.
 * .|..|..|.|.
 * .|.-X--+.|.
 * .|..|....|.
 * .|.......|.
 * .o-------+.
 * ...........
 * These wires cross at two locations (marked X), but the lower-left one is closer to the central port: its distance
 * is 3 + 3 = 6.
 * <p>
 * What is the Manhattan distance from the central port to the closest intersection?
 * <p>
 * --- Part Two ---
 * It turns out that this circuit is very timing-sensitive; you actually need to minimize the signal delay.
 * <p>
 * To do this, calculate the number of steps each wire takes to reach each intersection; choose the intersection
 * where the sum of both wires' steps is lowest. If a wire visits a position on the grid multiple times, use the
 * steps value from the first time it visits that position when calculating the total value of a specific intersection.
 * <p>
 * The number of steps a wire takes is the total number of grid squares the wire has entered to get to that location,
 * including the intersection being considered. Again consider the example from above:
 * <p>
 * ...........
 * .+-----+...
 * .|.....|...
 * .|..+--X-+.
 * .|..|..|.|.
 * .|.-X--+.|.
 * .|..|....|.
 * .|.......|.
 * .o-------+.
 * ...........
 * In the above example, the intersection closest to the central port is reached after 8+5+5+2 = 20 steps by the
 * first wire and 7+6+4+3 = 20 steps by the second wire for a total of 20+20 = 40 steps.
 * <p>
 * However, the top-right intersection is better: the first wire takes only 8+5+2 = 15 and the second wire takes only
 * 7+6+2 = 15, a total of 15+15 = 30 steps.
 * <p>
 * What is the fewest combined steps the wires must take to reach an intersection?
 */
public class Day3 {

    private static final Logger log = LoggerFactory.getLogger("Day 3");

    public static void main(String[] args) {

        String[] wires = FileUtils.getLinesAsArray("src/main/resources/2019/day3-wires");
        WireIntersector intersector = new WireIntersector();

        log.debug("Building Paths");
        long startTime = System.currentTimeMillis();
        Map<WireIntersector.Coords, Integer> intersections = intersector.calculateIntersections(wires[0].split(","),
                wires[1].split(","));
        log.debug("Paths built in {} ms", ((System.currentTimeMillis() - startTime)));

        log.info("Part 1 : Closest Intersection [{}]", intersector.getClosestIntersection(intersections));
        log.info("Part 2 : Fewest Steps [{}]", intersector.getFewestSteps(intersections));
    }
}
