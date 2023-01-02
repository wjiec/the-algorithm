package problem.p2522partitionstringintosubstringswithvaluesatmostk;

/**
 * 2522. Partition String Into Substrings With Values at Most K
 *
 * https://leetcode.cn/problems/partition-string-into-substrings-with-values-at-most-k/
 *
 * You are given a string s consisting of digits from 1 to 9 and an integer k.
 *
 * A partition of a string s is called good if:
 *
 * Each digit of s is part of exactly one substring.
 * The value of each substring is less than or equal to k.
 *
 * Return the minimum number of substrings in a good partition of s.
 * If no good partition of s exists, return -1.
 *
 * Note that:
 *
 * The value of a string is its result when interpreted as an integer.
 * For example, the value of "123" is 123 and the value of "1" is 1.
 *
 * A substring is a contiguous sequence of characters within a string.
 */

public class Solution {

    public int minimumPartition(String s, int k) {
        char[] chars = s.toCharArray();
        int[] dp = new int[chars.length + 1];
        for (int i = 1; i <= chars.length; i++) {
            long curr = chars[i - 1] - '0';
            if (curr > k) return -1;

            dp[i] = dp[i - 1] + 1;
            for (int j = i - 1, base = 10; j > 0; j--, base *= 10) {
                curr += (long) (chars[j - 1] - '0') * base;
                if (curr <= k) dp[i] = Math.min(dp[i], dp[j - 1] + 1);
                else break;
            }
        }
        return dp[chars.length];
    }

    public static void main(String[] args) {
        assert new Solution().minimumPartition("12938257226862", 15) == 13;

        assert new Solution().minimumPartition("165462", 60) == 4;
        assert new Solution().minimumPartition("238182", 5) == -1;
    }

}
