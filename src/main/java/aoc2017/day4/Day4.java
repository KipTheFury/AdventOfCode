package aoc2017.day4;

import common.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * --- Day 4: High-Entropy Passphrases ---
 * A new system policy has been put in place that requires all accounts to use a passphrase instead of simply a
 * password. A passphrase consists of a series of words (lowercase letters) separated by spaces.
 * <p>
 * To ensure security, a valid passphrase must contain no duplicate words.
 * <p>
 * For example:
 * <p>
 * aa bb cc dd ee is valid.
 * aa bb cc dd aa is not valid - the word aa appears more than once.
 * aa bb cc dd aaa is valid - aa and aaa count as different words.
 * The system's full passphrase list is available as your puzzle input. How many passphrases are valid?
 * <p>
 * --- Part Two ---
 * For added security, yet another system policy has been put in place. Now, a valid passphrase must contain no two
 * words that are anagrams of each other - that is, a passphrase is invalid if any word's letters can be rearranged
 * to form any other word in the passphrase.
 * <p>
 * For example:
 * <p>
 * abcde fghij is a valid passphrase.
 * abcde xyz ecdab is not valid - the letters from the third word can be rearranged to form the first word.
 * a ab abc abd abf abj is a valid passphrase, because all letters need to be used when forming another word.
 * iiii oiii ooii oooi oooo is valid.
 * oiii ioii iioi iiio is not valid - any of these words can be rearranged to form any other word.
 * Under this new system policy, how many passphrases are valid?
 */
public class Day4 {

    private static final Logger log = LoggerFactory.getLogger("2017 - Day4");

    public static void main(String[] args) {

        String[] passphrases = FileUtils.getLinesAsArray("src/main/resources/2017/day4-passphrases");
        PassphraseValidator validator = new PassphraseValidator();

        long validPassphrases = validator.validatePassphrases(passphrases, false);

        log.info("Part 1 - Valid Passphrases [{}]", validPassphrases);

        long validPassphraseAnagrams = validator.validatePassphrases(passphrases, true);

        log.info("Part 2 - Valid Passphrases, no anagrams [{}]", validPassphraseAnagrams);
    }
}
