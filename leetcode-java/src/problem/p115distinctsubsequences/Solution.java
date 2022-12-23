package problem.p115distinctsubsequences;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 115. Distinct Subsequences
 *
 * https://leetcode.cn/problems/distinct-subsequences/
 *
 * Given two strings s and t, return the number of distinct subsequences of s which equals t.
 *
 * The test cases are generated so that the answer fits on a 32-bit signed integer.
 */

public class Solution {

    public int numDistinct(String s, String t) {
        List<Integer>[] map = new List[128];
        for (int i = 0; i < map.length; i++) {
            map[i] = new ArrayList<>();
        }

        char[] chars = t.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            map[chars[i]].add(i);
        }
        for (var list : map) Collections.reverse(list);

        int[] dp = new int[chars.length + 1]; dp[0] = 1;
        for (var c : s.toCharArray()) {
            for (var idx : map[c]) {
                dp[idx + 1] += dp[idx];
            }
        }

        return dp[chars.length];
    }

    public static void main(String[] args) {
        assert new Solution().numDistinct("rabbbit", "rabbit") == 3;
        assert new Solution().numDistinct("babgbag", "bag") == 5;
    }

}
