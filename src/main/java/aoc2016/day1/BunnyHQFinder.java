package aoc2016.day1;

import common.Coords;

import java.util.HashSet;
import java.util.Set;

public class BunnyHQFinder {

    public int calculateDistanceToBunnyHQ(String[] directions) {

        Coords coord = new Coords(0, 0);
        char currentDirection = Coords.UP;

        for (String dir : directions) {

            String turn = dir.substring(0, 1);
            int distance = Integer.parseInt(dir.substring(1));

            currentDirection = turn(currentDirection, turn);

            coord = coord.move(currentDirection, distance);
        }

        return coord.calculateManhattanDistance(new Coords(0, 0));
    }

    private char turn(char currentDirection, String turn) {
        switch (currentDirection) {

            case Coords.UP:
                currentDirection = turn.equals("R") ? Coords.RIGHT : Coords.LEFT;
                break;

            case Coords.DOWN:
                currentDirection = turn.equals("R") ? Coords.LEFT : Coords.RIGHT;
                break;

            case Coords.RIGHT:
                currentDirection = turn.equals("R") ? Coords.DOWN : Coords.UP;
                break;

            case Coords.LEFT:
                currentDirection = turn.equals("R") ? Coords.UP : Coords.DOWN;
                break;
        }
        return currentDirection;
    }

    public int distanceToClosestRepeatedLocation(String[] directions) {

        Set<Coords> repeatedLocations = new HashSet<>();

        Coords coord = new Coords(0, 0);
        char currentDirection = Coords.UP;
        int directionCount = 0;
        boolean keepLooking = true;

        do {
            String turn = directions[directionCount].substring(0, 1);
            int distance = Integer.parseInt(directions[directionCount].substring(1));
            currentDirection = turn(currentDirection, turn);

            for (int i = 0; i < distance; i++) {
                coord = coord.move(currentDirection);
                if (!repeatedLocations.add(coord)) {
                    keepLooking = false;
                    break;
                }
            }
            directionCount++;

        } while (keepLooking);

        return coord.calculateManhattanDistance(new Coords(0, 0));
    }
}
