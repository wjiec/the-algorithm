package problem.p1982findarraygivensubsetsums;

import common.PrettyPrinter;

import java.util.Arrays;

/**
 * 1982. Find Array Given Subset Sums
 *
 * https://leetcode.cn/problems/find-array-given-subset-sums/
 *
 * You are given an integer n representing the length of an unknown array that you are trying to recover.
 *
 * You are also given an array sums containing the values of all 2n subset sums of the
 * unknown array (in no particular order).
 *
 * Return the array ans of length n representing the unknown array.
 * If multiple answers exist, return any of them.
 *
 * An array sub is a subset of an array arr if sub can be obtained from arr by
 * deleting some (possibly zero or all) elements of arr.
 *
 * The sum of the elements in sub is one possible subset sum of arr.
 * The sum of an empty array is considered to be 0.
 *
 * Note: Test cases are generated such that there will always be at least one correct answer.
 */

public class Solution {

    public int[] recoverArray(int n, int[] sums) {
        Arrays.sort(sums);

        int idx = 0;
        int[] ans = new int[n];
        for (int i = n; i >= 2; i--) {
            int diff = sums[1] - sums[0];
            int left = 0, right = 0;
            int[] s = new int[1 << (i - 1)];
            int[] t = new int[1 << (i - 1)];

            int si = 0, ti = 0;
            boolean found = false;
            boolean[] used = new boolean[1 << i];
            while (true) {
                while (left < (1 << i) && used[left]) ++left;
                if (left == 1 << i) break;

                s[si++] = sums[left];
                if (sums[left] == 0) found = true;

                used[left] = true;
                while (used[right] || sums[right] != sums[left] + diff) ++right;

                t[ti++] = sums[right];
                used[right] = true;
            }

            if (found) {
                ans[idx++] = diff;
                sums = s;
            } else {
                ans[idx++] = -diff;
                sums = t;
            }
        }

        ans[idx] = sums[0] + sums[1];
        return ans;
    }


    public static void main(String[] args) {
        PrettyPrinter.println(new Solution().recoverArray(2, new int[]{0,0,0,0}));
        PrettyPrinter.println(new Solution().recoverArray(3, new int[]{-3,-2,-1,0,0,1,2,3}));
        PrettyPrinter.println(new Solution().recoverArray(4, new int[]{0,0,5,5,4,-1,4,9,9,-1,4,3,4,8,3,8}));
    }

}
