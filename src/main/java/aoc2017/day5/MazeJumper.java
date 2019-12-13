package aoc2017.day5;

import java.util.List;

public class MazeJumper {

    public int stepsToEscapeMaze(List<Integer> instructions, boolean part2) {

        int stepCount = 0;
        int pointer = 0;

        while (pointer > -1 && pointer < instructions.size()) {

            int instruction = instructions.get(pointer);

            if (part2 && instruction >= 3) {
                instructions.set(pointer, instruction - 1);
            } else {
                instructions.set(pointer, instruction + 1);
            }
            pointer += instruction;

            stepCount++;
        }
        return stepCount;
    }
}
