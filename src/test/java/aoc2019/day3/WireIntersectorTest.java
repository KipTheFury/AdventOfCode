package aoc2019.day3;

import common.Coords;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Map;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class WireIntersectorTest {

    WireIntersector intersector = new WireIntersector();

    @ParameterizedTest
    @MethodSource("intersection_inputValues")
    void test_getClosestIntersection(String wire1, String wire2, int expected) {

        Map<Coords, Integer> intersections = intersector.calculateIntersections(wire1.split(","),
                wire2.split(","));

        assertThat(intersector.getClosestIntersection(intersections)).isEqualTo(expected);
    }

    static Stream<Arguments> intersection_inputValues() {

        return Stream.of(Arguments.of("R8,U5,L5,D3", "U7,R6,D4,L4", 6),
                Arguments.of("R75,D30,R83,U83,L12,D49,R71,U7,L72", "U62,R66,U55,R34,D71,R55,D58,R83", 159),
                Arguments.of("R98,U47,R26,D63,R33,U87,L62,D20,R33,U53,R51", "U98,R91,D20,R16,D67,R40,U7,R15,U6,R7",
                        135));
    }

    @ParameterizedTest
    @MethodSource("steps_inputValues")
    void test_getFewestSteps(String wire1, String wire2, int expected) {

        Map<Coords, Integer> intersections = intersector.calculateIntersections(wire1.split(","),
                wire2.split(","));

        assertThat(intersector.getFewestSteps(intersections)).isEqualTo(expected);
    }

    static Stream<Arguments> steps_inputValues() {

        return Stream.of(Arguments.of("R8,U5,L5,D3", "U7,R6,D4,L4", 30),
                Arguments.of("R75,D30,R83,U83,L12,D49,R71,U7,L72", "U62,R66,U55,R34,D71,R55,D58,R83", 610),
                Arguments.of("R98,U47,R26,D63,R33,U87,L62,D20,R33,U53,R51", "U98,R91,D20,R16,D67,R40,U7,R15,U6,R7",
                        410));
    }
}