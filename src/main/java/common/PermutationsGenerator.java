package common;

import java.util.List;

public class PermutationsGenerator {

    public static List<int[]> getPermutationsOfArray(int length, int[] elements, List<int[]> output) {

        if (length == 1) {
            output.add(elements.clone());
        } else {
            for (int i = 0; i < length - 1; i++) {
                getPermutationsOfArray(length - 1, elements, output);
                if (length % 2 == 0) {
                    swap(elements, i, length - 1);
                } else {
                    swap(elements, 0, length - 1);
                }
            }
            getPermutationsOfArray(length - 1, elements, output);
        }
        return output;
    }

    private static void swap(int[] input, int a, int b) {
        int tmp = input[a];
        input[a] = input[b];
        input[b] = tmp;
    }
}
