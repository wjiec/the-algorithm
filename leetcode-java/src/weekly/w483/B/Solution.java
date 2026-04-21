package weekly.w483.B;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Q2. Word Squares II
 *
 * https://leetcode.cn/contest/weekly-contest-483/problems/word-squares-ii/
 *
 * You are given a string array words, consisting of distinct 4-letter strings, each containing lowercase English letters.
 *
 * A word square consists of 4 distinct words: top, left, right and bottom, arranged as follows:
 *
 * top forms the top row.
 * bottom forms the bottom row.
 * left forms the left column (top to bottom).
 * right forms the right column (top to bottom).
 * It must satisfy:
 *
 * top[0] == left[0], top[3] == right[0]
 * bottom[0] == left[3], bottom[3] == right[3]
 *
 * Return all valid distinct word squares, sorted in ascending lexicographic
 * order by the 4-tuple (top, left, right, bottom).
 */

public class Solution {

    public List<List<String>> wordSquares(String[] words) {
        Arrays.sort(words, String::compareTo);

        List<List<String>> ans = new ArrayList<>();
        for (int i = 0; i < words.length; i++) {
            char t0 = words[i].charAt(0), t3 = words[i].charAt(3);
            for (int j = 0; j < words.length; j++) {
                if (i == j) continue;
                char l0 = words[j].charAt(0), l3 = words[j].charAt(3);
                if (t0 != l0) continue;

                for (int k = 0; k < words.length; k++) {
                    if (k == i || k == j) continue;
                    char r0 = words[k].charAt(0), r3 = words[k].charAt(3);
                    if (t3 != r0) continue;

                    for (int l = 0; l < words.length; l++) {
                        if (l == i || l == j || l == k) continue;
                        char b0 = words[l].charAt(0), b3 = words[l].charAt(3);
                        if (b0 != l3 || b3 != r3) continue;

                        ans.add(List.of(words[i], words[j], words[k], words[l]));
                    }
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
    }

}
