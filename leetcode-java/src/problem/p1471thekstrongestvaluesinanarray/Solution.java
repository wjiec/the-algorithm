package problem.p1471thekstrongestvaluesinanarray;

import common.Checker;

import java.util.Arrays;

/**
 * 1471. The k Strongest Values in an Array
 *
 * https://leetcode.cn/problems/the-k-strongest-values-in-an-array/
 *
 * Given an array of integers arr and an integer k.
 *
 * A value arr[i] is said to be stronger than a value arr[j] if |arr[i] - m| > |arr[j] - m|
 * where m is the median of the array.
 * If |arr[i] - m| == |arr[j] - m|, then arr[i] is said to be stronger than arr[j] if arr[i] > arr[j].
 *
 * Return a list of the strongest k values in the array. return the answer in any arbitrary order.
 *
 * Median is the middle value in an ordered integer list. More formally, if the length of the list is n, the
 * median is the element in position ((n - 1) / 2) in the sorted list (0-indexed).
 *
 * For arr = [6, -3, 7, 2, 11], n = 5 and the median is obtained by sorting the array arr = [-3, 2, 6, 7, 11] and
 * the median is arr[m] where m = ((5 - 1) / 2) = 2. The median is 6.
 * For arr = [-7, 22, 17, 3], n = 4 and the median is obtained by sorting the array arr = [-7, 3, 17, 22]
 * and the median is arr[m] where m = ((4 - 1) / 2) = 1. The median is 3.
 */

public class Solution {

    public int[] getStrongest(int[] arr, int k) {
        if (k == arr.length) return arr;

        Arrays.sort(arr);
        int mid = (arr.length - 1) / 2;
        int[] ans = new int[k];
        for (int i = 0, l = 0, r = arr.length - 1; i < k; i++) {
            int lv = Math.abs(arr[mid] - arr[l]);
            int rv = Math.abs(arr[r] - arr[mid]);
            if (lv > rv) ans[i] = arr[l++];
            else ans[i] = arr[r--];
        }
        return ans;
    }

    public static void main(String[] args) {
        assert Checker.anyOrder(new Solution().getStrongest(new int[]{1,2,3,4,5}, 2), new int[]{5,1});
        assert Checker.anyOrder(new Solution().getStrongest(new int[]{1,1,3,5,5}, 2), new int[]{5,5});
        assert Checker.anyOrder(new Solution().getStrongest(new int[]{6,7,11,7,6,8}, 5), new int[]{11,8,6,6,7});
        assert Checker.anyOrder(new Solution().getStrongest(new int[]{6,-3,7,2,11}, 3), new int[]{-3,11,2});
        assert Checker.anyOrder(new Solution().getStrongest(new int[]{-7,22,17,3}, 2), new int[]{22,17});
    }

}
