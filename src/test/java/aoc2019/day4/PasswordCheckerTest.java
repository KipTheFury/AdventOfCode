package aoc2019.day4;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class PasswordCheckerTest {

    final PasswordChecker checker = new PasswordChecker();

    @ParameterizedTest
    @MethodSource("passwords_inputValues")
    void test_checkPassword_part1(String password, boolean expected) {
        assertThat(checker.checkPassword(password, false)).isEqualTo(expected);
    }

    static Stream<Arguments> passwords_inputValues() {

        return Stream.of(Arguments.of("111111", true), Arguments.of("223450", false), Arguments.of("123789", false));
    }

    @ParameterizedTest
    @MethodSource("passwords_part2_inputValues")
    void test_checkPassword_part2(String password, boolean expected) {
        assertThat(checker.checkPassword(password, true)).isEqualTo(expected);
    }

    static Stream<Arguments> passwords_part2_inputValues() {

        return Stream.of(Arguments.of("111111", false), Arguments.of("112222", true), Arguments.of("111223", true),
                Arguments.of("112233", true), Arguments.of("123444", false), Arguments.of("111122", true));
    }
}