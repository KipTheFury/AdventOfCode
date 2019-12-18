package aoc2015.day1;

import common.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * --- Day 1: Not Quite Lisp ---
 * Santa was hoping for a white Christmas, but his weather machine's "snow" function is powered by stars, and he's
 * fresh out! To save Christmas, he needs you to collect fifty stars by December 25th.
 * <p>
 * Collect stars by helping Santa solve puzzles. Two puzzles will be made available on each day in the Advent
 * calendar; the second puzzle is unlocked when you complete the first. Each puzzle grants one star. Good luck!
 * <p>
 * Here's an easy puzzle to warm you up.
 * <p>
 * Santa is trying to deliver presents in a large apartment building, but he can't find the right floor - the
 * directions he got are a little confusing. He starts on the ground floor (floor 0) and then follows the
 * instructions one character at a time.
 * <p>
 * An opening parenthesis, (, means he should go up one floor, and a closing parenthesis, ), means he should go down
 * one floor.
 * <p>
 * The apartment building is very tall, and the basement is very deep; he will never find the top or bottom floors.
 * <p>
 * For example:
 * <p>
 * (()) and ()() both result in floor 0.
 * ((( and (()(()( both result in floor 3.
 * ))((((( also results in floor 3.
 * ()) and ))( both result in floor -1 (the first basement level).
 * ))) and )())()) both result in floor -3.
 * To what floor do the instructions take Santa?
 * <p>
 * --- Part Two ---
 * Now, given the same instructions, find the position of the first character that causes him to enter the basement
 * (floor -1). The first character in the instructions has position 1, the second character has position 2, and so on.
 * <p>
 * For example:
 * <p>
 * ) causes him to enter the basement at character position 1.
 * ()()) causes him to enter the basement at character position 5.
 * What is the position of the character that causes Santa to first enter the basement?
 */
public class Day1 {

    private static final Logger log = LoggerFactory.getLogger("2015 - Day 1");

    public static void main(String[] args) {

        String elevator = FileUtils.getLinesAsArray("src/main/resources/2015/day1-elevator")[0];

        int part1 = StringUtils.countMatches(elevator, '(') - StringUtils.countMatches(elevator, ')');
        log.info("Part 1 - Floor [{}]", part1);

        int currentFloor = 0;
        int stepCount = 1;

        for (char c : elevator.toCharArray()) {

            if (c == '(') currentFloor++;
            else currentFloor--;

            if (currentFloor < 0) {
                break;
            }
            stepCount++;
        }

        log.info("Part 2 - First Basement Step [{}]", stepCount);
    }

}
