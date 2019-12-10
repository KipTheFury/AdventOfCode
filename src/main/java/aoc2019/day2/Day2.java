package aoc2019.day2;

import common.IntcodeParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * --- Day 2: 1202 Program Alarm ---
 * On the way to your gravity assist around the Moon, your ship computer beeps angrily about a "1202 program alarm".
 * On the radio, an Elf is already explaining how to handle the situation: "Don't worry, that's perfectly norma--"
 * The ship computer bursts into flames.
 * <p>
 * You notify the Elves that the computer's magic smoke seems to have escaped. "That computer ran Intcode programs
 * like the gravity assist program it was working on; surely there are enough spare parts up there to build a new
 * Intcode computer!"
 * <p>
 * An Intcode program is a list of integers separated by commas (like 1,0,0,3,99). To run one, start by looking at
 * the first integer (called position 0). Here, you will find an opcode - either 1, 2, or 99. The opcode indicates
 * what to do; for example, 99 means that the program is finished and should immediately halt. Encountering an
 * unknown opcode means something went wrong.
 * <p>
 * Opcode 1 adds together numbers read from two positions and stores the result in a third position. The three
 * integers immediately after the opcode tell you these three positions - the first two indicate the positions from
 * which you should read the input values, and the third indicates the position at which the output should be stored.
 * <p>
 * For example, if your Intcode computer encounters 1,10,20,30, it should read the values at positions 10 and 20, add
 * those values, and then overwrite the value at position 30 with their sum.
 * <p>
 * Opcode 2 works exactly like opcode 1, except it multiplies the two inputs instead of adding them. Again, the three
 * integers after the opcode indicate where the inputs and outputs are, not their values.
 * <p>
 * Once you're done processing an opcode, move to the next one by stepping forward 4 positions.
 * <p>
 * For example, suppose you have the following program:
 * <p>
 * 1,9,10,3,2,3,11,0,99,30,40,50
 * For the purposes of illustration, here is the same program split into multiple lines:
 * <p>
 * 1,9,10,3,
 * 2,3,11,0,
 * 99,
 * 30,40,50
 * The first four integers, 1,9,10,3, are at positions 0, 1, 2, and 3. Together, they represent the first opcode (1,
 * addition), the positions of the two inputs (9 and 10), and the position of the output (3). To handle this opcode,
 * you first need to get the values at the input positions: position 9 contains 30, and position 10 contains 40. Add
 * these numbers together to get 70. Then, store this value at the output position; here, the output position (3) is
 * at position 3, so it overwrites itself. Afterward, the program looks like this:
 * <p>
 * 1,9,10,70,
 * 2,3,11,0,
 * 99,
 * 30,40,50
 * Step forward 4 positions to reach the next opcode, 2. This opcode works just like the previous, but it multiplies
 * instead of adding. The inputs are at positions 3 and 11; these positions contain 70 and 50 respectively.
 * Multiplying these produces 3500; this is stored at position 0:
 * <p>
 * 3500,9,10,70,
 * 2,3,11,0,
 * 99,
 * 30,40,50
 * Stepping forward 4 more positions arrives at opcode 99, halting the program.
 * <p>
 * Here are the initial and final states of a few more small programs:
 * <p>
 * 1,0,0,0,99 becomes 2,0,0,0,99 (1 + 1 = 2).
 * 2,3,0,3,99 becomes 2,3,0,6,99 (3 * 2 = 6).
 * 2,4,4,5,99,0 becomes 2,4,4,5,99,9801 (99 * 99 = 9801).
 * 1,1,1,4,99,5,6,0,99 becomes 30,1,1,4,2,5,6,0,99.
 * Once you have a working computer, the first step is to restore the gravity assist program (your puzzle input) to
 * the "1202 program alarm" state it had just before the last computer caught fire. To do this, before running the
 * program, replace position 1 with the value 12 and replace position 2 with the value 2. What value is left at
 * position 0 after the program halts?
 * <p>
 * --- Part Two ---
 * "Good, the new computer seems to be working correctly! Keep it nearby during this mission - you'll probably use it
 * again. Real Intcode computers support many more features than your new one, but we'll let you know what they are
 * as you need them."
 * <p>
 * "However, your current priority should be to complete your gravity assist around the Moon. For this mission to
 * succeed, we should settle on some terminology for the parts you've already built."
 * <p>
 * Intcode programs are given as a list of integers; these values are used as the initial state for the computer's
 * memory. When you run an Intcode program, make sure to start by initializing memory to the program's values. A
 * position in memory is called an address (for example, the first value in memory is at "address 0").
 * <p>
 * Opcodes (like 1, 2, or 99) mark the beginning of an instruction. The values used immediately after an opcode, if
 * any, are called the instruction's parameters. For example, in the instruction 1,2,3,4, 1 is the opcode; 2, 3, and
 * 4 are the parameters. The instruction 99 contains only an opcode and has no parameters.
 * <p>
 * The address of the current instruction is called the instruction pointer; it starts at 0. After an instruction
 * finishes, the instruction pointer increases by the number of values in the instruction; until you add more
 * instructions to the computer, this is always 4 (1 opcode + 3 parameters) for the add and multiply instructions.
 * (The halt instruction would increase the instruction pointer by 1, but it halts the program instead.)
 * <p>
 * "With terminology out of the way, we're ready to proceed. To complete the gravity assist, you need to determine
 * what pair of inputs produces the output 19690720."
 * <p>
 * The inputs should still be provided to the program by replacing the values at addresses 1 and 2, just like before.
 * In this program, the value placed in address 1 is called the noun, and the value placed in address 2 is called the
 * verb. Each of the two input values will be between 0 and 99, inclusive.
 * <p>
 * Once the program has halted, its output is available at address 0, also just like before. Each time you try a pair
 * of inputs, make sure you first reset the computer's memory to the values in the program (your puzzle input) - in
 * other words, don't reuse memory from a previous attempt.
 * <p>
 * Find the input noun and verb that cause the program to produce the output 19690720. What is 100 * noun + verb?
 * (For example, if noun=12 and verb=2, the answer would be 1202.)
 * <p>
 * Although it hasn't changed, you can still get your puzzle input.
 */
