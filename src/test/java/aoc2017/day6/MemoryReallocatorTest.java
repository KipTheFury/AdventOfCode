package aoc2017.day6;

import common.CircularArrayList;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MemoryReallocatorTest {

    @Test
    void test_countRedistributionCycles() {

        CircularArrayList<Integer> blocks = new CircularArrayList<>();
        blocks.add(0);
        blocks.add(2);
        blocks.add(7);
        blocks.add(0);

        MemoryReallocator reallocator = new MemoryReallocator();
        assertThat(reallocator.countRedistributionCycles(blocks).getValue0()).isEqualTo(5);
    }
}