package aoc2017.day8;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class RegisterProcessorTest {

    @Test
    void processInstructions() {

        RegisterProcessor processor = new RegisterProcessor();

        String[] instructions = new String[]{"b inc 5 if a > 1", "a inc 1 if b < 5", "c dec -10 if a >= 1", "c inc " +
                "-20 if c == 10"};

        Map<String, Integer> registers = processor.processInstructions(instructions);

        assertThat(registers.get("a")).isEqualTo(1);
        assertThat(registers.get("b")).isEqualTo(0);
        assertThat(registers.get("c")).isEqualTo(-10);
    }
}