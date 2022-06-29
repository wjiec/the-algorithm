package problem.p1073addingtwonegabinarynumbers;

import common.Checker;

import java.util.Arrays;

/**
 * 1073. Adding Two Negabinary Numbers
 *
 * https://leetcode.cn/problems/adding-two-negabinary-numbers/
 *
 * Given two numbers arr1 and arr2 in base -2, return the result of adding them together.
 *
 * Each number is given in array format: as an array of 0s and 1s, from most significant bit
 * to least significant bit. For example, arr = [1,1,0,1] represents
 * the number (-2)^3 + (-2)^2 + (-2)^0 = -3.
 *
 * A number arr in array, format is also guaranteed to have no leading zeros:
 *  either arr == [0] or arr[0] == 1.
 *
 * Return the result of adding arr1 and arr2 in the same format: as an array of 0s
 * and 1s with no leading zeros.
 */

public class Solution {

    public int[] addNegabinary(int[] arr1, int[] arr2) {
        int m = arr1.length, n = arr2.length, v = 0;
        int[] ans = new int[Math.max(m, n) + 2];

        int i = m - 1, j = n - 1, k = ans.length - 1;
        while (i >= 0 || j >= 0 || v != 0) {
            if (i >= 0) v += arr1[i];
            if (j >= 0) v += arr2[j];
            ans[k--] = Math.abs(v) % 2;

            if (v < 0) v = 1;
            else if (v > 1) v = -1;
            else v = 0;
            i--; j--;
        }

        int start = 0;
        while (start < ans.length - 1 && ans[start] == 0) start++;
        return Arrays.copyOfRange(ans, start, ans.length);
    }

    public static void main(String[] args) {
        assert Checker.check(new Solution().addNegabinary(
            new int[]{1,1,1,1,1}, new int[]{1,0,1}
        ), new int[]{1,0,0,0,0});

        assert Checker.check(new Solution().addNegabinary(
            new int[]{0}, new int[]{0}
        ), new int[]{0});

        assert Checker.check(new Solution().addNegabinary(
            new int[]{0}, new int[]{1}
        ), new int[]{1});
    }

}
