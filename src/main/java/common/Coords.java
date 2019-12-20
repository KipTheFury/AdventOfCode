package common;

import java.util.Objects;

public class Coords {

    private final int x;
    private final int y;

    public static final char UP = 'U';
    public static final char DOWN = 'D';
    public static final char RIGHT = 'R';
    public static final char LEFT = 'L';

    public Coords(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Coords move(char direction) {
        switch (direction) {
            case UP:
                return new Coords(x, y + 1);
            case DOWN:
                return new Coords(x, y - 1);
            case RIGHT:
                return new Coords(x + 1, y);
            case LEFT:
                return new Coords(x - 1, y);
            default:
                return this;
        }
    }

    public Coords move(char direction, int distance) {
        switch (direction) {
            case UP:
                return new Coords(x, y + distance);
            case DOWN:
                return new Coords(x, y - distance);
            case RIGHT:
                return new Coords(x + distance, y);
            case LEFT:
                return new Coords(x - distance, y);
            default:
                return this;
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int calculateManhattanDistance(Coords c) {

        return Math.abs(x - c.getX()) + Math.abs(y - c.getY());
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
