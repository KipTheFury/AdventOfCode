package aoc2019.day12;

import org.apache.commons.lang3.ArrayUtils;

import java.util.Arrays;

public class Moon {

    public static final int X = 0;
    public static final int Y = 1;
    public static final int Z = 2;

    int[] position = new int[]{0, 0, 0};
    int[] velocity = new int[]{0, 0, 0};

    public Moon() {
    }

    public Moon(int x, int y, int z) {
        this.position[X] = x;
        this.position[Y] = y;
        this.position[Z] = z;
    }

    public void applyVelocity() {
        position[X] += velocity[X];
        position[Y] += velocity[Y];
        position[Z] += velocity[Z];
    }

    public void calculateGravityFrom(Moon other) {
        for (int i = 0; i < 3; i++) {
            if (position[i] > other.position[i]) {
                velocity[i]--;
            } else if (position[i] < other.position[i]) {
                velocity[i]++;
            }
        }
    }

    @Override
    public String toString() {
        return "pos=" + ArrayUtils.toString(position) + ", vel=" + ArrayUtils.toString(velocity);
    }

    public int calculateEnergy() {

        int potential = Math.abs(position[X]) + Math.abs(position[Y]) + Math.abs(position[Z]);
        int kinetic = Math.abs(velocity[X]) + Math.abs(velocity[Y]) + Math.abs(velocity[Z]);

        return potential * kinetic;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Moon moon = (Moon) o;
        return Arrays.equals(position, moon.position) && Arrays.equals(velocity, moon.velocity);
    }

    @Override
    public int hashCode() {
        int result = Arrays.hashCode(position);
        result = 31 * result + Arrays.hashCode(velocity);
        return result;
    }
}
