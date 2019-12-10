package aoc2019.day5;

import common.IntcodeParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedList;
import java.util.Queue;

/**
 * --- Day 5: Sunny with a Chance of Asteroids ---
 * You're starting to sweat as the ship makes its way toward Mercury. The Elves suggest that you get the air
 * conditioner working by upgrading your ship computer to support the Thermal Environment Supervision Terminal.
 * <p>
 * The Thermal Environment Supervision Terminal (TEST) starts by running a diagnostic program (your puzzle input).
 * The TEST diagnostic program will run on your existing Intcode computer after a few modifications:
 * <p>
 * First, you'll need to add two new instructions:
 * <p>
 * Opcode 3 takes a single integer as input and saves it to the position given by its only parameter. For example,
 * the instruction 3,50 would take an input value and store it at address 50.
 * Opcode 4 outputs the value of its only parameter. For example, the instruction 4,50 would output the value at
 * address 50.
 * Programs that use these instructions will come with documentation that explains what should be connected to the
 * input and output. The program 3,0,4,0,99 outputs whatever it gets as input, then halts.
 * <p>
 * Second, you'll need to add support for parameter modes:
 * <p>
 * Each parameter of an instruction is handled based on its parameter mode. Right now, your ship computer already
 * understands parameter mode 0, position mode, which causes the parameter to be interpreted as a position - if the
 * parameter is 50, its value is the value stored at address 50 in memory. Until now, all parameters have been in
 * position mode.
 * <p>
 * Now, your ship computer will also need to handle parameters in mode 1, immediate mode. In immediate mode, a
 * parameter is interpreted as a value - if the parameter is 50, its value is simply 50.
 * <p>
 * Parameter modes are stored in the same value as the instruction's opcode. The opcode is a two-digit number based
 * only on the ones and tens digit of the value, that is, the opcode is the rightmost two digits of the first value
 * in an instruction. Parameter modes are single digits, one per parameter, read right-to-left from the opcode: the
 * first parameter's mode is in the hundreds digit, the second parameter's mode is in the thousands digit, the third
 * parameter's mode is in the ten-thousands digit, and so on. Any missing modes are 0.
 * <p>
 * For example, consider the program 1002,4,3,4,33.
 * <p>
 * The first instruction, 1002,4,3,4, is a multiply instruction - the rightmost two digits of the first value, 02,
 * indicate opcode 2, multiplication. Then, going right to left, the parameter modes are 0 (hundreds digit), 1
 * (thousands digit), and 0 (ten-thousands digit, not present and therefore zero):
 * <p>
 * ABCDE
 * 1002
 * <p>
 * DE - two-digit opcode,      02 == opcode 2
 * C - mode of 1st parameter,  0 == position mode
 * B - mode of 2nd parameter,  1 == immediate mode
 * A - mode of 3rd parameter,  0 == position mode,
 * omitted due to being a leading zero
 * This instruction multiplies its first two parameters. The first parameter, 4 in position mode, works like it did
 * before - its value is the value stored at address 4 (33). The second parameter, 3 in immediate mode, simply has
 * value 3. The result of this operation, 33 * 3 = 99, is written according to the third parameter, 4 in position
 * mode, which also works like it did before - 99 is written to address 4.
 * <p>
 * Parameters that an instruction writes to will never be in immediate mode.
 * <p>
 * Finally, some notes:
 * <p>
 * It is important to remember that the instruction pointer should increase by the number of values in the
 * instruction after the instruction finishes. Because of the new instructions, this amount is no longer always 4.
 * Integers can be negative: 1101,100,-1,4,0 is a valid program (find 100 + -1, store the result in position 4).
 * The TEST diagnostic program will start by requesting from the user the ID of the system to test by running an
 * input instruction - provide it 1, the ID for the ship's air conditioner unit.
 * <p>
 * It will then perform a series of diagnostic tests confirming that various parts of the Intcode computer, like
 * parameter modes, function correctly. For each test, it will run an output instruction indicating how far the
 * result of the test was from the expected value, where 0 means the test was successful. Non-zero outputs mean that
 * a function is not working correctly; check the instructions that were run before the output instruction to see
 * which one failed.
 * <p>
 * Finally, the program will output a diagnostic code and immediately halt. This final output isn't an error; an
 * output followed immediately by a halt means the program finished. If all outputs were zero except the diagnostic
 * code, the diagnostic program ran successfully.
 * <p>
 * After providing 1 to the only input instruction and passing all the tests, what diagnostic code does the program
 * produce?
 * <p>
 * --- Part Two ---
 * The air conditioner comes online! Its cold air feels good for a while, but then the TEST alarms start to go off.
 * Since the air conditioner can't vent its heat anywhere but back into the spacecraft, it's actually making the air
 * inside the ship warmer.
 * <p>
 * Instead, you'll need to use the TEST to extend the thermal radiators. Fortunately, the diagnostic program (your
 * puzzle input) is already equipped for this. Unfortunately, your Intcode computer is not.
 * <p>
 * Your computer is only missing a few opcodes:
 * <p>
 * Opcode 5 is jump-if-true: if the first parameter is non-zero, it sets the instruction pointer to the value from
 * the second parameter. Otherwise, it does nothing.
 * Opcode 6 is jump-if-false: if the first parameter is zero, it sets the instruction pointer to the value from the
 * second parameter. Otherwise, it does nothing.
 * Opcode 7 is less than: if the first parameter is less than the second parameter, it stores 1 in the position given
 * by the third parameter. Otherwise, it stores 0.
 * Opcode 8 is equals: if the first parameter is equal to the second parameter, it stores 1 in the position given by
 * the third parameter. Otherwise, it stores 0.
 * Like all instructions, these instructions need to support parameter modes as described above.
 * <p>
 * Normally, after an instruction is finished, the instruction pointer increases by the number of values in that
 * instruction. However, if the instruction modifies the instruction pointer, that value is used and the instruction
 * pointer is not automatically increased.
 * <p>
 * For example, here are several programs that take one input, compare it to the value 8, and then produce one output:
 * <p>
 * 3,9,8,9,10,9,4,9,99,-1,8 - Using position mode, consider whether the input is equal to 8; output 1 (if it is) or 0
 * (if it is not).
 * 3,9,7,9,10,9,4,9,99,-1,8 - Using position mode, consider whether the input is less than 8; output 1 (if it is) or
 * 0 (if it is not).
 * 3,3,1108,-1,8,3,4,3,99 - Using immediate mode, consider whether the input is equal to 8; output 1 (if it is) or 0
 * (if it is not).
 * 3,3,1107,-1,8,3,4,3,99 - Using immediate mode, consider whether the input is less than 8; output 1 (if it is) or 0
 * (if it is not).
 * Here are some jump tests that take an input, then output 0 if the input was zero or 1 if the input was non-zero:
 * <p>
 * 3,12,6,12,15,1,13,14,13,4,13,99,-1,0,1,9 (using position mode)
 * 3,3,1105,-1,9,1101,0,0,12,4,12,99,1 (using immediate mode)
 * Here's a larger example:
 * <p>
 * 3,21,1008,21,8,20,1005,20,22,107,8,21,20,1006,20,31,
 * 1106,0,36,98,0,0,1002,21,125,20,4,20,1105,1,46,104,
 * 999,1105,1,46,1101,1000,1,20,4,20,1105,1,46,98,99
 * The above example program uses an input instruction to ask for a single number. The program will then output 999
 * if the input value is below 8, output 1000 if the input value is equal to 8, or output 1001 if the input value is
 * greater than 8.
 * <p>
 * This time, when the TEST diagnostic program runs its input instruction to get the ID of the system to test,
 * provide it 5, the ID for the ship's thermal radiator controller. This diagnostic test suite only outputs one
 * number, the diagnostic code.
 * <p>
 * What is the diagnostic code for system ID 5?
 */
