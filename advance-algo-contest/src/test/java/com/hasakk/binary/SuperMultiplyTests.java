package com.hasakk.binary;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class SuperMultiplyTests {

    private final static int MOD = 1_000_000_007;

    @CsvSource({
        "2147483647,            2147483647,             850618742",
        "4611686018427387904,   4611686018427387904,    829977023",
        "9223372036854775806,   9223372036854775806,    155220066",
        "9223372036854775807,   9223372036854775807,    737564071",
    })
    @ParameterizedTest(name = "({1} * {2}) % 1_000_000_007")
    public void mul(long a, long b, long want) {
        Assertions.assertEquals(want, SuperMultiply.mul(a, b, MOD), () ->
            String.format("(%d * %d) %% %d should equals %d", a, b, MOD, want));
    }

}
