package common;

import org.apache.commons.lang3.ArrayUtils;
import org.javatuples.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class IntcodeParser {

    private final Logger log = LoggerFactory.getLogger(IntcodeParser.class);

    private static final int ADD = 1;
    private static final int MULTIPLY = 2;
    private static final int INPUT = 3;
    private static final int OUTPUT = 4;
    private static final int HALT = 99;

    private int pointer;
    private List<Integer> outputValues;

    public Pair<int[], List<Integer>> parseIntcode(int[] intcode, int input) {

        pointer = 0;
        outputValues = new ArrayList<>();

        while (intcode[pointer] != HALT) {

            int instruction = intcode[pointer];
            int[] subInstruction;

            if (instruction > 100) {
                subInstruction = Arrays.copyOfRange(intcode, pointer, pointer + 4);
                parseParameterModes(intcode, subInstruction, input);
            } else if (instruction == INPUT || instruction == OUTPUT) {
                subInstruction = Arrays.copyOfRange(intcode, pointer, pointer + 2);
                parseInOutInstruction(subInstruction, intcode, input);
            } else if (instruction == ADD || instruction == MULTIPLY) {
                subInstruction = Arrays.copyOfRange(intcode, pointer, pointer + 4);
                subInstruction[1] = intcode[subInstruction[1]];
                subInstruction[2] = intcode[subInstruction[2]];
                parseAddMultiplyInstruction(subInstruction, intcode);
            } else {
                log.error("Invalid OpCode [{}] at intcode[{}]", instruction, pointer);
                break;
            }
        }

        Pair<int[], List<Integer>> retVal = Pair.with(intcode, outputValues);
        return retVal;
    }

    private void parseAddMultiplyInstruction(int[] subInstruction, int[] intcode) {

        int opcode = subInstruction[0];
        int param1 = subInstruction[1];
        int param2 = subInstruction[2];
        int writeTo = subInstruction[3];

        switch (opcode) {
            case ADD:

                log.debug("Processing Instruction [{}] : intcode[{}] = [{}] + [{}]", subInstruction, writeTo, param1,
                        param2);

                intcode[writeTo] = param1 + param2;
                break;

            case MULTIPLY:

                log.debug("Processing Instruction [{}] : intcode[{}] = [{}] * [{}]", subInstruction, writeTo, param1,
                        param2);

                intcode[writeTo] = param1 * param2;
                break;
        }
        pointer += 4;
    }

    private void parseInOutInstruction(int[] subInstruction, int[] intcode, int input) {

        int opcode = subInstruction[0];
        int param1 = subInstruction[1];

        switch (opcode) {
            case INPUT:

                log.debug("Processing Instruction [{}] : Input [{}] to intcode[{}]", subInstruction, input, param1);
                intcode[param1] = input;
                break;

            case OUTPUT:

                log.debug("Processing Instruction [{}] : Output [{}]", subInstruction, intcode[param1]);
                outputValues.add(intcode[param1]);
                break;
        }

        pointer += 2;
    }

    private void parseParameterModes(int[] intcode, int[] instruction, int input) {

        int[] parsedInstruction = parseParameterNode(instruction[0]);
        int opcode = parsedInstruction[0] + parsedInstruction[1] * 10;

        if (opcode == ADD || opcode == MULTIPLY) {

            int paramMode1 = 0;
            int paramMode2 = 0;

            try {
                paramMode1 = parsedInstruction[2];
            } catch (ArrayIndexOutOfBoundsException aioobe) {
                //do nothing - keep as 0
            }

            try {
                paramMode2 = parsedInstruction[3];
            } catch (ArrayIndexOutOfBoundsException aioobe) {
                //do nothing - keep as 0
            }

            int param1 = paramMode1 == 0 ? intcode[instruction[1]] : instruction[1];
            int param2 = paramMode2 == 0 ? intcode[instruction[2]] : instruction[2];
            int writeTo = instruction[3];

            int[] addMultInstruction = new int[]{opcode, param1, param2, writeTo};
            parseAddMultiplyInstruction(addMultInstruction, intcode);

        } else if (opcode == INPUT || opcode == OUTPUT) {

            int param1 = parsedInstruction[2] == 0 ? intcode[instruction[1]] : instruction[1];

            int[] inOutInstruction = new int[]{opcode, param1};
            parseInOutInstruction(inOutInstruction, intcode, input);
        }
    }

    private int[] parseParameterNode(int i) {
        int[] parsedInstruction = Integer.toString(i).chars().map(c -> c - '0').toArray();
        ArrayUtils.reverse(parsedInstruction);
        return parsedInstruction;
    }
}
