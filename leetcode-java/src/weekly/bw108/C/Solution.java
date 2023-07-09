package weekly.bw108.C;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 6923. Partition String Into Minimum Beautiful Substrings
 *
 * https://leetcode.cn/contest/biweekly-contest-108/problems/partition-string-into-minimum-beautiful-substrings/
 *
 * Given a binary string s, partition the string into one or more substrings
 * such that each substring is beautiful.
 *
 * A string is beautiful if:
 *
 * It doesn't contain leading zeros.
 * It's the binary representation of a number that is a power of 5.
 *
 * Return the minimum number of substrings in such partition. If it is impossible to
 * partition the string s into beautiful substrings, return -1.
 *
 * A substring is a contiguous sequence of characters in a string.
 */

public class Solution {

    private final Set<Integer> fives = new HashSet<>();
    {
        for (int i = 1; i < (1 << 16); i *= 5) {
            fives.add(i);
        }
    }

    public int minimumBeautifulSubstrings(String s) {
        if (s.charAt(0) == '0') return -1;
        return minimum(s.toCharArray(), 0, 0);
    }

    private final int[][] memo = new int[16][1 << 15];
    {
        for (var row : memo) Arrays.fill(row, -2);
    }

    private int minimum(char[] chars, int i, int prev) {
        if (i == chars.length) {
            if (fives.contains(prev)) return 1;
            return -1;
        }

        if (memo[i][prev] == -2) {
            int curr = chars[i] - '0', ans = -2;
            int v1 = minimum(chars, i + 1, (prev << 1) | curr);
            if (v1 > 0) ans = v1;
            if (fives.contains(prev) && curr == 1) {
                int v2 = minimum(chars, i + 1, curr);
                if (v2 > 0) ans = ans < 0 ? (v2 + 1) : Math.min(ans, v2 + 1);
            }
            memo[i][prev] = ans == -2 ? -1 : ans;
        }
        return memo[i][prev];
    }

}
