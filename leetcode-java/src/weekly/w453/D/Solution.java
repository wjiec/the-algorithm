package weekly.w453.D;

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

    // 每个位置只能操作一次, 也就是每种操作只能执行一次
    public int minOperations(String word1, String word2) {
        int n = word1.length();
        char[] chars1 = word1.toCharArray();
        char[] chars2 = word2.toCharArray();

        // dp[i] 表示在 [0, i) 内使得 word1 与 word2 一样的最小操作次数
        int[] dp = new int[n + 1];
        // 枚举右端点, 然后找到最小的左端点
        for (int r = 0; r < n; r++) {
            dp[r + 1] = Integer.MAX_VALUE; // 初始化

            int forward = 0;
            int[][] correct = new int[26][26];
            for (int l = r; l >= 0; l--) {
                int a = chars1[l] - 'a', b = chars2[l] - 'a';
                if (a != b) {
                    // 检查是否有相反的纠错存在
                    //  - 如果有相反的纠错存在, 等于之前那次替换修改为交换
                    if (correct[b][a] == 0) {
                        correct[a][b]++; forward++;
                    } else correct[b][a]--;
                }

                // 如果从 [l, r] 进行反转的话, 我们需要额外操作一次
                int reversed = 1;
                int[][] revCorrect = new int[26][26];
                for (int p = l; p <= r; p++) {
                    // 如果反转 [l, r] 范围内的字符, 对于在范围内的索引 p 我们将其表示为 l + i 的相对形式
                    //  - 对于位置 p 的相对索引 i, 它反转之后相对位置在 rev = n - i - 1 处
                    //      -> n = r - l + 1
                    //      -> p = l + i
                    //          => i = p - l
                    //
                    //      => rev = n - i - 1
                    //             = (r - l + 1) - (p - l) - 1
                    //             = r - l  + 1- p + l - 1
                    //             = r - p
                    //  - 也就是在 l + (r - p) 位置
                    a = chars1[l + (r - p)] - 'a'; b = chars2[p] - 'a';
                    if (a != b) {
                        if (revCorrect[b][a] == 0) {
                            revCorrect[a][b]++; reversed++;
                        } else revCorrect[b][a]--;
                    }
                }

                dp[r + 1] = Math.min(dp[r + 1], dp[l] + Math.min(forward, reversed));
            }
        }

        return dp[n];
    }

    public static void main(String[] args) {
        assert new Solution().minOperations("abcdf", "dacbe") == 4;
        assert new Solution().minOperations("abceded", "baecfef") == 4;
        assert new Solution().minOperations("abcdef", "fedabc") == 2;
    }

}
