package aoc2015.day2;

import org.javatuples.Pair;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class WrappingCalculatorTest {

    @ParameterizedTest
    @MethodSource("presents_inputValues")
    void calculateTotalWrapping(String[] presents, int expectedWrapping, int expectedRibbon) {

        WrappingCalculator calculator = new WrappingCalculator();
        Pair<Integer, Integer> materials = calculator.calculateTotalMaterials(presents);
        assertThat(materials.getValue0()).isEqualTo(expectedWrapping);
        assertThat(materials.getValue1()).isEqualTo(expectedRibbon);

    }

    static Stream<Arguments> presents_inputValues() {
        return Stream.of(Arguments.of(new String[]{"2x3x4"}, 58, 34), Arguments.of(new String[]{"1x1x10"}, 43, 14));
    }
}