package aoc2017.day6;

import common.CircularArrayList;
import org.javatuples.Pair;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.IntStream;

public class MemoryReallocator {

    public Pair<Integer, Integer> countRedistributionCycles(CircularArrayList<Integer> blocks) {

        int stepCount = 0;
        Set<String> previousStates = new HashSet<>();

        while (previousStates.add(blocks.toString())) {
            redistributeBlocks(blocks);
            stepCount++;
        }

        int infiniteLoopStepCount = 0;
        String loopState = blocks.toString();

        do {
            redistributeBlocks(blocks);
            infiniteLoopStepCount++;
        } while (!loopState.equals(blocks.toString()));

        Pair<Integer, Integer> retVal = Pair.with(stepCount, infiniteLoopStepCount);

        return retVal;
    }

    private void redistributeBlocks(CircularArrayList<Integer> blocks) {
        int indexOfMax = IntStream.range(0, blocks.size()).boxed().max(Comparator.comparing(blocks::get)).orElse(-1);

        int blockCount = blocks.get(indexOfMax);
        blocks.set(indexOfMax, 0);

        for (int i = indexOfMax + 1; i < (indexOfMax + blockCount + 1); i++) {
            blocks.set(i, blocks.get(i) + 1);
        }
    }
}
