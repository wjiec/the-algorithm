package weekly.w477.C;

import common.Checker;

/**
 * Q3. Concatenate Non-Zero Digits and Multiply by Sum II
 *
 * https://leetcode.cn/contest/weekly-contest-477/problems/concatenate-non-zero-digits-and-multiply-by-sum-ii/
 *
 * You are given a string s of length m consisting of digits. You are also given a 2D integer
 * array queries, where queries[i] = [li, ri].
 *
 * For each queries[i], extract the substring s[li..ri]. Then, perform the following:
 *
 * Form a new integer x by concatenating all the non-zero digits from the substring in their
 * original order. If there are no non-zero digits, x = 0.
 *
 * Let sum be the sum of digits in x. The answer is x * sum.
 *
 * Return an array of integers answer where answer[i] is the answer to the ith query.
 *
 * Since the answers may be very large, return them modulo 1e9 + 7.
 */

public class Solution {

    public int[] sumAndMultiply(String s, int[][] queries) {
        return null;
    }

    public static void main(String[] args) {
        assert Checker.check(new Solution().sumAndMultiply("10203004", new int[][]{{0,7},{1,3},{4,6}}), new int[]{12340, 4, 9});
        assert Checker.check(new Solution().sumAndMultiply("1000", new int[][]{{0,3},{1,1}}), new int[]{1,0});
        assert Checker.check(new Solution().sumAndMultiply("9876543210", new int[][]{{0,9}}), new int[]{444444137});
    }

}
