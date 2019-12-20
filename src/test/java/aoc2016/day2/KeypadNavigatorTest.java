package aoc2016.day2;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class KeypadNavigatorTest {

    final String[] directions = new String[]{"ULL", "RRDDD", "LURDL", "UUUUD"};

    @Test
    void test_findCode_square() {

        KeypadNavigator nav = new KeypadNavigator();
        assertThat(nav.findCode_square(directions)).isEqualTo("1985");
    }

    @Test
    void test_findCode_diamond() {

        KeypadNavigator nav = new KeypadNavigator();
        assertThat(nav.findCode_diamond(directions)).isEqualTo("5DB3");
    }
}