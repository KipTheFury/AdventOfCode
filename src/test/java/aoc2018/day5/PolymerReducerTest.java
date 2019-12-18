package aoc2018.day5;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class PolymerReducerTest {

    @ParameterizedTest
    @MethodSource("polymerChain_inputValues")
    void test_processPolymerChain(String chain, String expected) {

        PolymerReducer reducer = new PolymerReducer();
        assertThat(reducer.processPolymerChain(chain)).isEqualTo(expected);
    }

    static Stream<Arguments> polymerChain_inputValues() {

        return Stream.of(Arguments.of("aA", ""), Arguments.of("abBA", ""), Arguments.of("aabAAB", "aabAAB"),
                Arguments.of("dabAcCaCBAcCcaDA", "dabCBAcaDA"));
    }

    @Test
    void test_optimisePolymerChain() {

        String chain = "dabAcCaCBAcCcaDA";
        PolymerReducer reducer = new PolymerReducer();

        assertThat(reducer.optimisePolymerChain(chain)).isEqualTo("daDA");
    }
}