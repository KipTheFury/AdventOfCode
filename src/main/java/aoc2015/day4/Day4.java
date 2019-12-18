package aoc2015.day4;

import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * --- Day 4: The Ideal Stocking Stuffer ---
 * Santa needs help mining some AdventCoins (very similar to bitcoins) to use as gifts for all the economically
 * forward-thinking little girls and boys.
 * <p>
 * To do this, he needs to find MD5 hashes which, in hexadecimal, start with at least five zeroes. The input to the
 * MD5 hash is some secret key (your puzzle input, given below) followed by a number in decimal. To mine AdventCoins,
 * you must find Santa the lowest positive number (no leading zeroes: 1, 2, 3, ...) that produces such a hash.
 * <p>
 * For example:
 * <p>
 * If your secret key is abcdef, the answer is 609043, because the MD5 hash of abcdef609043 starts with five zeroes
 * (000001dbbfa...), and it is the lowest such number to do so.
 * If your secret key is pqrstuv, the lowest number it combines with to make an MD5 hash starting with five zeroes is
 * 1048970; that is, the MD5 hash of pqrstuv1048970 looks like 000006136ef....
 * Your puzzle input is iwrupvqb.
 * <p>
 * --- Part Two ---
 * Now find one that starts with six zeroes.
 */
public class Day4 {

    private static final Logger log = LoggerFactory.getLogger("2015 - Day 4");

    public static void main(String[] args) {

        String input = "iwrupvqb";
        int part1 = getNumber(input, 5);

        log.info("Part 1 - Mining number [{}]", part1);

        int part2 = getNumber(input, 6);

        log.info("Part 2 - Mining number [{}]", part2);
    }

    private static int getNumber(String input, int md5PrefixZeroes) {

        int number = 0;

        String md5Hex;
        String subString;
        String expectedPrefix = "";

        for (int i = 0; i < md5PrefixZeroes; i++) expectedPrefix += "0";

        do {
            number++;
            md5Hex = DigestUtils.md5Hex(input + number);
            subString = md5Hex.substring(0, md5PrefixZeroes);

        } while (!subString.equals(expectedPrefix));
        return number;
    }
}
