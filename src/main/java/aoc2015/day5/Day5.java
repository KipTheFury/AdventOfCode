package aoc2015.day5;

import common.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * --- Day 5: Doesn't He Have Intern-Elves For This? ---
 * Santa needs help figuring out which strings in his text file are naughty or nice.
 * <p>
 * A nice string is one with all of the following properties:
 * <p>
 * It contains at least three vowels (aeiou only), like aei, xazegov, or aeiouaeiouaeiou.
 * It contains at least one letter that appears twice in a row, like xx, abcdde (dd), or aabbccdd (aa, bb, cc, or dd).
 * It does not contain the strings ab, cd, pq, or xy, even if they are part of one of the other requirements.
 * For example:
 * <p>
 * ugknbfddgicrmopn is nice because it has at least three vowels (u...i...o...), a double letter (...dd...), and none
 * of the disallowed substrings.
 * aaa is nice because it has at least three vowels and a double letter, even though the letters used by different
 * rules overlap.
 * jchzalrnumimnmhp is naughty because it has no double letter.
 * haegwjzuvuyypxyu is naughty because it contains the string xy.
 * dvszwmarrgswjxmb is naughty because it contains only one vowel.
 * How many strings are nice?
 * <p>
 * --- Part Two ---
 * Realizing the error of his ways, Santa has switched to a better model of determining whether a string is naughty
 * or nice. None of the old rules apply, as they are all clearly ridiculous.
 * <p>
 * Now, a nice string is one with all of the following properties:
 * <p>
 * It contains a pair of any two letters that appears at least twice in the string without overlapping, like xyxy
 * (xy) or aabcdefgaa (aa), but not like aaa (aa, but it overlaps).
 * It contains at least one letter which repeats with exactly one letter between them, like xyx, abcdefeghi (efe), or
 * even aaa.
 * For example:
 * <p>
 * qjhvhtzxzqqjkmpb is nice because is has a pair that appears twice (qj) and a letter that repeats with exactly one
 * letter between them (zxz).
 * xxyxx is nice because it has a pair that appears twice and a letter that repeats with one between, even though the
 * letters used by each rule overlap.
 * uurcxstgmygtbstg is naughty because it has a pair (tg) but no repeat with a single letter between them.
 * ieodomkazucvgmuy is naughty because it has a repeating letter with one between (odo), but no pair that appears twice.
 * How many strings are nice under these new rules?
 */
public class Day5 {

    private static final Logger log = LoggerFactory.getLogger("2015 - Day 5");

    public static void main(String[] args) {

        String[] strings = FileUtils.getLinesAsArray("src/main/resources/2015/day5-Strings");
        NaughtyNiceStringValidator validator = new NaughtyNiceStringValidator();

        int niceCount = 0;

        for (String s : strings) {
            if (validator.isNice(s)) niceCount++;
        }

        log.info("Part 1 - Nice Strings [{}]", niceCount);

        int niceV2Count = 0;

        for (String s : strings) {
            if (validator.isNice_v2(s)) niceV2Count++;
        }

        log.info("Part 2 - Nice v2 Strings [{}]", niceV2Count);
    }
}
