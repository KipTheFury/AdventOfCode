package aoc2015.day2;

import org.javatuples.Pair;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class WrappingCalculator {

    private static final String presentRegex = "([\\d]+)x([\\d]+)x([\\d]+)";

    public Pair<Integer, Integer> calculateTotalMaterials(String[] presents) {

        int totalWrapping = 0, totalRibbon = 0;

        Pattern regex = Pattern.compile(presentRegex);
        for (String present : presents) {

            Matcher m = regex.matcher(present);
            int l = 0, w = 0, h = 0;

            if (m.find()) {
                l = Integer.parseInt(m.group(1));
                w = Integer.parseInt(m.group(2));
                h = Integer.parseInt(m.group(3));
            }

            totalWrapping += calculateWrapping(l, w, h);
            totalRibbon += calculateRibbon(l, w, h);

        }
        final Pair<Integer, Integer> materials = Pair.with(totalWrapping, totalRibbon);
        return materials;
    }

    private int calculateRibbon(int l, int w, int h) {

        int ribbon = Stream.of(2 * (l + w), 2 * (h + w), 2 * (h + l)).min(Integer::compare).get();
        int bow = l * w * h;

        return ribbon + bow;
    }

    private int calculateWrapping(int l, int w, int h) {

        int wrapping = (2 * l * w) + (2 * h * w) + (2 * h * l);
        int excess = Stream.of(l * w, h * w, h * l).min(Integer::compare).get();

        return wrapping + excess;
    }
}
