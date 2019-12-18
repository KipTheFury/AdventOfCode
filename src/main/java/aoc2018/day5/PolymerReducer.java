package aoc2018.day5;

import java.util.ArrayList;
import java.util.List;

public class PolymerReducer {

    public String optimisePolymerChain(String polymerChain) {

        String shortestPolymerChain = polymerChain;
        String alteredPolymer = "";
        for (int i = 65; i <= 90; i++) {
            alteredPolymer = polymerChain.replaceAll(String.valueOf((char) i), "");
            alteredPolymer = alteredPolymer.replaceAll(String.valueOf((char) (i + 32)), "");

            String processedPolymer = processPolymerChain(alteredPolymer);

            shortestPolymerChain = processedPolymer.length() < shortestPolymerChain.length() ? processedPolymer :
                    shortestPolymerChain;
        }

        return shortestPolymerChain;
    }

    public String processPolymerChain(String polymerChain) {

        List<Character> chain = new ArrayList<>();
        for (char c : polymerChain.toCharArray()) {
            chain.add(c);
        }

        int matchCount = 0;
        do {
            matchCount = 0;
            for (int i = 0; i < (chain.size() - 1); i++) {

                char first = chain.get(i);
                char second = chain.get(i + 1);

                boolean match =
                        (Character.isUpperCase(first) && Character.isLowerCase(second) && first == Character.toUpperCase(second)) || (Character.isLowerCase(first) && Character.isUpperCase(second) && first == Character.toLowerCase(second));

                if (match) {
                    chain.remove(i);
                    chain.remove(i);
                    i++;
                    matchCount++;
                }
            }
        } while (matchCount > 0);

        StringBuilder finalChain = new StringBuilder();
        for (char c : chain) finalChain.append(c);

        return finalChain.toString();
    }
}
