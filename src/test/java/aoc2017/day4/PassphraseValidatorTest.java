package aoc2017.day4;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class PassphraseValidatorTest {

    @Test
    void test_validatePassphrases() {

        String[] input = new String[]{"aa bb cc dd ee", "aa bb cc dd aa", "aa bb cc dd aaa"};
        PassphraseValidator validator = new PassphraseValidator();
        assertThat(validator.validatePassphrases(input, false)).isEqualTo(2);

    }

    @ParameterizedTest
    @MethodSource("passphrase_inputValues")
    void test_validatePassphrase(String passphrase, boolean part2, boolean expected) {

        PassphraseValidator validator = new PassphraseValidator();
        assertThat(validator.validatePassphrase(passphrase, part2)).isEqualTo(expected);
    }

    static Stream<Arguments> passphrase_inputValues() {
        return Stream.of(Arguments.of("aa bb cc dd ee", false, true), Arguments.of("aa bb cc dd aa", false, false),
                Arguments.of("aa bb cc dd aaa", false, true), Arguments.of("abcde fghij", true, true), Arguments.of(
                        "abcde xyz ecdab", true, false), Arguments.of("a ab abc abd abf abj", true, true),
                Arguments.of("iiii oiii ooii oooi oooo", true, true), Arguments.of("oiii ioii iioi iiio", true, false));
    }
}