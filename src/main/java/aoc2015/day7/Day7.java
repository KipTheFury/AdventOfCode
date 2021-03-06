package aoc2015.day7;

import common.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * --- Day 7: Some Assembly Required ---
 * This year, Santa brought little Bobby Tables a set of wires and bitwise logic gates! Unfortunately, little Bobby
 * is a little under the recommended age range, and he needs help assembling the circuit.
 * <p>
 * Each wire has an identifier (some lowercase letters) and can carry a 16-bit signal (a number from 0 to 65535). A
 * signal is provided to each wire by a gate, another wire, or some specific value. Each wire can only get a signal
 * from one source, but can provide its signal to multiple destinations. A gate provides no signal until all of its
 * inputs have a signal.
 * <p>
 * The included instructions booklet describes how to connect the parts together: x AND y -> z means to connect wires
 * x and y to an AND gate, and then connect its output to wire z.
 * <p>
 * For example:
 * <p>
 * 123 -> x means that the signal 123 is provided to wire x.
 * x AND y -> z means that the bitwise AND of wire x and wire y is provided to wire z.
 * p LSHIFT 2 -> q means that the value from wire p is left-shifted by 2 and then provided to wire q.
 * NOT e -> f means that the bitwise complement of the value from wire e is provided to wire f.
 * Other possible gates include OR (bitwise OR) and RSHIFT (right-shift). If, for some reason, you'd like to emulate
 * the circuit instead, almost all programming languages (for example, C, JavaScript, or Python) provide operators
 * for these gates.
 * <p>
 * For example, here is a simple circuit:
 * <p>
 * 123 -> x
 * 456 -> y
 * x AND y -> d
 * x OR y -> e
 * x LSHIFT 2 -> f
 * y RSHIFT 2 -> g
 * NOT x -> h
 * NOT y -> i
 * After it is run, these are the signals on the wires:
 * <p>
 * d: 72
 * e: 507
 * f: 492
 * g: 114
 * h: 65412
 * i: 65079
 * x: 123
 * y: 456
 * In little Bobby's kit's instructions booklet (provided as your puzzle input), what signal is ultimately provided
 * to wire a?
 */
public class Day7 {

    private static final Logger log = LoggerFactory.getLogger("2015 - Day 7");

    public static void main(String[] args) {

        String[] instructions = FileUtils.getLinesAsArray("src/main/resources/2015/day7-instructions");
        CircuitProcessor processor = new CircuitProcessor();

        Map<String, Character> circuit = processor.parseInstructions(instructions);

        log.info("Part 1 - Signal at wire a [{}]", (int) circuit.get("a"));
    }

}
