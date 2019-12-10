package aoc2019.day8;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SpaceImageFormatDecoderTest {

    @Test
    void test_decodeImage() {

        String input = "123456789012";
        SpaceImageFormatDecoder decoder = new SpaceImageFormatDecoder();

        SpaceImageFormatDecoder.Image image = decoder.decodeImage(input, 2, 3);

        assertThat(image.layers).hasSize(2);
        SpaceImageFormatDecoder.Layer layer0 = image.layers.get(0);

        assertThat(layer0.rows).hasSize(2);
        SpaceImageFormatDecoder.Row row0 = layer0.rows.get(0);

        assertThat(row0.characters).contains('1', '2', '3');
    }
}