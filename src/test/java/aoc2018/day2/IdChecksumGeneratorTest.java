package aoc2018.day2;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class IdChecksumGeneratorTest {

    IdChecksumGenerator generator = new IdChecksumGenerator();

    @Test
    void test_getChecksum() {

        String[] input = new String[]{"abcdef", "bababc", "abbcde", "abcccd", "aabcdd", "abcdee", "ababab"};
        int checksum = generator.getChecksum(input);

        assertThat(checksum).isEqualTo(12);
    }

    @Test
    void test_findCommonLettersInAdjacentIds() {

        String[] input = new String[]{"abcde", "fghij", "klmno", "pqrst", "fguij", "axcye", "wvxyz",};

        String commonLetters = generator.findCommonLettersInAdjacentIds(input);
        assertThat(commonLetters).isEqualTo("fgij");
    }
}