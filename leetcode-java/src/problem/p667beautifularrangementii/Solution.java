package problem.p667beautifularrangementii;

import common.Checker;

/**
 * 667. Beautiful Arrangement II
 *
 * https://leetcode-cn.com/problems/beautiful-arrangement-ii/
 *
 * Given two integers n and k, construct a list answer that contains n different positive integers ranging
 * from 1 to n and obeys the following requirement:
 *
 * Suppose this list is answer = [a1, a2, a3, ... , an], then the list
 * [|a1 - a2|, |a2 - a3|, |a3 - a4|, ... , |an-1 - an|] has exactly k distinct integers.
 * Return the list answer. If there multiple valid answers, return any of them.
 */

public class Solution {

    public int[] constructArray(int n, int k) {
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) ans[i] = i + 1;

        int l = n - k, r = n;
        for (int i = n - k - 1, j = 0; i < n; i++, j++) {
            if (j % 2 == 0) ans[i] = l++;
            else ans[i] = r--;
        }
        return ans;
    }

    public static void main(String[] args) {
        assert Checker.check(new Solution().constructArray(3, 1), new int[]{1,2,3});
    }

}
