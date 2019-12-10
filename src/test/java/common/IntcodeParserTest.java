package common;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.LinkedList;
import java.util.Queue;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class IntcodeParserTest {

    final IntcodeParser parser = new IntcodeParser();

    @ParameterizedTest
    @MethodSource("intcode_inputValues")
    void parseIntcode(int[] intcode, Queue<Integer> input, int[] expectedIntcode) {

        IntcodeParser.ReturnValue retVal = parser.parseIntcode(intcode, input);

        assertThat(retVal.getIntcode()).isEqualTo(expectedIntcode);
    }

    private static Stream<Arguments> intcode_inputValues() {

        Queue<Integer> input = new LinkedList<>();
        input.add(1);

        return Stream.of(Arguments.of(new int[]{1, 0, 0, 0, 99}, null, new int[]{2, 0, 0, 0, 99}),
                Arguments.of(new int[]{2, 3, 0, 3, 99}, null, new int[]{2, 3, 0, 6, 99}), Arguments.of(new int[]{2, 4
                        , 4, 5, 99, 0}, null, new int[]{2, 4, 4, 5, 99, 9801}), Arguments.of(new int[]{1, 1, 1, 4, 99
                        , 5, 6, 0, 99}, null, new int[]{30, 1, 1, 4, 2, 5, 6, 0, 99}), Arguments.of(new int[]{3, 0, 4
                        , 0, 99}, input, new int[]{1, 0, 4, 0, 99}), Arguments.of(new int[]{1002, 4, 3, 4, 33}, null,
                        new int[]{1002, 4, 3, 4, 99}, 0));
    }
}