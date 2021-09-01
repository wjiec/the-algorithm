package problem.p1299replaceelementswithgreatestelementonrightside;

import common.Checker;

/**
 * 1299. Replace Elements with Greatest Element on Right Side
 *
 * https://leetcode-cn.com/problems/replace-elements-with-greatest-element-on-right-side/
 *
 * Given an array arr, replace every element in that array with the greatest element
 * among the elements to its right, and replace the last element with -1.
 *
 * After doing so, return the array.
 */

public class Solution {

    public int[] replaceElements(int[] arr) {
        int max = -1;
        for (int i = arr.length - 1; i >= 0; i--) {
            int v = max;
            max = Math.max(v, arr[i]);
            arr[i] = v;
        }
        return arr;
    }

    public static void main(String[] args) {
        assert Checker.check(new Solution().replaceElements(new int[]{17,18,5,4,6,1}), new int[]{18,6,6,6,1,-1});
        assert Checker.check(new Solution().replaceElements(new int[]{400}), new int[]{-1});
    }

}
