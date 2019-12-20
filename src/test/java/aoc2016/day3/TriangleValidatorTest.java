package aoc2016.day3;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class TriangleValidatorTest {

    @Test
    void test_validateTriangles() {

        String[] input = new String[]{"5 10 25", "1 1 1", "1 2 3"};

        TriangleValidator validator = new TriangleValidator();

        assertThat(validator.validateTriangles(input)).isEqualTo(1);
    }
}