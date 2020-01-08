package aoc2015.day5;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class NaughtyNiceStringValidatorTest {

    @ParameterizedTest
    @MethodSource("inputValues_Strings")
    void isNice(String s, boolean expected) {

        NaughtyNiceStringValidator validator = new NaughtyNiceStringValidator();

        assertThat(validator.isNice(s)).isEqualTo(expected);
    }

    static Stream<Arguments> inputValues_Strings() {

        return Stream.of(Arguments.of("ugknbfddgicrmopn", true), Arguments.of("aaa", true), Arguments.of(
                "jchzalrnumimnmhp", false), Arguments.of("haegwjzuvuyypxyu", false), Arguments.of("dvszwmarrgswjxmb",
                false));
    }

    @ParameterizedTest
    @MethodSource("inputValues_Strings_v2")
    void isNice_v2(String s, boolean expected) {

        NaughtyNiceStringValidator validator = new NaughtyNiceStringValidator();

        assertThat(validator.isNice_v2(s)).isEqualTo(expected);
    }

    static Stream<Arguments> inputValues_Strings_v2() {

        return Stream.of(Arguments.of("qjhvhtzxzqqjkmpb", true), Arguments.of("xxyxx", true), Arguments.of(
                "uurcxstgmygtbstg", false), Arguments.of("ieodomkazucvgmuy", false), Arguments.of("aaaxyx", false));
    }
}