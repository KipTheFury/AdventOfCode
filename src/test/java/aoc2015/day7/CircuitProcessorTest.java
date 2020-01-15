package aoc2015.day7;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class CircuitProcessorTest {

    @Test
    void parseInstructions() {

        String[] instructions = new String[]{"123 -> x", "456 -> y", "x AND y -> d", "x OR y -> e", "x LSHIFT 2 -> f"
                , "y RSHIFT 2 -> g", "NOT x -> h", "NOT y -> i"};

        CircuitProcessor processor = new CircuitProcessor();

        Map<String, Character> circuit = processor.parseInstructions(instructions);

        assertThat((int) circuit.get("d")).isEqualTo(72);
        assertThat((int) circuit.get("e")).isEqualTo(507);
        assertThat((int) circuit.get("f")).isEqualTo(492);
        assertThat((int) circuit.get("g")).isEqualTo(114);
        assertThat((int) circuit.get("h")).isEqualTo(65412);
        assertThat((int) circuit.get("i")).isEqualTo(65079);
    }
}