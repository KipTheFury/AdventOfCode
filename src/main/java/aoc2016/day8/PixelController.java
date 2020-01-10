package aoc2016.day8;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PixelController {

    private static final Logger log = LoggerFactory.getLogger(PixelController.class);

    private static final int WIDTH = 50;
    private static final int HEIGHT = 6;
    private static boolean[][] grid = new boolean[WIDTH][HEIGHT];

    public void parseInstructions(String[] instructions) {

        for (String instruction : instructions) {

            String[] split = instruction.split(" ");

            String action = split[0];

            switch (action) {
                case "rect":
                    String[] size = split[1].split("x");

                    int width = Integer.parseInt(size[0]);
                    int height = Integer.parseInt(size[1]);

                    rect(width, height);

                    break;

                case "rotate":

                    int location = Integer.parseInt(split[2].split("=")[1]);
                    int amount = Integer.parseInt(split[split.length - 1]);

                    if (split[1].equals("row")) {
                        for (int a = 0; a < amount; a++) rotateRow(location);
                    } else if (split[1].equals("column")) {
                        for (int a = 0; a < amount; a++) rotateColumn(location);
                    }
                    break;
                default:
                    log.error("Invalid action [{}]", action);
            }
        }
    }

    private void rect(int width, int height) {
        for (int row = 0; row < height; row++) {
            for (int column = 0; column < width; column++) {
                grid[column][row] = true;
            }
        }
    }

    private void rotateRow(int row) {
        boolean newRow[] = new boolean[WIDTH];
        newRow[0] = grid[WIDTH - 1][row];
        for (int column = 1; column < WIDTH; column++) newRow[column] = grid[column - 1][row];
        for (int column = 0; column < WIDTH; column++) grid[column][row] = newRow[column];
    }

    private void rotateColumn(int column) {
        boolean newColumn[] = new boolean[HEIGHT];
        newColumn[0] = grid[column][HEIGHT - 1];
        for (int row = 1; row < HEIGHT; row++) newColumn[row] = grid[column][row - 1];
        for (int row = 0; row < HEIGHT; row++) grid[column][row] = newColumn[row];
    }

    public void display() {

        for (int row = 0; row < HEIGHT; row++) {
            String rowString = "";

            for (int column = 0; column < WIDTH; column++) {
                String pixel = grid[column][row] ? "# " : "  ";
                rowString += pixel;

                if ((column + 1) % 5 == 0) {
                    rowString += "|  ";
                }
            }

            log.info(rowString);
        }
    }

    public int getActivePixels() {
        int activePixels = 0;

        for (int row = 0; row < HEIGHT; row++) {
            for (int column = 0; column < WIDTH; column++) {
                if (grid[column][row]) activePixels++;
            }
        }
        return activePixels;
    }
}
