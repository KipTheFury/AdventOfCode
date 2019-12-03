package aoc2018.day1;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class FrequencyCalibratorTest {

    final FrequencyCalibrator calibrator = new FrequencyCalibrator();

    @ParameterizedTest
    @MethodSource("parse_inputValues")
    void test_parseInstructions(String instructions, int expected) {

        assertThat(calibrator.parseInstructions(instructions.split(","))).isEqualTo(expected);
    }

    static Stream<Arguments> parse_inputValues() {
        return Stream.of(Arguments.of("+1,+1,+1", 3), Arguments.of("+1,+1,-2", 0), Arguments.of("-1,-2,-3", -6));
    }

    @ParameterizedTest
    @MethodSource("repeated_inputValues")
    void test_findRepeatedFrequency(String instructions, int expected) {
    }

    static Stream<Arguments> repeated_inputValues() {
        return Stream.of(Arguments.of("+1, -1", 0), Arguments.of("+3,+3,+4,-2,-4", 10), Arguments.of("-6,+3,+8,+5,-6"
                , 5), Arguments.of("+7,+7,-2,-7,-4", 14));
    }
}