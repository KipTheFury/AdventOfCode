package aoc2017.day8;

import common.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class RegisterProcessor {

    private static final Logger log = LoggerFactory.getLogger(RegisterProcessor.class);

    public Map<String, Integer> processInstructions(String[] instructions) {

        int maxProcessedValue = 0;

        Map<String, Integer> registers = new HashMap<>();

        for (String instruction : instructions) {

            String[] splitInstruction = instruction.split(" if ");
            String registerMod = splitInstruction[0];
            String condition = splitInstruction[1];

            String[] registerModSplit = registerMod.split(" ");
            String targetRegister = registerModSplit[0];
            String registerAction = registerModSplit[1];
            int registerValue = Integer.parseInt(registerModSplit[2]);

            String[] conditionSplit = condition.split(" ");
            String conditionRegister = conditionSplit[0];
            String conditionAction = conditionSplit[1];
            int conditionValue = Integer.parseInt(conditionSplit[2]);

            if (processCondition(conditionRegister, conditionAction, conditionValue, registers)) {
                processRegisterAction(targetRegister, registerAction, registerValue, registers);
            } else {
                registers.putIfAbsent(targetRegister, 0);
            }

            int maxValueAfterInstruction = MapUtils.getMaxValue(registers).getValue();
            maxProcessedValue = maxValueAfterInstruction > maxProcessedValue ? maxValueAfterInstruction :
                    maxProcessedValue;
        }

        log.info("Part 2 - Max Processed Value [{}]", maxProcessedValue);
        return registers;
    }

    private void processRegisterAction(String register, String action, int value, Map<String, Integer> registers) {

        int currentRegisterValue = registers.getOrDefault(register, 0);

        switch (action) {
            case "inc":
                registers.put(register, currentRegisterValue + value);
                break;
            case "dec":
                registers.put(register, currentRegisterValue - value);
                break;
        }
    }

    private boolean processCondition(String register, String operator, int value, Map<String, Integer> registers) {

        int registerValue = registers.getOrDefault(register, 0);

        switch (operator) {
            case ">":
                return registerValue > value;
            case "<":
                return registerValue < value;
            case ">=":
                return registerValue >= value;
            case "<=":
                return registerValue <= value;
            case "==":
                return registerValue == value;
            case "!=":
                return registerValue != value;
            default:
                return false;
        }
    }
}
