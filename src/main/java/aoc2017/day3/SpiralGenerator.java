package aoc2017.day3;

import common.Coords;

import java.util.ArrayList;
import java.util.List;

public class SpiralGenerator {

    public List<Coords> generateSpiral(int count) {

        List<Coords> spiral = new ArrayList<Coords>();

        int direction = 0;
        int stepsCount = 1;
        int stepPosition = 0;
        int stepChange = 0;

        Coords currentCoord = new Coords(0, 0);

        for (int i = 0; i < count; i++) {
            spiral.add(currentCoord);
            System.out.println(currentCoord);

            // Check for direction / step changes
            if (stepPosition < stepsCount) {
                stepPosition++;
            } else {
                stepPosition = 1;
                if (stepChange == 1) {
                    stepsCount++;
                }
                stepChange = (stepChange + 1) % 2;
                direction = (direction + 1) % 4;
            }

            switch (direction) {
                case 0:
                    currentCoord = currentCoord.move('R');
                    break;

                case 1:
                    currentCoord = currentCoord.move('U');
                    break;

                case 2:
                    currentCoord = currentCoord.move('L');
                    break;

                case 3:
                    currentCoord = currentCoord.move('D');
                    break;
            }
        }
        return spiral;
    }
}
