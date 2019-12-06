package aoc2019.day6;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class OrbitMapperTest {

    @Test
    void test_getTotalOrbitCount() {

        String[] input = new String[]{"COM)B",
                "B)C",
                "C)D",
                "D)E",
                "E)F",
                "B)G",
                "G)H",
                "D)I",
                "E)J",
                "J)K",
                "K)L"};

        OrbitMapper mapper = new OrbitMapper();
        mapper.mapOrbits(input);

        assertThat(mapper.getTotalOrbitCount()).isEqualTo(42);
    }

    @Test
    void test_getShortestPathBetweenPlanets() {

        String[] input = new String[]{"COM)B",
                "B)C",
                "C)D",
                "D)E",
                "E)F",
                "B)G",
                "G)H",
                "D)I",
                "E)J",
                "J)K",
                "K)L",
                "K)YOU",
                "I)SAN"};

        OrbitMapper mapper = new OrbitMapper();
        mapper.mapOrbits(input);

        int shortestPath = mapper.getShortestPathBetweenPlanets("YOU", "SAN");
        assertThat(shortestPath).isEqualTo(4);
    }
}