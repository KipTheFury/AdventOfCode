package day3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class WireIntersector {

    public int getClosestIntersection(List<Coords> intersections) {

        ArrayList<Integer> distances = new ArrayList<>();

        for (Coords c : intersections) {
            distances.add(calculateManhattanDistance(c));
        }

        return Collections.min(distances);
    }

    public int getFewestSteps(List<Coords> firstPath, List<Coords> secondPath, List<Coords> intersections) {

        ArrayList<Integer> steps = new ArrayList<>();

        for (Coords intersection : intersections) {
            steps.add((firstPath.indexOf(intersection) + 1) + (secondPath.indexOf(intersection) + 1));
        }

        return Collections.min(steps);
    }

    public List<Coords> getIntersections(List<Coords> first, List<Coords> second) {
        List<Coords> intersections = new ArrayList<>(first);
        intersections.retainAll(second);
        return intersections;
    }

    public List<Coords> getPath(String first) {
        String[] firstWireDirections = first.split(",");
        return calculatePath(firstWireDirections);
    }

    private int calculateManhattanDistance(Coords c) {

        return Math.abs(c.x - 0) + Math.abs(c.y - 0);
    }

    private List<Coords> calculatePath(String[] directions) {

        int x = 0;
        int y = 0;

        List<Coords> path = new ArrayList<>();

        for (String d : directions) {

            char direction = d.charAt(0);
            int distance = Integer.parseInt(d.substring(1));

            switch (direction) {
                case 'U':
                    for (int i = 0; i < distance; i++) {
                        path.add(new Coords(x, y++));
                    }
                    break;
                case 'D':
                    for (int i = 0; i < distance; i++) {
                        path.add(new Coords(x, y--));
                    }
                    break;
                case 'R':
                    for (int i = 0; i < distance; i++) {
                        path.add(new Coords(x++, y));
                    }
                    break;
                case 'L':
                    for (int i = 0; i < distance; i++) {
                        path.add(new Coords(x--, y));
                    }
                    break;
            }
        }

        return path;
    }

    static class Coords {

        int x;
        int y;

        public Coords(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "{" + x + "," + y + '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Coords coords = (Coords) o;
            return x == coords.x && y == coords.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }
}
