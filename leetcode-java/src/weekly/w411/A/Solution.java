package weekly.w411.A;

/**
 * 3258. Count Substrings That Satisfy K-Constraint I
 *
 * https://leetcode.cn/contest/weekly-contest-411/problems/count-substrings-that-satisfy-k-constraint-i/
 *
 * You are given a binary string s and an integer k.
 *
 * A binary string satisfies the k-constraint if either of the following conditions holds:
 *
 * The number of 0's in the string is at most k.
 * The number of 1's in the string is at most k.
 *
 * Return an integer denoting the number of substrings of s that satisfy the k-constraint.
 */

public class Solution {

    public int countKConstraintSubstrings(String s, int k) {
        int[] sum = new int[s.length() + 1];
        for (int i = 1; i <= s.length(); i++) {
            sum[i] = sum[i - 1] + (s.charAt(i - 1) - '0');
        }

        int ans = 0;
        for (int i = 0; i < s.length(); i++) {
            for (int j = i; j < s.length(); j++) {
                int ones = sum[j + 1] - sum[i];
                if (ones <= k || (j - i + 1 - ones) <= k) ans++;
            }
        }

        return ans;
    }

    public static void main(String[] args) {
    }

}
