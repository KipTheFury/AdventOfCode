package aoc2019.day3;

import common.Coords;

import java.util.HashMap;
import java.util.Map;

public class WireIntersector {

    public int getClosestIntersection(Map<Coords, Integer> intersections) {

        return intersections.entrySet().stream().map(Map.Entry::getKey).mapToInt(this::calculateManhattanDistance).min().getAsInt();
    }

    public int getFewestSteps(Map<Coords, Integer> intersections) {

        return intersections.entrySet().stream().mapToInt(Map.Entry::getValue).min().getAsInt();
    }

    private int calculateManhattanDistance(Coords c) {

        return Math.abs(c.getX()) + Math.abs(c.getY());
    }

    public Map<Coords, Integer> calculateIntersections(String[] wire1, String[] wire2) {

        Map<Coords, Integer> intersections = new HashMap<>();

        Map<Coords, Integer> firstPath = getPath(wire1);
        Map<Coords, Integer> secondPath = getPath(wire2);

        for (Map.Entry<Coords, Integer> entry : firstPath.entrySet()) {

            Coords key = entry.getKey();

            if (secondPath.containsKey(key)) {
                intersections.putIfAbsent(key, entry.getValue() + secondPath.get(key));
            }
        }

        return intersections;
    }

    private Map<Coords, Integer> getPath(String[] wire1) {

        Map<Coords, Integer> path = new HashMap<>();
        Coords first = new Coords(0, 0);

        int steps = 0;

        for (String d : wire1) {
            char direction = d.charAt(0);
            int distance = Integer.parseInt(d.substring(1));
            for (int i = 0; i < distance; i++) {
                path.putIfAbsent(first.move(direction), ++steps);
            }
        }
        return path;
    }


}
