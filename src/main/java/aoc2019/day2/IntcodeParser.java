package aoc2019.day2;

public class IntcodeParser {

    private static final int ADD = 1;
    private static final int MULTIPLY = 2;
    private static final int HALT = 99;

    public int[] parseIntcode(int[] intcode) {

        int pointer = 0;

        while (intcode[pointer] != HALT) {
            if (intcode[pointer] == ADD) {
                intcode[intcode[pointer + 3]] = intcode[intcode[pointer + 1]] + intcode[intcode[pointer + 2]];
            } else if (intcode[pointer] == MULTIPLY) {
                intcode[intcode[pointer + 3]] = intcode[intcode[pointer + 1]] * intcode[intcode[pointer + 2]];
            }
            pointer += 4;
        }

        return intcode;
    }
}
