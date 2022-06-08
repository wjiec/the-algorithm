package problem.p967numberswithsameconsecutivedifferences;

import common.Checker;

import java.util.HashSet;
import java.util.Set;

/**
 * 967. Numbers With Same Consecutive Differences
 *
 * https://leetcode.cn/problems/numbers-with-same-consecutive-differences/
 *
 * Return all non-negative integers of length n such that the absolute
 * difference between every two consecutive digits is k.
 *
 * Note that every number in the answer must not have leading zeros.
 *
 * For example, 01 has one leading zero and is invalid.
 *
 * You may return the answer in any order.
 */

public class Solution {

    public int[] numsSameConsecDiff(int n, int k) {
        Set<Integer> list = new HashSet<>();
        for (int i = 1; i < 10; i++) dfs(i, n, k, list, 0);

        int i = 0;
        int[] ans = new int[list.size()];
        for (var val : list) ans[i++] = val;
        return ans;
    }

    private void dfs(int digit, int n, int k, Set<Integer> ans, int curr) {
        if (n == 0) { ans.add(curr); return; }

        curr = curr * 10 + digit;
        if (digit + k < 10) dfs(digit + k, n - 1, k, ans, curr);
        if (digit -k >= 0) dfs(digit - k, n - 1, k, ans, curr);
    }

    public static void main(String[] args) {
        assert Checker.anyOrder(new Solution().numsSameConsecDiff(3, 7), new int[]{181,292,707,818,929});
        assert Checker.anyOrder(new Solution().numsSameConsecDiff(2, 1), new int[]{
            10,12,21,23,32,34,43,45,54,56,65,67,76,78,87,89,98
        });
    }

}
