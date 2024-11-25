package weekly.bw144.B;

/**
 * 3361. Shift Distance Between Two Strings
 *
 * https://leetcode.cn/contest/biweekly-contest-144/problems/shift-distance-between-two-strings/
 *
 * You are given two strings s and t of the same length, and two integer arrays nextCost and previousCost.
 *
 * In one operation, you can pick any index i of s, and perform either one of the following actions:
 *
 * Shift s[i] to the next letter in the alphabet. If s[i] == 'z', you should replace it with 'a'.
 * This operation costs nextCost[j] where j is the index of s[i] in the alphabet.
 *
 * Shift s[i] to the previous letter in the alphabet. If s[i] == 'a', you should replace it with 'z'.
 * This operation costs previousCost[j] where j is the index of s[i] in the alphabet.
 *
 * The shift distance is the minimum total cost of operations required to transform s into t.
 *
 * Return the shift distance from s to t.
 */

public class Solution {

    public long shiftDistance(String s, String t, int[] nextCost, int[] previousCost) {
        // dp[i][j] 表示从 i 变成 j 的最小代价
        long[][] dp = new long[26][26];
        for (int i = 0; i < 26; i++) {
            for (int j = 0; j < 26; j++) {
                // 正向
                long forward = 0;
                for (int curr = i; curr % 26 != j; curr++) {
                    forward += nextCost[curr % 26];
                }
                // 反向
                long backward = 0;
                for (int curr = i; (curr + 26) % 26 != j; curr--) {
                    backward += previousCost[(curr + 26) % 26];
                }

                dp[i][j] = Math.min(forward, backward);
            }
        }

        long ans = 0;
        for (int i = 0; i < s.length(); i++) {
            ans += dp[s.charAt(i) - 'a'][t.charAt(i) - 'a'];
        }
        return ans;
    }

    public static void main(String[] args) {
    }

}
