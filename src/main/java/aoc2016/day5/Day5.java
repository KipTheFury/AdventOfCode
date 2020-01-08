package aoc2016.day5;

import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

/**
 * --- Day 5: How About a Nice Game of Chess? ---
 * You are faced with a security door designed by Easter Bunny engineers that seem to have acquired most of their
 * security knowledge by watching hacking movies.
 * <p>
 * The eight-character password for the door is generated one character at a time by finding the MD5 hash of some
 * Door ID (your puzzle input) and an increasing integer index (starting with 0).
 * <p>
 * A hash indicates the next character in the password if its hexadecimal representation starts with five zeroes. If
 * it does, the sixth character in the hash is the next character of the password.
 * <p>
 * For example, if the Door ID is abc:
 * <p>
 * The first index which produces a hash that starts with five zeroes is 3231929, which we find by hashing
 * abc3231929; the sixth character of the hash, and thus the first character of the password, is 1.
 * 5017308 produces the next interesting hash, which starts with 000008f82..., so the second character of the
 * password is 8.
 * The third time a hash starts with five zeroes is for abc5278568, discovering the character f.
 * In this example, after continuing this search a total of eight times, the password is 18f47a30.
 * <p>
 * Given the actual Door ID, what is the password?
 * <p>
 * Your puzzle input is ugkcyxxp.
 */
public class Day5 {

    private static final Logger log = LoggerFactory.getLogger("2016 - Day 5");

    public static void main(String[] args) {

        final String doorID = "ugkcyxxp";
        int salt = 0;

        String password = "";

        while (password.length() < 8) {

            String md5Hex = DigestUtils.md5Hex(doorID + salt);
            String subString = md5Hex.substring(0, 5);

            if (subString.equals("00000")) {
                password = password + md5Hex.charAt(5);
            }

            salt++;
        }

        log.info("Part 1 - password [{}]", password);

        salt = 0;
        String[] passwordArray = new String[8];

        while (Arrays.asList(passwordArray).contains(null)) {

            String md5Hex = DigestUtils.md5Hex(doorID + salt);
            String subString = md5Hex.substring(0, 5);

            if (subString.equals("00000")) {

                char positionChar = md5Hex.charAt(5);
                char value = md5Hex.charAt(6);

                if (Character.isDigit(positionChar)) {
                    int position = Integer.parseInt("" + positionChar);

                    if (position >= 0 && position < 8 && passwordArray[position] == null) {
                        passwordArray[position] = "" + value;
                    }
                }
            }
            salt++;
        }

        log.info("Part 2 - password [{}]", Arrays.toString(passwordArray));
    }
}
