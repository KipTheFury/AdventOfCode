package aoc2016.day7;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IPv7Validator {

    private static final String withinBracketsRegex = "\\[([a-z]+)\\]";

    public boolean supportsTLS(String ip) {

        List<String> withinBrackets = getSectionsWithinBrackets(ip);
        List<String> outsideBrackets = Arrays.asList(ip.replaceAll(withinBracketsRegex, "-").split("-"));

        boolean abbaOutsideBrackets = false;
        boolean abbaInsideBrackets = false;

        for (String out : outsideBrackets) {
            if (containsAbba(out)) {
                abbaOutsideBrackets = true;
            }
        }

        for (String inside : withinBrackets) {
            if (containsAbba(inside)) {
                abbaInsideBrackets = true;
            }
        }

        return abbaOutsideBrackets && !abbaInsideBrackets;
    }

    public boolean supportsSSL(String ip) {

        List<String> withinBrackets = getSectionsWithinBrackets(ip);
        List<String> outsideBrackets = Arrays.asList(ip.replaceAll(withinBracketsRegex, "-").split("-"));

        for (String outerIpSection : outsideBrackets) {

            List<String> abas = containsAba(outerIpSection);

            for (String aba : abas) {
                for (String innerIpSection : withinBrackets) {
                    if (containsBab(innerIpSection, aba)) return true;
                }
            }
        }
        return false;
    }

    private List<String> getSectionsWithinBrackets(String ip) {
        List<String> withinBrackets = new ArrayList<>();

        Pattern p = Pattern.compile(withinBracketsRegex);
        Matcher m = p.matcher(ip);

        while (m.find()) {
            withinBrackets.add(m.group(1));
        }
        return withinBrackets;
    }

    private List<String> containsAba(String ipSection) {
        List<String> abas = new ArrayList<>();

        for (int i = 0; i < ipSection.length() - 2; i++) {

            char first = ipSection.charAt(i);
            char second = ipSection.charAt(i + 1);
            char third = ipSection.charAt(i + 2);

            if (first == third && second != third) {
                abas.add("" + first + second + third);
            }
        }

        return abas;
    }

    private boolean containsBab(String ipSection, String aba) {

        String bab = "" + aba.charAt(1) + aba.charAt(0) + aba.charAt(1);
        return ipSection.contains(bab);
    }

    private boolean containsAbba(String ipSection) {

        for (int i = 0; i < ipSection.length() - 3; i++) {

            char first = ipSection.charAt(i);
            char second = ipSection.charAt(i + 1);
            char third = ipSection.charAt(i + 2);
            char fourth = ipSection.charAt(i + 3);

            if (first == fourth && second == third && first != second) {
                return true;
            }
        }
        return false;
    }
}
