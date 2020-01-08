package aoc2015.day5;

import org.apache.commons.lang3.ArrayUtils;

import java.util.HashMap;
import java.util.Map;

public class NaughtyNiceStringValidator {

    public boolean isNice(String s) {

        return contains3Vowels(s) && containsDoubleLetter(s) && !containsNaughtyStrings(s);
    }

    public boolean isNice_v2(String s) {

        return contains2Pairs(s) && containsInterruptedPair(s);
    }

    private boolean contains3Vowels(String s) {

        int vowelCount = 0;
        char[] vowels = {'a', 'e', 'i', 'o', 'u'};

        for (char c : s.toCharArray()) {
            if (ArrayUtils.contains(vowels, c)) vowelCount++;
        }

        return vowelCount >= 3;
    }

    private boolean containsDoubleLetter(String s) {

        for (char ch = 'a'; ch <= 'z'; ch++) {
            if (s.contains("" + ch + ch)) return true;
        }

        return false;
    }

    private boolean containsNaughtyStrings(String s) {
        return s.contains("ab") || s.contains("cd") || s.contains("pq") || s.contains("xy");
    }

    private boolean contains2Pairs(String s) {

        Map<String, Integer> pairs = new HashMap<>();

        String prevPair = "";

        for (int i = 0; i < s.length() - 1; i++) {

            String pair = "" + s.charAt(i) + s.charAt(i + 1);

            if (!pair.equals(prevPair)) {
                pairs.put(pair, pairs.getOrDefault(pair, 0) + 1);
            }

            prevPair = pair;
        }

        for (Map.Entry<String, Integer> entry : pairs.entrySet()) {
            if (entry.getValue() > 1) return true;
        }

        return false;
    }

    private boolean containsInterruptedPair(String s) {

        for (int i = 0; i < s.length() - 2; i++) {
            if (s.charAt(i) == s.charAt(i + 2)) return true;
        }
        return false;
    }
}
