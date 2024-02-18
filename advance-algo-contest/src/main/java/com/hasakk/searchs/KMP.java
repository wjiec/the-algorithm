package com.hasakk.searchs;

import java.util.ArrayList;
import java.util.List;

// the Knuth–Morris–Pratt algorithm (or KMP algorithm)
public class KMP {

    // 找到 pattern 在 text 中的所有出现的位置
    public static List<Integer> search(char[] text, char[] pattern) {
        List<Integer> found = new ArrayList<>();

        int[] next = lps(pattern);
        for (int i = 0, j = 0; i < text.length; i++) {
            while (j > 0 && pattern[j] != text[i]) j = next[j - 1];

            if (text[i] == pattern[j]) j++;
            if (j == pattern.length) {
                found.add(i - j + 1);
                j = next[j - 1];
            }
        }

        return found;
    }

    // 找到最长的相同真前缀和真后缀 (Longest Prefix Suffix)
    //
    // next[i] 表示 pattern[0..i] 的最长真前缀和真后缀的长度
    public static int[] lps(char[] pattern) {
        int[] next = new int[pattern.length];
        for (int i = 1, j = 0; i < pattern.length; i++) {
            var curr = pattern[i];
            while (j > 0 && pattern[j] != curr) {
                j = next[j - 1];
            }

            if (pattern[j] == curr) j++;
            next[i] = j;
        }

        return next;
    }

}
