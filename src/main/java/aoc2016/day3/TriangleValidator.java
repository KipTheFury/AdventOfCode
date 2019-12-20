package aoc2016.day3;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TriangleValidator {

    private static final String regex = "(\\d+) +(\\d+) +(\\d+)";

    public int validateTriangles(String[] triangles) {

        int valid = 0;

        Pattern p = Pattern.compile(regex);

        for (String triangle : triangles) {
            Matcher m = p.matcher(triangle);
            if (m.find()) {
                if (validateTriangle(Integer.parseInt(m.group(1)), Integer.parseInt(m.group(2)),
                        Integer.parseInt(m.group(3))))
                    valid++;
            }
        }
        return valid;
    }

    public int validateVerticalTriangles(String[] triangles) {

        int valid = 0;

        Pattern p = Pattern.compile(regex);

        for (int i = 0; i < triangles.length; i += 3) {

            Matcher m1 = p.matcher(triangles[i]);
            Matcher m2 = p.matcher(triangles[i + 1]);
            Matcher m3 = p.matcher(triangles[i + 2]);

            if (m1.find() && m2.find() && m3.find()) {

                for (int j = 1; j <= 3; j++) {

                    if (validateTriangle(Integer.parseInt(m1.group(j)), Integer.parseInt(m2.group(j)),
                            Integer.parseInt(m3.group(j))))
                        valid++;
                }

            }
        }

        return valid;
    }

    private boolean validateTriangle(int a, int b, int c) {
        return (a + b > c) && (b + c > a) && (a + c > b);
    }
}
