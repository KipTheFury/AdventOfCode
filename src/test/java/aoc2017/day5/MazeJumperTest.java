package aoc2017.day5;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class MazeJumperTest {

    @Test
    void test_stepsToEscapeMaze() {

        List<Integer> input = new ArrayList<>();
        input.add(0);
        input.add(3);
        input.add(0);
        input.add(1);
        input.add(-3);

        MazeJumper jumper = new MazeJumper();

        assertThat(jumper.stepsToEscapeMaze(input, false)).isEqualTo(5);

        List<Integer> input2 = new ArrayList<>();
        input2.add(0);
        input2.add(3);
        input2.add(0);
        input2.add(1);
        input2.add(-3);

        assertThat(jumper.stepsToEscapeMaze(input2, true)).isEqualTo(10);
    }
}