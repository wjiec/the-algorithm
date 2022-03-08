package daily.d220308p2055platesbetweencandles;

import common.Checker;

/**
 * 2055. Plates Between Candles
 *
 * https://leetcode-cn.com/problems/plates-between-candles/
 *
 * There is a long table with a line of plates and candles arranged on top of it. You are given a 0-indexed
 * string s consisting of characters '*' and '|' only, where a '*' represents a plate and a '|' represents a candle.
 *
 * You are also given a 0-indexed 2D integer array queries where queries[i] = [lefti, righti] denotes the
 * substring s[lefti...righti] (inclusive). For each query, you need to find the number of plates
 * between candles that are in the substring.
 *
 * A plate is considered between candles if there is at least one candle to its left and at
 * least one candle to its right in the substring.
 *
 * For example, s = "||**||**|*", and a query [3, 8] denotes the substring "*||**|".
 * The number of plates between candles in this substring is 2, as each of the two plates has at
 * least one candle in the substring to its left and right.
 *
 * Return an integer array answer where answer[i] is the answer to the ith query.
 */

public class Solution {

    public int[] platesBetweenCandles(String s, int[][] queries) {
        int[] sum = new int[s.length()];
        for (int i = 0, n = s.length(); i < n; i++) {
            if (i != 0) sum[i] = sum[i - 1];
            if (s.charAt(i) == '*') sum[i]++;
        }

        int[] left = new int[s.length()];
        for (int i = 0, l = -1, n = s.length(); i < n; i++) {
            if (s.charAt(i) == '|') l = i;
            left[i] = l;
        }

        int[] right = new int[s.length()];
        for (int i = s.length() - 1, r = -1; i >= 0; i--) {
            if (s.charAt(i) == '|') r = i;
            right[i] = r;
        }

        int[] ans = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int l = right[queries[i][0]], r = left[queries[i][1]];
            ans[i] = (l == -1 || r == -1 || l >= r) ? 0 : (sum[r] - sum[l]);
        }

        return ans;
    }

    public static void main(String[] args) {
        assert Checker.check(new Solution().platesBetweenCandles("**|**|***|", new int[][]{{2,5}, {5,9}}), new int[]{2, 3});
        assert Checker.check(new Solution().platesBetweenCandles("***|**|*****|**||**|*", new int[][]{
            {1,17}, {4,5}, {14,17}, {5,11}, {15,16}
        }), new int[]{9,0,0,0,0});
    }

}
