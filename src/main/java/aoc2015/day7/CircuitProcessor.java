package aoc2015.day7;

import org.apache.commons.lang3.math.NumberUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class CircuitProcessor {

    public Map<String, Character> parseInstructions(String[] instructions) {


        ArrayList<String> instructionsList =
                Arrays.stream(instructions).collect(Collectors.toCollection(ArrayList::new));
        Map<String, Character> circuit = new HashMap<>();

        while (!instructionsList.isEmpty()) {

            for (int i = 0; i < instructionsList.size(); i++) {

                String instruction = instructionsList.get(i);

                String[] actionTarget = instruction.split(" -> ");
                String action = actionTarget[0];
                String targetWire = actionTarget[1];

                if (action.contains("AND")) {
                    String[] inputs = action.split(" AND ");

                    if (NumberUtils.isParsable(inputs[0])) {
                        Character rightSignal = circuit.get(inputs[1]);

                        if (rightSignal != null) {
                            circuit.put(targetWire, (char) (1 & rightSignal));
                            instructionsList.remove(instruction);
                        }

                    } else {

                        Character leftSignal = circuit.get(inputs[0]);
                        Character rightSignal = circuit.get(inputs[1]);

                        if (leftSignal != null && rightSignal != null) {
                            circuit.put(targetWire, (char) (leftSignal & rightSignal));
                            instructionsList.remove(instruction);
                        }
                    }
                } else if (action.contains("OR")) {
                    String[] inputs = action.split(" OR ");

                    Character leftSignal = circuit.get(inputs[0]);
                    Character rightSignal = circuit.get(inputs[1]);

                    if (leftSignal != null && rightSignal != null) {
                        circuit.put(targetWire, (char) (leftSignal | rightSignal));
                        instructionsList.remove(instruction);
                    }
                } else if (action.contains("LSHIFT")) {
                    String[] inputs = action.split(" LSHIFT ");

                    Character signal = circuit.get(inputs[0]);
                    int amount = Integer.valueOf(inputs[1]);

                    if (signal != null) {
                        circuit.put(targetWire, (char) ((char) signal << amount));
                        instructionsList.remove(instruction);
                    }
                } else if (action.contains("RSHIFT")) {
                    String[] inputs = action.split(" RSHIFT ");

                    Character signal = circuit.get(inputs[0]);
                    int amount = Integer.valueOf(inputs[1]);

                    if (signal != null) {
                        circuit.put(targetWire, (char) ((char) signal >> amount));
                        instructionsList.remove(instruction);
                    }
                } else if (action.contains("NOT")) {
                    String[] inputs = action.split("NOT ");

                    Character signal = circuit.get(inputs[1]);

                    if (signal != null) {
                        circuit.put(targetWire, (char) ~signal);
                        instructionsList.remove(instruction);
                    }
                } else {
                    if (NumberUtils.isParsable(action)) {
                        circuit.put(targetWire, (char) Integer.parseInt(action));
                    } else {
                        circuit.put(targetWire, circuit.get(action));
                    }
                    instructionsList.remove(instruction);
                }
            }
        }
        return circuit;
    }
}
