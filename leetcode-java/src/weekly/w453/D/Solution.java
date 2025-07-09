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

@SuppressWarnings("DuplicatedCode")
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

    private static class Optimization {
        public int minOperations(String word1, String word2) {
            int n = word1.length();
            char[] chars1 = word1.toCharArray();
            char[] chars2 = word2.toCharArray();

            // 预处理所有的反转字符串的最少操作系数, 对于 a ... b 和 b ... a 来说
            //  - 如果一个字符串的前后字符反向相同, 则实际就等于中间 ... 的最小操作次数
            //
            // 预处理方式是使用中心扩展法, 当 i 是偶数时, 枚举的是奇数长度; 当 i 是奇数时, 枚举的是偶数长度
            int[][] rev = new int[n][n];
            for (int i = 0; i < 2 * n - 1; i++) {
                int[][] correct = new int[26][26];
                int l = i / 2, r = (i + 1) / 2, curr = 1; // 默认需要一次反转
                while (l >= 0 && r < n) {
                    // 左半边
                    int a = chars1[l] - 'a', b = chars2[r] - 'a';
                    if (a != b) {
                        if (correct[a][b] == 0) {
                            correct[b][a]++; curr++;
                        } else correct[a][b]--;
                    }

                    // 右半边(如果有的话)
                    a = chars1[r] - 'a'; b = chars2[l] - 'a';
                    if (l != r && a != b) {
                        if (correct[a][b] == 0) {
                            correct[b][a]++; curr++;
                        } else correct[a][b]--;
                    }

                    rev[l--][r++] = curr;
                }
            }

            // 剩下的操作与原始版本一致, 最近降低至 O(n^2)
            int[] dp = new int[n + 1];
            for (int r = 0; r < n; r++) {
                dp[r + 1] = Integer.MAX_VALUE;

                int curr = 0;
                int[][] correct = new int[26][26];
                for (int l = r; l >= 0; l--) {
                    int a = chars1[l] - 'a', b = chars2[l] - 'a';
                    if (a != b) {
                        if (correct[a][b] == 0) {
                            correct[b][a]++; curr++;
                        } else correct[a][b]--;
                    }

                    dp[r + 1] = Math.min(dp[r + 1], dp[l] + Math.min(curr, rev[l][r]));
                }
            }
            return dp[n];
        }
    }

    public static void main(String[] args) {
        assert new Optimization().minOperations("abcdf", "dacbe") == 4;
        assert new Optimization().minOperations("abceded", "baecfef") == 4;
        assert new Optimization().minOperations("abcdef", "fedabc") == 2;

        assert new Solution().minOperations("abcdf", "dacbe") == 4;
        assert new Solution().minOperations("abceded", "baecfef") == 4;
        assert new Solution().minOperations("abcdef", "fedabc") == 2;
    }

}
