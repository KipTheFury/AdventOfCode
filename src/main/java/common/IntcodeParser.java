package common;

import org.apache.commons.lang3.ArrayUtils;
import org.javatuples.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class IntcodeParser {

    private final Logger log = LoggerFactory.getLogger(IntcodeParser.class);

    private static final int ADD = 1;
    private static final int MULTIPLY = 2;
    private static final int INPUT = 3;
    private static final int OUTPUT = 4;
    private static final int JUMP_IF_TRUE = 5;
    private static final int JUMP_IF_FALSE = 6;
    private static final int LESS_THAN = 7;
    private static final int EQUALS = 8;
    private static final int HALT = 99;

    private int pointer;
    private LinkedList<Integer> outputValues = new LinkedList<>();

    public ReturnValue parseIntcode(int[] intcode, Queue<Integer> input) {

        pointer = 0;

        ReturnValue returnValue = new ReturnValue();

        while (true) {
            if (intcode[pointer] == HALT) {

                returnValue.exitCode = ExitCode.EXIT_99;
                returnValue.intcode = intcode;
                returnValue.outputs = outputValues;

                return returnValue;

            } else {

                int instruction = intcode[pointer];
                int[] subInstruction;

                if (instruction > 100) {
                    subInstruction = Arrays.copyOfRange(intcode, pointer, pointer + 4);
                    parseParameterModes(intcode, subInstruction, input);
                } else {

                    switch (instruction) {
                        case INPUT:
                        case OUTPUT:

                            subInstruction = Arrays.copyOfRange(intcode, pointer, pointer + 2);
                            ExitCode exit = parse2ParamInstruction(subInstruction, intcode, input);

                            if (exit != null) {
                                returnValue.intcode = intcode;
                                returnValue.exitCode = ExitCode.AWAITING_INPUT;
                                returnValue.outputs = outputValues;
                            }

                            break;

                        case JUMP_IF_FALSE:
                        case JUMP_IF_TRUE:

                            subInstruction = Arrays.copyOfRange(intcode, pointer, pointer + 3);
                            parse3ParamInstruction(subInstruction);
                            break;

                        case ADD:
                        case MULTIPLY:
                        case LESS_THAN:
                        case EQUALS:

                            subInstruction = Arrays.copyOfRange(intcode, pointer, pointer + 4);
                            subInstruction[1] = intcode[subInstruction[1]];
                            subInstruction[2] = intcode[subInstruction[2]];
                            parse4ParamInstruction(subInstruction, intcode);
                            break;


                        default:
                            log.error("Invalid OpCode [{}] at intcode[{}]", instruction, pointer);
                            returnValue.exitCode = ExitCode.UNKNOWN_OPCODE;
                            returnValue.outputs = outputValues;
                            returnValue.intcode = intcode;

                            return returnValue;
                    }
                }
            }
        }
    }

    private void parse4ParamInstruction(int[] subInstruction, int[] intcode) {

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

            case LESS_THAN:

                boolean lessThan = param1 < param2;
                log.debug("Processing Instruction [{}] : [{}] < [{}] = [{}] : intcode[{}]", subInstruction, param1,
                        param2, lessThan, writeTo);
                intcode[writeTo] = lessThan ? 1 : 0;
                break;

            case EQUALS:

                boolean equal = param1 == param2;
                log.debug("Processing Instruction [{}] : [{}] == [{}] = [{}] : intcode[{}]", subInstruction, param1,
                        param2, equal, writeTo);
                intcode[writeTo] = equal ? 1 : 0;
                break;
        }
        pointer += 4;
    }

    private void parse3ParamInstruction(int[] subInstruction) {

        int opcode = subInstruction[0];
        int param1 = subInstruction[1];
        int param2 = subInstruction[2];

        switch (opcode) {
            case JUMP_IF_TRUE:
                if (param1 != 0) {
                    log.debug("Processing Instruction [{}] : Pointer set to [{}]", subInstruction, param2);
                    pointer = param2;
                } else {
                    pointer += 3;
                }
                break;

            case JUMP_IF_FALSE:
                if (param1 == 0) {
                    log.debug("Processing Instruction [{}] : Pointer set to [{}]", subInstruction, param2);
                    pointer = param2;
                } else {
                    pointer += 3;
                }
                break;
        }
    }

    private ExitCode parse2ParamInstruction(int[] subInstruction, int[] intcode, Queue<Integer> input) {

        int opcode = subInstruction[0];
        int param1 = subInstruction[1];

        ExitCode exit = null;

        switch (opcode) {
            case INPUT:

                if (input.size() == 0) {
                    exit = ExitCode.AWAITING_INPUT;
                    pointer -= 2;
                } else {
                    log.debug("Processing Instruction [{}] : Input [{}] to intcode[{}]", subInstruction, input, param1);
                    intcode[param1] = input.poll();
                    pointer += 2;
                }
                break;

            case OUTPUT:

                log.debug("Processing Instruction [{}] : Output [{}]", subInstruction, intcode[param1]);
                outputValues.add(intcode[param1]);
                pointer += 2;
                break;
        }


        return exit;
    }

    private void parseParameterModes(int[] intcode, int[] instruction, Queue<Integer> input) {

        int[] parsedInstruction = parseParameterNode(instruction[0]);
        int opcode = parsedInstruction[0] + parsedInstruction[1] * 10;

        if (opcode == ADD || opcode == MULTIPLY || opcode == LESS_THAN || opcode == EQUALS) {

            Pair<Integer, Integer> params = parseParam2_3(parsedInstruction, intcode, instruction);

            int writeTo = instruction[3];
            int[] subInstruction = new int[]{opcode, params.getValue0(), params.getValue1(), writeTo};

            parse4ParamInstruction(subInstruction, intcode);

        } else if (opcode == JUMP_IF_TRUE || opcode == JUMP_IF_FALSE) {

            Pair<Integer, Integer> params = parseParam2_3(parsedInstruction, intcode, instruction);

            int[] subInstruction = new int[]{opcode, params.getValue0(), params.getValue1()};
            parse3ParamInstruction(subInstruction);

        } else if (opcode == INPUT || opcode == OUTPUT) {

            int param1 = parsedInstruction[2] == 0 ? intcode[instruction[1]] : instruction[1];

            int[] subInstruction = new int[]{opcode, param1};
            parse2ParamInstruction(subInstruction, intcode, input);

        } else {
            log.error("Invalid OpCode [{}] at intcode[{}]", opcode, pointer);
        }
    }

    private Pair<Integer, Integer> parseParam2_3(int[] parsedInstruction, int[] intcode, int[] instruction) {

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

        return Pair.with(param1, param2);
    }

    private int[] parseParameterNode(int i) {
        int[] parsedInstruction = Integer.toString(i).chars().map(c -> c - '0').toArray();
        ArrayUtils.reverse(parsedInstruction);
        return parsedInstruction;
    }

    public enum ExitCode {
        AWAITING_INPUT, EXIT_99, UNKNOWN_OPCODE
    }

    public static class ReturnValue {

        private int[] intcode;
        private List<Integer> outputs;
        private ExitCode exitCode;

        public int[] getIntcode() {
            return intcode;
        }

        public void setIntcode(int[] intcode) {
            this.intcode = intcode;
        }

        public List<Integer> getOutputs() {
            return outputs;
        }

        public void setOutputs(List<Integer> outputs) {
            this.outputs = outputs;
        }

        public ExitCode getExitCode() {
            return exitCode;
        }

        public void setExitCode(ExitCode exitCode) {
            this.exitCode = exitCode;
        }
    }
}
