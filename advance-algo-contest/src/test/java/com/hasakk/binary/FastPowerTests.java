package com.hasakk.binary;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.stream.Stream;

class FastPowerTests {

    private final static int MOD = 1_000_000_007;

    @CsvSource({
        "0,             0,              1",
        "1,             0,              1",
        "2,             10,             1024",
        "1234,          4321,           353466073",
        "2147483647,    2147483647,     234199171",
    })
    @ParameterizedTest(name = "pow({0}, {1}, 1_000_000_007)")
    public void pow(long a, long b, long want) {
        Assertions.assertEquals(want, FastPower.pow(a, b, MOD), () ->
            String.format("pow(%d, %d, %d) should equals %d", a, b, MOD, want));
    }

    @MethodSource("powArgsProvider")
    @ParameterizedTest(name = "pow({0}, {1}, 1_000_000_007)")
    public void pow(long a, int[] b, long want) {
        Assertions.assertEquals(want, FastPower.pow(a, b, MOD), () ->
            String.format("pow(%d, %s, %d) should equals %d", a, Arrays.toString(b), MOD, want));
    }

    private static Stream<Arguments> powArgsProvider() {
        return Stream.of(
            Arguments.arguments(0, new int[]{0}, 1),
            Arguments.arguments(1, new int[]{0}, 1),
            Arguments.arguments(2, new int[]{1, 0}, 1024),
            Arguments.arguments(1234, new int[]{4, 3, 2, 1}, 353466073),
            Arguments.arguments(2147483647, new int[]{2, 1, 4, 7, 4, 8, 3, 6, 4, 7}, 234199171)
        );
    }

}
