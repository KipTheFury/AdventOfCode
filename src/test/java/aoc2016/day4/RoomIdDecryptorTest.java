package aoc2016.day4;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class RoomIdDecryptorTest {

    @ParameterizedTest
    @MethodSource("inputValues_RoomIds")
    void isRealRoom(RoomIdDecryptor.RoomID roomID, boolean expected) {

        RoomIdDecryptor decryptor = new RoomIdDecryptor();

        assertThat(decryptor.isRealRoom(roomID)).isEqualTo(expected);
    }

    static Stream<Arguments> inputValues_RoomIds() {

        return Stream.of(Arguments.of(new RoomIdDecryptor.RoomID("aaaaabbbzyx", 123, "abxyz"), true),
                Arguments.of(new RoomIdDecryptor.RoomID("abcdefgh", 123, "abcde"), true),
                Arguments.of(new RoomIdDecryptor.RoomID("notarealroom", 123, "oarel"), true),
                Arguments.of(new RoomIdDecryptor.RoomID("totsllyrealroom", 123, "decoy"), false));

    }
}