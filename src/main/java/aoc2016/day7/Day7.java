package aoc2016.day7;

import common.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * --- Day 7: Internet Protocol Version 7 ---
 * While snooping around the local network of EBHQ, you compile a list of IP addresses (they're IPv7, of course; IPv6
 * is much too limited). You'd like to figure out which IPs support TLS (transport-layer snooping).
 * <p>
 * An IP supports TLS if it has an Autonomous Bridge Bypass Annotation, or ABBA. An ABBA is any four-character
 * sequence which consists of a pair of two different characters followed by the reverse of that pair, such as xyyx
 * or abba. However, the IP also must not have an ABBA within any hypernet sequences, which are contained by square
 * brackets.
 * <p>
 * For example:
 * <p>
 * abba[mnop]qrst supports TLS (abba outside square brackets).
 * abcd[bddb]xyyx does not support TLS (bddb is within square brackets, even though xyyx is outside square brackets).
 * aaaa[qwer]tyui does not support TLS (aaaa is invalid; the interior characters must be different).
 * ioxxoj[asdfgh]zxcvbn supports TLS (oxxo is outside square brackets, even though it's within a larger string).
 * How many IPs in your puzzle input support TLS?
 * <p>
 * --- Part Two ---
 * You would also like to know which IPs support SSL (super-secret listening).
 * <p>
 * An IP supports SSL if it has an Area-Broadcast Accessor, or ABA, anywhere in the supernet sequences (outside any
 * square bracketed sections), and a corresponding Byte Allocation Block, or BAB, anywhere in the hypernet sequences.
 * An ABA is any three-character sequence which consists of the same character twice with a different character
 * between them, such as xyx or aba. A corresponding BAB is the same characters but in reversed positions: yxy and
 * bab, respectively.
 * <p>
 * For example:
 * <p>
 * aba[bab]xyz supports SSL (aba outside square brackets with corresponding bab within square brackets).
 * xyx[xyx]xyx does not support SSL (xyx, but no corresponding yxy).
 * aaa[kek]eke supports SSL (eke in supernet with corresponding kek in hypernet; the aaa sequence is not related,
 * because the interior character must be different).
 * zazbz[bzb]cdb supports SSL (zaz has no corresponding aza, but zbz has a corresponding bzb, even though zaz and zbz
 * overlap).
 * How many IPs in your puzzle input support SSL?
 */
public class Day7 {

    private static final Logger log = LoggerFactory.getLogger("2016 - Day 6");

    public static void main(String[] args) {

        String[] ipAddresses = FileUtils.getLinesAsArray("src/main/resources/2016/day7-ipaddresses");
        IPv7Validator validator = new IPv7Validator();

        int supportsTLS = 0;

        for (String ipAddress : ipAddresses) {
            if (validator.supportsTLS(ipAddress)) supportsTLS++;
        }

        log.info("Part 1 - Ip Addresses that support TLS [{}]", supportsTLS);

        int supportsSSL = 0;

        for (String ipAddress : ipAddresses) {
            if (validator.supportsSSL(ipAddress)) supportsSSL++;
        }

        log.info("Part 1 - Ip Addresses that support SSL [{}]", supportsSSL);

    }
}
