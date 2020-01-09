package aoc2016.day6;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class RepeatingMessageParser {

    public String parseRepeatingMessage(String[] input, boolean mostCommon) {

        Map[] letterCounts = new Map[8];

        for (String message : input) {

            for (int i = 0; i < message.length(); i++) {

                Map<Character, Integer> letterCount = letterCounts[i] != null ? letterCounts[i] : new HashMap<>();

                letterCount.put(message.charAt(i), letterCount.getOrDefault(message.charAt(i), 0) + 1);

                letterCounts[i] = letterCount;
            }
        }

        StringBuilder parsedMessage = new StringBuilder();

        for (Map<Character, Integer> count : letterCounts) {

            if (mostCommon) {
                parsedMessage.append(Collections.max(count.entrySet(), Map.Entry.comparingByValue()).getKey());
            } else {
                parsedMessage.append(Collections.min(count.entrySet(), Map.Entry.comparingByValue()).getKey());
            }
        }

        return parsedMessage.toString();
    }
}
