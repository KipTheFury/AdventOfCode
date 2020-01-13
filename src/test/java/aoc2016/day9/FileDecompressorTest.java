package aoc2016.day9;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class FileDecompressorTest {

    @ParameterizedTest
    @MethodSource("inputValues_files")
    void decompress(String file, boolean part2, int length) {

        FileDecompressor decompressor = new FileDecompressor();
        assertThat(decompressor.decompress(file, part2)).isEqualTo(length);
    }

    static Stream<Arguments> inputValues_files() {

        return Stream.of(Arguments.of("ADVENT", false, 6), Arguments.of("A(1x5)BC", false, 7), Arguments.of("(3x3)" +
                "XYZ", false, 9), Arguments.of("A(2x2)BCD(2x2)EFG", false, 11), Arguments.of("(6x1)(1x3)A", false, 6)
                , Arguments.of("X(8x2)(3x3)ABCY", false, 18), Arguments.of("(3x3)XYZ", true, 9), Arguments.of("X(8x2)" +
                        "(3x3)ABCY", true, 20), Arguments.of("(27x12)(20x12)(13x14)(7x10)(1x12)A", true, 241920),
                Arguments.of("(25x3)(3x3)ABC(2x3)XY(5x2)PQRSTX(18x9)(3x2)TWO(5x7)SEVEN", true, 445));
    }
}