package aoc2019.day12;

import common.CircularArrayList;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MoonGravityMapperTest {

    @Test
    void test_processTimeStep() {

        MoonGravityMapper mapper = new MoonGravityMapper();

        CircularArrayList<Moon> system = new CircularArrayList<>();
        system.add(new Moon(-1, 0, 2));
        system.add(new Moon(2, -10, -7));
        system.add(new Moon(4, -8, 8));
        system.add(new Moon(3, 5, -1));

        mapper.processTimeStep(system);

        assertThat(system.get(0).position).isEqualTo(new int[]{2, -1, 1});
        assertThat(system.get(0).velocity).isEqualTo(new int[]{3, -1, -1});

        assertThat(system.get(1).position).isEqualTo(new int[]{3, -7, -4});
        assertThat(system.get(1).velocity).isEqualTo(new int[]{1, 3, 3});

        assertThat(system.get(2).position).isEqualTo(new int[]{1, -7, 5});
        assertThat(system.get(2).velocity).isEqualTo(new int[]{-3, 1, -3});

        assertThat(system.get(3).position).isEqualTo(new int[]{2, 2, 0});
        assertThat(system.get(3).velocity).isEqualTo(new int[]{-1, -3, 1});

        mapper.processTimeStep(system);
        mapper.processTimeStep(system);
        mapper.processTimeStep(system);

        assertThat(system.get(0).position).isEqualTo(new int[]{2, -8, 0});
        assertThat(system.get(0).velocity).isEqualTo(new int[]{-3, -2, 1});

        assertThat(system.get(1).position).isEqualTo(new int[]{2, 1, 7});
        assertThat(system.get(1).velocity).isEqualTo(new int[]{2, 1, 1});

        assertThat(system.get(2).position).isEqualTo(new int[]{2, 3, -6});
        assertThat(system.get(2).velocity).isEqualTo(new int[]{0, 2, -1});

        assertThat(system.get(3).position).isEqualTo(new int[]{2, -9, 1});
        assertThat(system.get(3).velocity).isEqualTo(new int[]{1, -1, -1});

    }

    @Test
    void calculateSystemEnergy() {


    }
}