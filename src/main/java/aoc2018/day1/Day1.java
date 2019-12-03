package aoc2018.day1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

/**
 * --- Day 1: Chronal Calibration ---
 * "We've detected some temporal anomalies," one of Santa's Elves at the Temporal Anomaly Research and Detection
 * Instrument Station tells you. She sounded pretty worried when she called you down here. "At 500-year intervals
 * into the past, someone has been changing Santa's history!"
 * <p>
 * "The good news is that the changes won't propagate to our time stream for another 25 days, and we have a device" -
 * she attaches something to your wrist - "that will let you fix the changes with no such propagation delay. It's
 * configured to send you 500 years further into the past every few days; that was the best we could do on such short
 * notice."
 * <p>
 * "The bad news is that we are detecting roughly fifty anomalies throughout time; the device will indicate fixed
 * anomalies with stars. The other bad news is that we only have one device and you're the best person for the job!
 * Good lu--" She taps a button on the device and you suddenly feel like you're falling. To save Christmas, you need
 * to get all fifty stars by December 25th.
 * <p>
 * Collect stars by solving puzzles. Two puzzles will be made available on each day in the Advent calendar; the
 * second puzzle is unlocked when you complete the first. Each puzzle grants one star. Good luck!
 * <p>
 * After feeling like you've been falling for a few minutes, you look at the device's tiny screen. "Error: Device
 * must be calibrated before first use. Frequency drift detected. Cannot maintain destination lock." Below the
 * message, the device shows a sequence of changes in frequency (your puzzle input). A value like +6 means the
 * current frequency increases by 6; a value like -3 means the current frequency decreases by 3.
 * <p>
 * For example, if the device displays frequency changes of +1, -2, +3, +1, then starting from a frequency of zero,
 * the following changes would occur:
 * <p>
 * Current frequency  0, change of +1; resulting frequency  1.
 * Current frequency  1, change of -2; resulting frequency -1.
 * Current frequency -1, change of +3; resulting frequency  2.
 * Current frequency  2, change of +1; resulting frequency  3.
 * In this example, the resulting frequency is 3.
 * <p>
 * Here are other example situations:
 * <p>
 * +1, +1, +1 results in  3
 * +1, +1, -2 results in  0
 * -1, -2, -3 results in -6
 * Starting with a frequency of zero, what is the resulting frequency after all of the changes in frequency have been
 * applied?
 * <p>
 * --- Part Two ---
 * <p>
 * You notice that the device repeats the same frequency change list over and over. To calibrate the device, you need
 * to find the first frequency it reaches twice.
 * <p>
 * For example, using the same list of changes above, the device would loop as follows:
 * <p>
 * Current frequency  0, change of +1; resulting frequency  1.
 * Current frequency  1, change of -2; resulting frequency -1.
 * Current frequency -1, change of +3; resulting frequency  2.
 * Current frequency  2, change of +1; resulting frequency  3.
 * (At this point, the device continues from the start of the list.)
 * Current frequency  3, change of +1; resulting frequency  4.
 * Current frequency  4, change of -2; resulting frequency  2, which has already been seen.
 * In this example, the first frequency reached twice is 2. Note that your device might need to repeat its list of
 * frequency changes many times before a duplicate frequency is found, and that duplicates might be found while in
 * the middle of processing the list.
 * <p>
 * Here are other examples:
 * <p>
 * +1, -1 first reaches 0 twice.
 * +3, +3, +4, -2, -4 first reaches 10 twice.
 * -6, +3, +8, +5, -6 first reaches 5 twice.
 * +7, +7, -2, -7, -4 first reaches 14 twice.
 * What is the first frequency your device reaches twice?
 */
public class Day1 {

    private static final Logger log = LoggerFactory.getLogger("2018 Day1");

    public static void main(String[] args) {

        List<String> linesList = Collections.emptyList();
        try {
            linesList = Files.readAllLines(Paths.get("src/main/resources/2018/day1-frequencies"),
                    StandardCharsets.UTF_8);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
        String[] instructions = linesList.toArray(new String[linesList.size()]);

        FrequencyCalibrator calibrator = new FrequencyCalibrator();

        log.info("Part 1 : Frequency [{}]", calibrator.parseInstructions(instructions));
        log.info("Part 2 : Repeated Frequency [{}]", calibrator.findRepeatedFrequency(instructions));
    }
}
