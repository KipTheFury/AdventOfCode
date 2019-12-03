package aoc2018.day1;

import java.util.HashSet;
import java.util.Set;

public class FrequencyCalibrator {

    public int parseInstructions(String[] instructions) {

        int frequency = 0;

        for (String instruction : instructions) {
            frequency = parseInstruction(frequency, instruction);
        }

        return frequency;
    }

    public int findRepeatedFrequency(String[] instructions) {

        int repeatedFrequency = 0;
        Set<Integer> repeatedFrequencies = new HashSet<>();
        int frequency = 0;

        while (repeatedFrequency == 0) {

            for (String instruction : instructions) {
                frequency = parseInstruction(frequency, instruction);

                if (!repeatedFrequencies.add(frequency)) {
                    repeatedFrequency = frequency;
                    break;
                }
            }
        }

        return repeatedFrequency;
    }

    private int parseInstruction(int frequency, String instruction) {

        char operation = instruction.charAt(0);
        int value = Integer.parseInt(instruction.substring(1));

        switch (operation) {
            case '-':
                frequency -= value;
                break;
            case '+':
                frequency += value;
        }
        return frequency;
    }
}
