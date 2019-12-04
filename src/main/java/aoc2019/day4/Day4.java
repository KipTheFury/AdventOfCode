package aoc2019.day4;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * --- Day 4: Secure Container ---
 * You arrive at the Venus fuel depot only to discover it's protected by a password. The Elves had written the
 * password on a sticky note, but someone threw it out.
 * <p>
 * However, they do remember a few key facts about the password:
 * <p>
 * It is a six-digit number.
 * The value is within the range given in your puzzle input.
 * Two adjacent digits are the same (like 22 in 122345).
 * Going from left to right, the digits never decrease; they only ever increase or stay the same (like 111123 or
 * 135679).
 * Other than the range rule, the following are true:
 * <p>
 * 111111 meets these criteria (double 11, never decreases).
 * 223450 does not meet these criteria (decreasing pair of digits 50).
 * 123789 does not meet these criteria (no double).
 * How many different passwords within the range given in your puzzle input meet these criteria?
 * <p>
 * Your puzzle input is 307237-769058.
 * <p>
 * --- Part Two ---
 * An Elf just remembered one more important detail: the two adjacent matching digits are not part of a larger group
 * of matching digits.
 * <p>
 * Given this additional criterion, but still ignoring the range rule, the following are now true:
 * <p>
 * 112233 meets these criteria because the digits never decrease and all repeated digits are exactly two digits long.
 * 123444 no longer meets the criteria (the repeated 44 is part of a larger group of 444).
 * 111122 meets the criteria (even though 1 is repeated more than twice, it still contains a double 22).
 * How many different passwords within the range given in your puzzle input meet all of the criteria?
 */
public class Day4 {

    private static final Logger log = LoggerFactory.getLogger("2019 - Day4");

    public static void main(String[] args) {

        PasswordChecker checker = new PasswordChecker();
        int validPasswords_part1 = 0, validPasswords_part2 = 0;

        for (int i = 307237; i <= 769058; i++) {

            if (checker.checkPassword(String.valueOf(i), false)) validPasswords_part1 += 1;

            if (checker.checkPassword(String.valueOf(i), true)) validPasswords_part2 += 1;
        }

        log.info("Part 1 : Valid Passwords [{}]", validPasswords_part1);
        log.info("Part 2 : Valid Passwords [{}]", validPasswords_part2);
    }
}
