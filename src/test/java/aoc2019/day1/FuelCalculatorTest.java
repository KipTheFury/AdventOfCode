package aoc2019.day1;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class FuelCalculatorTest {

    private final FuelCalculator calculator = new FuelCalculator();

    @ParameterizedTest
    @MethodSource("calculate_inputValues")
    void test_calculateFuel(int mass, int expected) {

        assertThat(calculator.calculateFuel(mass)).isEqualTo(expected);
    }

    private static Stream<Arguments> calculate_inputValues() {
        return Stream.of(
                Arguments.of(12, 2),
                Arguments.of(14, 2),
                Arguments.of(1969, 654),
                Arguments.of(100756, 33583)
        );
    }

    @ParameterizedTest
    @MethodSource("calculateAdditional_inputValues")
    void calculateFuelWithAdditionalFuel(int mass, int expected) {

        assertThat(calculator.calculateFuelWithAdditionalFuel(mass)).isEqualTo(expected);
    }

    private static Stream<Arguments> calculateAdditional_inputValues() {
        return Stream.of(
                Arguments.of(14, 2),
                Arguments.of(1969, 966),
                Arguments.of(100756, 50346)
        );
    }
}