public class Day5 {

    private static final Logger log = LoggerFactory.getLogger("2019 - Day 5");

    public static void main(String[] args) {

        Queue<Integer> part1Input = new LinkedList<>();
        part1Input.add(1);

        Queue<Integer> part2Input = new LinkedList<>();
        part2Input.add(5);


        IntcodeParser parser = new IntcodeParser();
        IntcodeParser.ReturnValue part1Result = parser.parseIntcode(getInitialIntcode(), part1Input);
        log.info("Part 1 - Diagnostic Code [{}]", part1Result.getOutputs());

        IntcodeParser.ReturnValue part2Result = parser.parseIntcode(getInitialIntcode(), part2Input);
        log.info("Part 2 - Diagnostic Code [{}]", part2Result.getOutputs());
    }

    private static int[] getInitialIntcode() {

        return new int[]{3, 225, 1, 225, 6, 6, 1100, 1, 238, 225, 104, 0, 1101, 65, 39, 225, 2, 14, 169, 224, 101,
                -2340, 224, 224, 4, 224, 1002, 223, 8, 223, 101, 7, 224, 224, 1, 224, 223, 223, 1001, 144, 70, 224,
                101, -96, 224, 224, 4, 224, 1002, 223, 8, 223, 1001, 224, 2, 224, 1, 223, 224, 223, 1101, 92, 65, 225
                , 1102, 42, 8, 225, 1002, 61, 84, 224, 101, -7728, 224, 224, 4, 224, 102, 8, 223, 223, 1001, 224, 5,
                224, 1, 223, 224, 223, 1102, 67, 73, 224, 1001, 224, -4891, 224, 4, 224, 102, 8, 223, 223, 101, 4,
                224, 224, 1, 224, 223, 223, 1102, 54, 12, 225, 102, 67, 114, 224, 101, -804, 224, 224, 4, 224, 102, 8
                , 223, 223, 1001, 224, 3, 224, 1, 224, 223, 223, 1101, 19, 79, 225, 1101, 62, 26, 225, 101, 57, 139,
                224, 1001, 224, -76, 224, 4, 224, 1002, 223, 8, 223, 1001, 224, 2, 224, 1, 224, 223, 223, 1102, 60,
                47, 225, 1101, 20, 62, 225, 1101, 47, 44, 224, 1001, 224, -91, 224, 4, 224, 1002, 223, 8, 223, 101, 2
                , 224, 224, 1, 224, 223, 223, 1, 66, 174, 224, 101, -70, 224, 224, 4, 224, 102, 8, 223, 223, 1001,
                224, 6, 224, 1, 223, 224, 223, 4, 223, 99, 0, 0, 0, 677, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1105, 0,
                99999, 1105, 227, 247, 1105, 1, 99999, 1005, 227, 99999, 1005, 0, 256, 1105, 1, 99999, 1106, 227,
                99999, 1106, 0, 265, 1105, 1, 99999, 1006, 0, 99999, 1006, 227, 274, 1105, 1, 99999, 1105, 1, 280,
                1105, 1, 99999, 1, 225, 225, 225, 1101, 294, 0, 0, 105, 1, 0, 1105, 1, 99999, 1106, 0, 300, 1105, 1,
                99999, 1, 225, 225, 225, 1101, 314, 0, 0, 106, 0, 0, 1105, 1, 99999, 108, 226, 226, 224, 102, 2, 223,
                223, 1005, 224, 329, 101, 1, 223, 223, 1107, 226, 677, 224, 1002, 223, 2, 223, 1005, 224, 344, 101, 1
                , 223, 223, 8, 226, 677, 224, 102, 2, 223, 223, 1006, 224, 359, 101, 1, 223, 223, 108, 677, 677, 224,
                1002, 223, 2, 223, 1005, 224, 374, 1001, 223, 1, 223, 1108, 226, 677, 224, 1002, 223, 2, 223, 1005,
                224, 389, 101, 1, 223, 223, 1007, 677, 677, 224, 1002, 223, 2, 223, 1006, 224, 404, 1001, 223, 1, 223
                , 1108, 677, 677, 224, 102, 2, 223, 223, 1006, 224, 419, 1001, 223, 1, 223, 1008, 226, 677, 224, 102,
                2, 223, 223, 1005, 224, 434, 101, 1, 223, 223, 107, 677, 677, 224, 102, 2, 223, 223, 1006, 224, 449,
                1001, 223, 1, 223, 1007, 226, 677, 224, 102, 2, 223, 223, 1005, 224, 464, 101, 1, 223, 223, 7, 677,
                226, 224, 102, 2, 223, 223, 1005, 224, 479, 101, 1, 223, 223, 1007, 226, 226, 224, 102, 2, 223, 223,
                1005, 224, 494, 101, 1, 223, 223, 7, 677, 677, 224, 102, 2, 223, 223, 1006, 224, 509, 101, 1, 223,
                223, 1008, 677, 677, 224, 1002, 223, 2, 223, 1006, 224, 524, 1001, 223, 1, 223, 108, 226, 677, 224,
                1002, 223, 2, 223, 1006, 224, 539, 101, 1, 223, 223, 8, 226, 226, 224, 102, 2, 223, 223, 1006, 224,
                554, 101, 1, 223, 223, 8, 677, 226, 224, 102, 2, 223, 223, 1005, 224, 569, 1001, 223, 1, 223, 1108,
                677, 226, 224, 1002, 223, 2, 223, 1006, 224, 584, 101, 1, 223, 223, 1107, 677, 226, 224, 1002, 223, 2
                , 223, 1005, 224, 599, 101, 1, 223, 223, 107, 226, 226, 224, 102, 2, 223, 223, 1006, 224, 614, 1001,
                223, 1, 223, 7, 226, 677, 224, 102, 2, 223, 223, 1005, 224, 629, 1001, 223, 1, 223, 107, 677, 226,
                224, 1002, 223, 2, 223, 1005, 224, 644, 1001, 223, 1, 223, 1107, 677, 677, 224, 102, 2, 223, 223,
                1006, 224, 659, 101, 1, 223, 223, 1008, 226, 226, 224, 1002, 223, 2, 223, 1006, 224, 674, 1001, 223,
                1, 223, 4, 223, 99, 226};
    }

}
