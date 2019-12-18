package aoc2015.day3;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class SantaTrackerTest {

    @ParameterizedTest
    @MethodSource("directions_inputValues")
    void parseDirections(String directions, boolean roboSanta, int expectedHouses) {

        SantaTracker tracker = new SantaTracker();
        assertThat(tracker.parseDirections(directions, roboSanta).values()).hasSize(expectedHouses);

    }

    static Stream<Arguments> directions_inputValues() {
        return Stream.of(Arguments.of(">", false, 2), Arguments.of("^>v<", false, 4), Arguments.of("^v^v^v^v^v",
                false, 2), Arguments.of("^v", true, 3), Arguments.of("^>v<", true, 3), Arguments.of("^v^v^v^v^v",
                true, 11));
    }
}