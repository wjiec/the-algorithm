package weekly.w453.D;

import java.util.Arrays;

/**
 * Q4. Minimum Steps to Convert String with Operations
 *
 * https://leetcode.cn/contest/weekly-contest-453/problems/minimum-steps-to-convert-string-with-operations
 *
 * You are given two strings, word1 and word2, of equal length. You need to transform word1 into word2.
 *
 * For this, divide word1 into one or more contiguous substrings. For each substring substr
 * you can perform the following operations:
 *
 * Replace: Replace the character at any one index of substr with another lowercase English letter.
 *
 * Swap: Swap any two characters in substr.
 *
 * Reverse Substring: Reverse substr.
 *
 * Each of these counts as one operation and each character of each substring can be used
 * in each type of operation at most once (i.e. no single index may be involved in more
 * than one replace, one swap, or one reverse).
 *
 * Return the minimum number of operations required to transform word1 into word2.
 */

public class Solution {

    // 每个位置只能操作一次
    public int minOperations(String word1, String word2) {
        return minOperations(word1.toCharArray(), word2.toCharArray(), 0, word1.length());
    }

    private final int[] memo = new int[1 << 16];
    { Arrays.fill(memo, -1); }

    private int minOperations(char[] chars1, char[] chars2, int l, int r) {
        int key = (l << 8) | r;
        if (memo[key] != -1) return memo[key];

        if (equals(chars1, chars2, l, r, false)) return memo[key] = 0;
        if (equals(chars1, chars2, l, r, true)) return memo[key] = 1;

        // 联合使用操作 1 和 2, 找到 chars1 中所有需要修改的字符和目标字符
        //  - 如果 chars1 中有一对 a -> b 和 b -> a 那么直接交换即可
        //  - 否则需要使用操作 1 进行替换
        int ans = 0;
        int[][] correct = new int[26][26];
        for (int i = l; i < r; i++) {
            int a = chars1[i] - 'a', b = chars2[i] - 'a';
            if (a != b) {
                // 检查是否有相反的纠错存在
                //  - 如果有相反的纠错存在, 等于之前那次替换修改为交换
                if (correct[b][a] == 0) {
                    correct[a][b]++; ans++;
                } else correct[b][a]--;
            }
        }

        // 枚举子串
        for (int i = l + 1; i < r; i++) {
            ans = Math.min(ans, minOperations(chars1, chars2, l, i) + minOperations(chars1, chars2, i, r));
        }
        return memo[key] = ans;
    }

    private boolean equals(char[] chars1, char[] chars2, int l, int r, boolean rev) {
        for (int i = 0, n = r - l; i < n; i++) {
            if (chars1[rev ? (r - i - 1) : i] != chars2[i]) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        assert new Solution().minOperations("abcdf", "dacbe") == 4;
        assert new Solution().minOperations("abceded", "baecfef") == 4;
        assert new Solution().minOperations("abcdef", "fedabc") == 2;
    }

}
