package aoc2019.day2;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class IntcodeParserTest {

    final IntcodeParser parser = new IntcodeParser();

    @ParameterizedTest
    @MethodSource("intcode_inputValues")
    void parseIntcode(int[] intcode, int[] expected) {
        assertThat(parser.parseIntcode(intcode)).isEqualTo(expected);
    }

    private static Stream<Arguments> intcode_inputValues() {
        return Stream.of(Arguments.of(new int[]{1, 0, 0, 0, 99}, new int[]{2, 0, 0, 0, 99}), Arguments.of(new int[]{2
                , 3, 0, 3, 99}, new int[]{2, 3, 0, 6, 99}), Arguments.of(new int[]{2, 4, 4, 5, 99, 0}, new int[]{2, 4
                , 4, 5, 99, 9801}), Arguments.of(new int[]{1, 1, 1, 4, 99, 5, 6, 0, 99}, new int[]{30, 1, 1, 4, 2, 5,
                6, 0, 99}));
    }
}