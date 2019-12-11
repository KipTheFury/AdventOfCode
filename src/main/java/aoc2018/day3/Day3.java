package aoc2018.day3;

import common.Coords;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.FileUtils;

import java.util.List;
import java.util.Map;

/**
 * --- Day 3: No Matter How You Slice It ---
 * The Elves managed to locate the chimney-squeeze prototype fabric for Santa's suit (thanks to someone who helpfully
 * wrote its box IDs on the wall of the warehouse in the middle of the night). Unfortunately, anomalies are still
 * affecting them - nobody can even agree on how to cut the fabric.
 * <p>
 * The whole piece of fabric they're working on is a very large square - at least 1000 inches on each side.
 * <p>
 * Each Elf has made a claim about which area of fabric would be ideal for Santa's suit. All claims have an ID and
 * consist of a single rectangle with edges parallel to the edges of the fabric. Each claim's rectangle is defined as
 * follows:
 * <p>
 * The number of inches between the left edge of the fabric and the left edge of the rectangle.
 * The number of inches between the top edge of the fabric and the top edge of the rectangle.
 * The width of the rectangle in inches.
 * The height of the rectangle in inches.
 * A claim like #123 @ 3,2: 5x4 means that claim ID 123 specifies a rectangle 3 inches from the left edge, 2 inches
 * from the top edge, 5 inches wide, and 4 inches tall. Visually, it claims the square inches of fabric represented
 * by # (and ignores the square inches of fabric represented by .) in the diagram below:
 * <p>
 * ...........
 * ...........
 * ...#####...
 * ...#####...
 * ...#####...
 * ...#####...
 * ...........
 * ...........
 * ...........
 * The problem is that many of the claims overlap, causing two or more claims to cover part of the same areas. For
 * example, consider the following claims:
 * <p>
 * #1 @ 1,3: 4x4
 * #2 @ 3,1: 4x4
 * #3 @ 5,5: 2x2
 * Visually, these claim the following areas:
 * <p>
 * ........
 * ...2222.
 * ...2222.
 * .11XX22.
 * .11XX22.
 * .111133.
 * .111133.
 * ........
 * The four square inches marked with X are claimed by both 1 and 2. (Claim 3, while adjacent to the others, does not
 * overlap either of them.)
 * <p>
 * If the Elves all proceed with their own plans, none of them will have enough fabric. How many square inches of
 * fabric are within two or more claims?
 */
public class Day3 {

    private static final Logger log = LoggerFactory.getLogger("2018 - Day 3");

    public static void main(String[] args) {

        String[] input = FileUtils.getLinesAsArray("src/main/resources/2018/day3-claims");
        ClaimMapper claimMapper = new ClaimMapper();
        List<ClaimMapper.Claim> claims = claimMapper.parseClaims(input);

        Map<Coords, Integer> overlaps = claimMapper.findOverlaps(claims);
        long overlappingSquares = overlaps.values().stream().filter(count -> count > 1).count();

        log.info("Part 1 - Number of overlaps [{}]", overlappingSquares);

        String claimId = claimMapper.findClaimWithNoOverlaps(claims, overlaps);

        log.info("Part 2 - Claim with no overlaps [{}]", claimId);
    }
}
