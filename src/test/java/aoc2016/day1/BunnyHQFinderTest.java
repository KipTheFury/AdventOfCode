package aoc2016.day1;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class BunnyHQFinderTest {

    @ParameterizedTest
    @MethodSource("directions_inputValues")
    void test_calculateDistanceToBunnyHQ(String[] directions, int expected) {

        BunnyHQFinder finder = new BunnyHQFinder();
        assertThat(finder.calculateDistanceToBunnyHQ(directions)).isEqualTo(expected);
    }

    static Stream<Arguments> directions_inputValues() {
        return Stream.of(Arguments.of(new String[]{"R2", "L3"}, 5), Arguments.of(new String[]{"R2", "R2", "R2"}, 2),
                Arguments.of(new String[]{"R5", "L5", "R5", "R3"}, 12));
    }

    @Test
    void test_distanceToClosestRepeatedLocation() {

        String[] directions = new String[]{"R8", "R4", "R4", "R8"};
        BunnyHQFinder finder = new BunnyHQFinder();

        assertThat(finder.distanceToClosestRepeatedLocation(directions)).isEqualTo(4);

    }
}