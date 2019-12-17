package aoc2017.day6;

import common.CircularArrayList;
import org.javatuples.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * --- Day 6: Memory Reallocation ---
 * A debugger program here is having an issue: it is trying to repair a memory reallocation routine, but it keeps
 * getting stuck in an infinite loop.
 * <p>
 * In this area, there are sixteen memory banks; each memory bank can hold any number of blocks. The goal of the
 * reallocation routine is to balance the blocks between the memory banks.
 * <p>
 * The reallocation routine operates in cycles. In each cycle, it finds the memory bank with the most blocks (ties
 * won by the lowest-numbered memory bank) and redistributes those blocks among the banks. To do this, it removes all
 * of the blocks from the selected bank, then moves to the next (by index) memory bank and inserts one of the blocks.
 * It continues doing this until it runs out of blocks; if it reaches the last memory bank, it wraps around to the
 * first one.
 * <p>
 * The debugger would like to know how many redistributions can be done before a blocks-in-banks configuration is
 * produced that has been seen before.
 * <p>
 * For example, imagine a scenario with only four memory banks:
 * <p>
 * The banks start with 0, 2, 7, and 0 blocks. The third bank has the most blocks, so it is chosen for redistribution.
 * Starting with the next bank (the fourth bank) and then continuing to the first bank, the second bank, and so on,
 * the 7 blocks are spread out over the memory banks. The fourth, first, and second banks get two blocks each, and
 * the third bank gets one back. The final result looks like this: 2 4 1 2.
 * Next, the second bank is chosen because it contains the most blocks (four). Because there are four memory banks,
 * each gets one block. The result is: 3 1 2 3.
 * Now, there is a tie between the first and fourth memory banks, both of which have three blocks. The first bank
 * wins the tie, and its three blocks are distributed evenly over the other three banks, leaving it with none: 0 2 3 4.
 * The fourth bank is chosen, and its four blocks are distributed such that each of the four banks receives one: 1 3
 * 4 1.
 * The third bank is chosen, and the same thing happens: 2 4 1 2.
 * At this point, we've reached a state we've seen before: 2 4 1 2 was already seen. The infinite loop is detected
 * after the fifth block redistribution cycle, and so the answer in this example is 5.
 * <p>
 * Given the initial block counts in your puzzle input, how many redistribution cycles must be completed before a
 * configuration is produced that has been seen before?
 * <p>
 * --- Part Two ---
 * Out of curiosity, the debugger would also like to know the size of the loop: starting from a state that has
 * already been seen, how many block redistribution cycles must be performed before that same state is seen again?
 * <p>
 * In the example above, 2 4 1 2 is seen again after four cycles, and so the answer in that example would be 4.
 * <p>
 * How many cycles are in the infinite loop that arises from the configuration in your puzzle input?
 */
public class Day6 {

    private static final Logger log = LoggerFactory.getLogger("2017 - Day 6");

    public static void main(String[] args) {

        int[] blocksArray = new int[]{2, 8, 8, 5, 4, 2, 3, 1, 5, 5, 1, 2, 15, 13, 5, 14};
        CircularArrayList<Integer> blockList = new CircularArrayList<>();

        for (int block : blocksArray) {
            blockList.add(block);
        }

        MemoryReallocator reallocator = new MemoryReallocator();
        Pair<Integer, Integer> retVal = reallocator.countRedistributionCycles(blockList);

        log.info("Part 1 - Redistribution cycles before previous state [{}]", retVal.getValue0());
        log.info("Part 1 - Redistribution cycles in infinite loop [{}]", retVal.getValue1());

    }

}
