package aoc2019.day7;

import common.IntcodeParser;
import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.PermutationsGenerator;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class AmplifierOptimiser {

    private static final Logger log = LoggerFactory.getLogger(AmplifierOptimiser.class);

    public int optimiseAmplifiers(int[] phaseSettings, int[] intcode, int initialInput, boolean feedbackLoop) {

        List<int[]> permutations = new ArrayList<>();
        PermutationsGenerator.getPermutationsOfArray(5, phaseSettings, permutations);

        int maxOutput = 0;
        int output;

        for (int[] permutation : permutations) {

            log.debug("Permutation [{}]", ArrayUtils.toString(permutation));

            List<Amplifier> amplifiers = getAmplifiers(permutation, intcode, initialInput);

            int currentAmplifier = 0;
            int amplifiersProcessed = 0;

            IntcodeParser.ReturnValue retVal = new IntcodeParser.ReturnValue();

            do {
                Amplifier amp = amplifiers.get(currentAmplifier);
                amp.addInputs(retVal.getOutputs());
                retVal = amp.parseIntcode();

                amplifiersProcessed += 1;
                currentAmplifier = (currentAmplifier + 1) % amplifiers.size();

            } while (amplifiersProcessed <= amplifiers.size());

            output = retVal.getOutputs().get(0);

            log.debug("Current Max Signal [{}]", maxOutput);
            log.debug("Permutation Output [{}]", output);

            maxOutput = output > maxOutput ? output : maxOutput;
        }

        return maxOutput;
    }

    private List<Amplifier> getAmplifiers(int[] permutation, int[] intcode, int initialInput) {

        List<Amplifier> amplifiers = new ArrayList<>();

        for (int phaseSetting : permutation) {
            Queue<Integer> initialInstructions = new LinkedList<>();
            initialInstructions.add(phaseSetting);

            amplifiers.add(new Amplifier(phaseSetting, intcode.clone(), initialInstructions));
        }

        List<Integer> initialInputInstruction = new LinkedList<>();
        initialInputInstruction.add(initialInput);
        amplifiers.get(0).addInputs(initialInputInstruction);

        return amplifiers;
    }

    private class Amplifier {

        int phaseSetting;
        int[] intcode;
        IntcodeParser intcodeParser = new IntcodeParser();
        Queue<Integer> inputs;

        public Amplifier(int phaseSetting, int[] intcode, Queue<Integer> inputs) {
            this.phaseSetting = phaseSetting;
            this.intcode = intcode;
            this.inputs = inputs;
        }

        public void addInputs(List<Integer> newInputs) {
            if (newInputs != null) this.inputs.addAll(newInputs);
        }

        public IntcodeParser.ReturnValue parseIntcode() {

            return intcodeParser.parseIntcode(this.intcode, this.inputs);
        }
    }
}


