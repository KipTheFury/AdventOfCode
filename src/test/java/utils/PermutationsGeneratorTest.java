package utils;

import common.PermutationsGenerator;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class PermutationsGeneratorTest {

    @Test
    void test_getPermutationsOfArray() {

        int[] input = new int[]{1, 2, 3};

        List<int[]> output = new ArrayList<>();

        PermutationsGenerator.getPermutationsOfArray(3, input, output);
        assertThat(output).contains(new int[]{1, 2, 3}, new int[]{2, 1, 3}, new int[]{3, 1, 2}, new int[]{1, 3, 2},
                new int[]{2, 3, 1}, new int[]{3, 2, 1});

    }
}