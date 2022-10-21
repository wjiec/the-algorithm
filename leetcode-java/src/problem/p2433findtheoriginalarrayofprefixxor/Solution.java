package problem.p2433findtheoriginalarrayofprefixxor;

import common.Checker;

import java.util.Arrays;

/**
 * 2433. Find The Original Array of Prefix Xor
 *
 * https://leetcode.cn/problems/find-the-original-array-of-prefix-xor/
 *
 * You are given an integer array pref of size n. Find and return the
 * array arr of size n that satisfies:
 *
 * pref[i] = arr[0] ^ arr[1] ^ ... ^ arr[i].
 * Note that ^ denotes the bitwise-xor operation.
 *
 * It can be proven that the answer is unique.
 */

public class Solution {

    public int[] findArray(int[] pref) {
        int[] ans = new int[pref.length];

        ans[0] = pref[0];
        for (int i = 1; i < pref.length; i++) {
            ans[i] = pref[i - 1] ^ pref[i];
        }
        return ans;
    }

    public static void main(String[] args) {
        assert Checker.check(new Solution().findArray(new int[]{5,2,0,3,1}), new int[]{5,7,2,3,2});
        assert Checker.check(new Solution().findArray(new int[]{13}), new int[]{13});
    }

}
