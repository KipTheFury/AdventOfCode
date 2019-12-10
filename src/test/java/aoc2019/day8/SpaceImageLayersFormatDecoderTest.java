package aoc2019.day8;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SpaceImageLayersFormatDecoderTest {

    @Test
    void test_decodeImage() {

        String input = "123456789012";
        SpaceImageFormatDecoder decoder = new SpaceImageFormatDecoder();

        int[][][] image = decoder.decodeImage(input, 2, 2, 3);

        //2 layers
        assertThat(image[0]).hasSize(2);
        //2 rows
        assertThat(image[0][0]).hasSize(3);

        assertThat(image[0][0]).contains(1, 2, 3);
        assertThat(image[0][1]).contains(4, 5, 6);

        assertThat(image[1][0]).contains(7, 8, 9);
        assertThat(image[1][1]).contains(0, 1, 2);
    }


}