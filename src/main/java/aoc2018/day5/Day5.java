package aoc2018.day5;

import common.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * --- Day 5: Alchemical Reduction ---
 * You've managed to sneak in to the prototype suit manufacturing lab. The Elves are making decent progress, but are
 * still struggling with the suit's size reduction capabilities.
 * <p>
 * While the very latest in 1518 alchemical technology might have solved their problem eventually, you can do better.
 * You scan the chemical composition of the suit's material and discover that it is formed by extremely long polymers
 * (one of which is available as your puzzle input).
 * <p>
 * The polymer is formed by smaller units which, when triggered, react with each other such that two adjacent units
 * of the same type and opposite polarity are destroyed. Units' types are represented by letters; units' polarity is
 * represented by capitalization. For instance, r and R are units with the same type but opposite polarity, whereas r
 * and s are entirely different types and do not react.
 * <p>
 * For example:
 * <p>
 * In aA, a and A react, leaving nothing behind.
 * In abBA, bB destroys itself, leaving aA. As above, this then destroys itself, leaving nothing.
 * In abAB, no two adjacent units are of the same type, and so nothing happens.
 * In aabAAB, even though aa and AA are of the same type, their polarities match, and so nothing happens.
 * Now, consider a larger example, dabAcCaCBAcCcaDA:
 * <p>
 * dabAcCaCBAcCcaDA  The first 'cC' is removed.
 * dabAaCBAcCcaDA    This creates 'Aa', which is removed.
 * dabCBAcCcaDA      Either 'cC' or 'Cc' are removed (the result is the same).
 * dabCBAcaDA        No further actions can be taken.
 * After all possible reactions, the resulting polymer contains 10 units.
 * <p>
 * How many units remain after fully reacting the polymer you scanned?
 * <p>
 * --- Part Two ---
 * Time to improve the polymer.
 * <p>
 * One of the unit types is causing problems; it's preventing the polymer from collapsing as much as it should. Your
 * goal is to figure out which unit type is causing the most problems, remove all instances of it (regardless of
 * polarity), fully react the remaining polymer, and measure its length.
 * <p>
 * For example, again using the polymer dabAcCaCBAcCcaDA from above:
 * <p>
 * Removing all A/a units produces dbcCCBcCcD. Fully reacting this polymer produces dbCBcD, which has length 6.
 * Removing all B/b units produces daAcCaCAcCcaDA. Fully reacting this polymer produces daCAcaDA, which has length 8.
 * Removing all C/c units produces dabAaBAaDA. Fully reacting this polymer produces daDA, which has length 4.
 * Removing all D/d units produces abAcCaCBAcCcaA. Fully reacting this polymer produces abCBAc, which has length 6.
 * In this example, removing all C/c units was best, producing the answer 4.
 * <p>
 * What is the length of the shortest polymer you can produce by removing all units of exactly one type and fully
 * reacting the result?
 */
public class Day5 {

    private static final Logger log = LoggerFactory.getLogger("2018 - Day 5");

    public static void main(String[] args) {

        String chain = FileUtils.getLinesAsArray("src/main/resources/2018/day5-polymer")[0];
        PolymerReducer reducer = new PolymerReducer();

        String reducedChain = reducer.processPolymerChain(chain);
        log.info("Part 1 - Units in reduced Polymer chain [{}]", reducedChain.length());

        String optimisedChain = reducer.optimisePolymerChain(chain);
        log.info("Part 2 - Units in optimised Polymer chain [{}]", optimisedChain.length());
    }
}
