package aoc2017.day2;

import java.util.List;

public class ChecksumCalculator {

    public int calculateMaxMinChecksum(List<List<Integer>> input) {

        int totalSum = 0;

        for (List<Integer> row : input) {
            int max = row.stream().max(Integer::compare).get();
            int min = row.stream().min(Integer::compare).get();

            totalSum += max - min;
        }
        return totalSum;
    }

    public int calculateEvenlyDivisibleChecksum(List<List<Integer>> input) {

        int totalSum = 0;

        for (List<Integer> row : input) {
            for (int i = 0; i < row.size(); i++) {
                for (int j = 0; j < row.size(); j++) {
                    if (i != j) {
                        if (row.get(i) % row.get(j) == 0) {
                            totalSum += (row.get(i) / row.get(j));
                            break;
                        }
                    }
                }
            }
        }
        return totalSum;
    }
}
