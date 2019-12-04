package aoc2018.day2;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class IdChecksumGenerator {

    public int getChecksum(String[] ids) {

        int two = 0;
        int three = 0;

        for (String id : ids) {
            Collection<Integer> values = parseId(id);

            if (values.contains(Integer.valueOf(2))) {
                two += 1;
            }
            if (values.contains(3)) {
                three += 1;
            }
        }
        return three * two;
    }

    private Collection<Integer> parseId(String id) {

        Map<Character, Integer> letterCounts = new HashMap<>();

        for (char c : id.toCharArray()) {
            letterCounts.put(c, letterCounts.getOrDefault(c, 0) + 1);
        }

        return letterCounts.values();
    }

    public String findCommonLettersInAdjacentIds(String[] ids) {

        String commonLetters = "";

        for (String id : ids) {
            for (String otherId : ids) {
                for (int i = 0; i < id.length(); i++) {
                    if (id.charAt(i) == (otherId.charAt(i))) {
                        commonLetters += id.charAt(i);
                    }
                }
                if (commonLetters.length() == id.length() - 1) {
                    return commonLetters;
                } else {
                    commonLetters = "";
                }
            }
        }

        return commonLetters;
    }

    private int compareIds(String id, String otherId) {

        int closeness = 0;

        for (int i = 0; i < id.length(); i++) {
            closeness += Character.compare(id.charAt(i), otherId.charAt(i));
        }

        return closeness;
    }
}
