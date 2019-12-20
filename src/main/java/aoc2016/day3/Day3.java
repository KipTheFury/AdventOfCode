package aoc2016.day3;

import common.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * --- Day 3: Squares With Three Sides ---
 * Now that you can think clearly, you move deeper into the labyrinth of hallways and office furniture that makes up
 * this part of Easter Bunny HQ. This must be a graphic design department; the walls are covered in specifications
 * for triangles.
 * <p>
 * Or are they?
 * <p>
 * The design document gives the side lengths of each triangle it describes, but... 5 10 25? Some of these aren't
 * triangles. You can't help but mark the impossible ones.
 * <p>
 * In a valid triangle, the sum of any two sides must be larger than the remaining side. For example, the "triangle"
 * given above is impossible, because 5 + 10 is not larger than 25.
 * <p>
 * In your puzzle input, how many of the listed triangles are possible?
 */
public class Day3 {

    private static final Logger log = LoggerFactory.getLogger("2016 - Day 3");

    public static void main(String[] args) {

        String[] triangles = FileUtils.getLinesAsArray("src/main/resources/2016/day3-triangles");

        TriangleValidator validator = new TriangleValidator();

        int validTriangles = validator.validateTriangles(triangles);

        log.info("Part 1 - Valid Triangles [{}]", validTriangles);

        int validVerticalTriangles = validator.validateVerticalTriangles(triangles);

        log.info("Part 2 - Valid Triangles [{}]", validVerticalTriangles);
    }
}
