package aoc2018.day1;

import java.util.ArrayList;
import java.util.List;

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
        List<Integer> repeatedFrequencies = new ArrayList<>();
        int frequency = 0;

        while (repeatedFrequency == 0) {

            for (String instruction : instructions) {
                frequency = parseInstruction(frequency, instruction);
                if (!repeatedFrequencies.contains(frequency)) {
                    repeatedFrequencies.add(frequency);
                } else {
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
