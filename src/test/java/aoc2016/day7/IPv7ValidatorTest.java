package aoc2016.day7;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class IPv7ValidatorTest {

    @ParameterizedTest
    @MethodSource("inputValues_ipAddresses_TLS")
    void supportsTLS(String ipAddress, boolean expected) {

        IPv7Validator validator = new IPv7Validator();
        assertThat(validator.supportsTLS(ipAddress)).isEqualTo(expected);
    }

    static Stream<Arguments> inputValues_ipAddresses_TLS() {
        return Stream.of(Arguments.of("abba[mnop]qrst", true), Arguments.of("abcd[bddb]xyyx", false), Arguments.of(
                "aaaa[qwer]tyui", false), Arguments.of("ioxxoj[asdfgh]zxcvbn", true));
    }

    @ParameterizedTest
    @MethodSource("inputValues_ipAddresses_SSL")
    void supportsSSL(String ipAddress, boolean expected) {

        IPv7Validator validator = new IPv7Validator();
        assertThat(validator.supportsSSL(ipAddress)).isEqualTo(expected);
    }

    static Stream<Arguments> inputValues_ipAddresses_SSL() {
        return Stream.of(Arguments.of("aba[bab]xyz", true), Arguments.of("xyx[xyx]xyx", false), Arguments.of("aaa[kek" +
                "]eke", true), Arguments.of("zazbz[bzb]cdb", true));
    }

}