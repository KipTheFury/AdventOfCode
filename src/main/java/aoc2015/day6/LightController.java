package aoc2015.day6;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LightController {

    private static final Logger log = LoggerFactory.getLogger(LightController.class);
    private static final String regexPattern = "([a-zA-Z ]+)([\\d]{1,3},[\\d]{1,3}) through ([\\d]{1,3},[\\d]{1,3})";

    public boolean[][] parseOnOffInstructions(String[] instructions) {

        boolean[][] grid = new boolean[1000][1000];

        Pattern p = Pattern.compile(regexPattern);

        for (String instruction : instructions) {

            Matcher m = p.matcher(instruction);

            if (m.find()) {

                String action = m.group(1).trim();
                String coord1 = m.group(2).trim();
                String coord2 = m.group(3).trim();

                String[] splitcoord1 = coord1.split(",");
                int[] startCoord = Arrays.stream(splitcoord1).mapToInt(Integer::parseInt).toArray();

                String[] splitcoord2 = coord2.split(",");
                int[] endCoord = Arrays.stream(splitcoord2).mapToInt(Integer::parseInt).toArray();

                for (int x = startCoord[0]; x <= endCoord[0]; x++) {
                    for (int y = startCoord[1]; y <= endCoord[1]; y++) {

                        switch (action) {
                            case "turn off":
                                grid[x][y] = false;
                                break;
                            case "turn on":
                                grid[x][y] = true;
                                break;
                            case "toggle":
                                grid[x][y] = !grid[x][y];
                                break;
                            default:
                                log.error("Invalid Action [{}]", action);
                                break;
                        }
                    }
                }
            }
        }

        return grid;
    }

    public int[][] parseBrightnessInstructions(String[] instructions) {

        int[][] grid = new int[1000][1000];

        Pattern p = Pattern.compile(regexPattern);

        for (String instruction : instructions) {

            Matcher m = p.matcher(instruction);

            if (m.find()) {

                String action = m.group(1).trim();
                String coord1 = m.group(2).trim();
                String coord2 = m.group(3).trim();

                String[] splitcoord1 = coord1.split(",");
                int[] startCoord = Arrays.stream(splitcoord1).mapToInt(Integer::parseInt).toArray();

                String[] splitcoord2 = coord2.split(",");
                int[] endCoord = Arrays.stream(splitcoord2).mapToInt(Integer::parseInt).toArray();

                for (int x = startCoord[0]; x <= endCoord[0]; x++) {
                    for (int y = startCoord[1]; y <= endCoord[1]; y++) {

                        switch (action) {
                            case "turn off":
                                grid[x][y] = grid[x][y] > 0 ? grid[x][y] - 1 : grid[x][y];
                                break;
                            case "turn on":
                                grid[x][y] += 1;
                                break;
                            case "toggle":
                                grid[x][y] += 2;
                                break;
                            default:
                                log.error("Invalid Action [{}]", action);
                                break;
                        }
                    }
                }
            }
        }

        return grid;
    }
}
