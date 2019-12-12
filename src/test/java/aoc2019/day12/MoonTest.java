package aoc2019.day12;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class MoonTest {

    @ParameterizedTest
    @MethodSource("applyVelocity_inputValues")
    void test_applyVelocity(int[] pos, int[] vel, int[] expected) {

        Moon m = new Moon();
        m.position = pos;
        m.velocity = vel;

        m.applyVelocity();
        assertThat(m.position).isEqualTo(expected);
    }

    static Stream<Arguments> applyVelocity_inputValues() {
        return Stream.of(Arguments.of(new int[]{2, 1, -3}, new int[]{-3, -2, 1}, new int[]{-1, -1, -2}),
                Arguments.of(new int[]{1, -8, 0}, new int[]{-1, 1, 3}, new int[]{0, -7, 3}), Arguments.of(new int[]{3
                        , -6, 1}, new int[]{3, 2, -3}, new int[]{6, -4, -2}), Arguments.of(new int[]{2, 0, 4},
                        new int[]{1, -1, -1}, new int[]{3, -1, 3}));
    }

    @ParameterizedTest
    @MethodSource("calculateEnergy_inputValues")
    void test_calculateEnergy(int[] pos, int[] vel, int expected) {

        Moon m = new Moon();
        m.position = pos;
        m.velocity = vel;

        assertThat(m.calculateEnergy()).isEqualTo(expected);
    }

    static Stream<Arguments> calculateEnergy_inputValues() {
        return Stream.of(Arguments.of(new int[]{2, 1, -3}, new int[]{-3, -2, 1}, 36), Arguments.of(new int[]{1, -8,
                0}, new int[]{-1, 1, 3}, 45), Arguments.of(new int[]{3, -6, 1}, new int[]{3, 2, -3}, 80),
                Arguments.of(new int[]{2, 0, 4}, new int[]{1, -1, -1}, 18));
    }


}