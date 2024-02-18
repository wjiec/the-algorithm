package com.hasakk.searchs;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class KMPTests {

    @CsvSource({
        "a,         a,      1",
        "aba,       a,      2",
        "aababb,    abb,    1",
        "abcdefg,   hijk,   0"
    })
    @ParameterizedTest
    public void search(String text, String pattern, int count) {
        assertEquals(count, KMP.search(text.toCharArray(), pattern.toCharArray()).size());
    }

    @MethodSource("lpsArgsProvider")
    @ParameterizedTest(name = "lps({0})")
    public void lps(String pattern, int[] want) {
        assertArrayEquals(want, KMP.lps(pattern.toCharArray()), () ->
            String.format("lps(%s) not matched to %s", pattern, Arrays.toString(want)));
    }

    private static Stream<Arguments> lpsArgsProvider() {
        return Stream.of(
            Arguments.arguments("", new int[0]),
            Arguments.arguments("a", new int[]{0}),
            Arguments.arguments("aa", new int[]{0, 1}),
            Arguments.arguments("aabaaab", new int[]{0, 1, 0, 1, 2, 2, 3})
        );
    }

}
