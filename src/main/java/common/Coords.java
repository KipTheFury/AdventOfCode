package common;

import java.util.Objects;

public class Coords {

    private final int x;
    private final int y;

    public Coords(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Coords move(char direction) {
        switch (direction) {
            case 'U':
                return new Coords(x, y + 1);
            case 'D':
                return new Coords(x, y - 1);
            case 'R':
                return new Coords(x + 1, y);
            case 'L':
                return new Coords(x - 1, y);
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
