package aoc2015.day6;

import common.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * --- Day 6: Probably a Fire Hazard ---
 * Because your neighbors keep defeating you in the holiday house decorating contest year after year, you've decided
 * to deploy one million lights in a 1000x1000 grid.
 * <p>
 * Furthermore, because you've been especially nice this year, Santa has mailed you instructions on how to display
 * the ideal lighting configuration.
 * <p>
 * Lights in your grid are numbered from 0 to 999 in each direction; the lights at each corner are at 0,0, 0,999,
 * 999,999, and 999,0. The instructions include whether to turn on, turn off, or toggle various inclusive ranges
 * given as coordinate pairs. Each coordinate pair represents opposite corners of a rectangle, inclusive; a
 * coordinate pair like 0,0 through 2,2 therefore refers to 9 lights in a 3x3 square. The lights all start turned off.
 * <p>
 * To defeat your neighbors this year, all you have to do is set up your lights by doing the instructions Santa sent
 * you in order.
 * <p>
 * For example:
 * <p>
 * turn on 0,0 through 999,999 would turn on (or leave on) every light.
 * toggle 0,0 through 999,0 would toggle the first line of 1000 lights, turning off the ones that were on, and
 * turning on the ones that were off.
 * turn off 499,499 through 500,500 would turn off (or leave off) the middle four lights.
 * After following the instructions, how many lights are lit?
 * <p>
 * --- Part Two ---
 * You just finish implementing your winning light pattern when you realize you mistranslated Santa's message from
 * Ancient Nordic Elvish.
 * <p>
 * The light grid you bought actually has individual brightness controls; each light can have a brightness of zero or
 * more. The lights all start at zero.
 * <p>
 * The phrase turn on actually means that you should increase the brightness of those lights by 1.
 * <p>
 * The phrase turn off actually means that you should decrease the brightness of those lights by 1, to a minimum of
 * zero.
 * <p>
 * The phrase toggle actually means that you should increase the brightness of those lights by 2.
 * <p>
 * What is the total brightness of all lights combined after following Santa's instructions?
 * <p>
 * For example:
 * <p>
 * turn on 0,0 through 0,0 would increase the total brightness by 1.
 * toggle 0,0 through 999,999 would increase the total brightness by 2000000.
 */
public class Day6 {

    private static Logger log = LoggerFactory.getLogger("2015 - Day 6");

    public static void main(String[] args) {

        String[] instructions = FileUtils.getLinesAsArray("src/main/resources/2015/day6-instructions");
        LightController controller = new LightController();

        boolean[][] onOffGrid = controller.parseOnOffInstructions(instructions);

        int onCount = 0;

        for (int x = 0; x < onOffGrid.length; x++) {
            for (int y = 0; y < onOffGrid[x].length; y++) {
                if (onOffGrid[x][y]) onCount++;
            }
        }

        log.info("Part 1 - Lights turned on [{}]", onCount);

        int[][] brightnessGrid = controller.parseBrightnessInstructions(instructions);

        int totalBrightness = 0;

        for (int x = 0; x < brightnessGrid.length; x++) {
            for (int y = 0; y < brightnessGrid[x].length; y++) {
                totalBrightness += brightnessGrid[x][y];
            }
        }

        log.info("Part 1 - Total Brightness [{}]", totalBrightness);
    }
}