public class Day2 {

    private static final Logger log = LoggerFactory.getLogger("Day 2");
    private static final IntcodeParser parser = new IntcodeParser();

    public static void main(String[] args) {

        partOne();
        partTwo();
    }

    private static void partOne() {

        int[] intcode = getInitialIntcode();

        intcode[1] = 12;
        intcode[2] = 2;

        IntcodeParser.ReturnValue result = parser.parseIntcode(intcode, null);
        log.info("Part 1 : Intcode result [{}]", result.getIntcode()[0]);
    }

    private static void partTwo() {

        for (int noun = 0; noun <= 99; noun++) {
            for (int verb = 0; verb <= 99; verb++) {

                int[] intcode = getInitialIntcode();

                intcode[1] = noun;
                intcode[2] = verb;

                IntcodeParser.ReturnValue result = parser.parseIntcode(intcode, null);
                if (result.getIntcode()[0] == 19690720) {
                    int answer = 100 * noun + verb;
                    log.info("Part 2:  noun = [{}] verb = [{}] answer = [{}]", noun, verb, answer);
                    return;
                }
            }
        }
    }

    private static int[] getInitialIntcode() {

        return new int[]{1, 0, 0, 3, 1, 1, 2, 3, 1, 3, 4, 3, 1, 5, 0, 3, 2, 9, 1, 19, 1, 19, 5, 23, 1, 23, 6, 27, 2,
                9, 27, 31, 1, 5, 31, 35, 1, 35, 10, 39, 1, 39, 10, 43, 2, 43, 9, 47, 1, 6, 47, 51, 2, 51, 6, 55, 1, 5
                , 55, 59, 2, 59, 10, 63, 1, 9, 63, 67, 1, 9, 67, 71, 2, 71, 6, 75, 1, 5, 75, 79, 1, 5, 79, 83, 1, 9,
                83, 87, 2, 87, 10, 91, 2, 10, 91, 95, 1, 95, 9, 99, 2, 99, 9, 103, 2, 10, 103, 107, 2, 9, 107, 111, 1, 111, 5, 115, 1, 115, 2, 119, 1, 119, 6, 0, 99, 2, 0, 14, 0};
    }
}
