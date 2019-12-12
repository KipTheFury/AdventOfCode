package aoc2017.day2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * --- Day 2: Corruption Checksum ---
 * As you walk through the door, a glowing humanoid shape yells in your direction. "You there! Your state appears to
 * be idle. Come help us repair the corruption in this spreadsheet - if we take another millisecond, we'll have to
 * display an hourglass cursor!"
 * <p>
 * The spreadsheet consists of rows of apparently-random numbers. To make sure the recovery process is on the right
 * track, they need you to calculate the spreadsheet's checksum. For each row, determine the difference between the
 * largest value and the smallest value; the checksum is the sum of all of these differences.
 * <p>
 * For example, given the following spreadsheet:
 * <p>
 * 5 1 9 5
 * 7 5 3
 * 2 4 6 8
 * The first row's largest and smallest values are 9 and 1, and their difference is 8.
 * The second row's largest and smallest values are 7 and 3, and their difference is 4.
 * The third row's difference is 6.
 * In this example, the spreadsheet's checksum would be 8 + 4 + 6 = 18.
 * <p>
 * What is the checksum for the spreadsheet in your puzzle input?
 */
public class Day2 {

    private static final Logger log = LoggerFactory.getLogger("2017 - Day 2");
    private static final String filename = "src/main/resources/2017/day2-rows";

    public static void main(String[] args) {

        List<List<Integer>> rows = getInput();
        ChecksumCalculator calculator = new ChecksumCalculator();

        log.info("Part 1 - MaxMin Checksum [{}]", calculator.calculateMaxMinChecksum(rows));
        log.info("Part 2 - EvenlyDivisible Checksum [{}]", calculator.calculateEvenlyDivisibleChecksum(rows));
    }

    private static List<List<Integer>> getInput() {

        List<List<Integer>> rows = new ArrayList<>();

        try {
            List<String> strings = Files.readAllLines(Paths.get(filename), StandardCharsets.UTF_8);
            for (String rowString : strings) {
                List<Integer> row = new ArrayList<>();
                for (String val : rowString.split("\t")) {
                    row.add(Integer.parseInt(val));
                }
                rows.add(row);
            }
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
        return rows;
    }
}
