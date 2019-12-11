package aoc2017.day1;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class ReverseCaptchaParserTest {

    @ParameterizedTest
    @MethodSource("captcha_InputValues")
    void parseCaptcha(String captcha, int step, int expected) {
        ReverseCaptchaParser parser = new ReverseCaptchaParser();
        assertThat(parser.parseCaptcha(captcha, step)).isEqualTo(expected);
    }

    static Stream<Arguments> captcha_InputValues() {

        return Stream.of(Arguments.of("1122", 1, 3), Arguments.of("1111", 1, 4), Arguments.of("1234", 1, 0),
                Arguments.of("91212129", 1, 9), Arguments.of("1212", 2, 6), Arguments.of("1221", 2, 0), Arguments.of(
                        "123425", 3, 4), Arguments.of("123123", 3, 12), Arguments.of("12131415", 4, 4));
    }
}