package problem.p321createmaximumnumber;

import common.Checker;

/**
 * 321. Create Maximum Number
 *
 * https://leetcode.cn/problems/create-maximum-number
 *
 * You are given two integer arrays nums1 and nums2 of lengths m and n respectively.
 * nums1 and nums2 represent the digits of two numbers. You are also given an integer k.
 *
 * Create the maximum number of length k <= m + n from digits of the two numbers.
 * The relative order of the digits from the same array must be preserved.
 *
 * Return an array of the k digits representing the answer.
 */

public class Solution {

    public int[] maxNumber(int[] nums1, int[] nums2, int k) {
        // 从 nums1 和 nums2 中按顺序选出 k 个数字, 使得得到的数组最大
        // 可以从 nums1 中取 i 个数字, 同时从 nums2 中取 k - i 个数字, 取最大的情况
        int[] ans = new int[k];
        for (int i = 0; i <= k; i++) {
            if (i > nums1.length || k - i > nums2.length) continue;

            ans = concatAndCompare(ans, select(nums1, i), select(nums2, k - i));
        }

        return ans;
    }

    // 从数组 array 中按顺序选出 k 个最大的数
    private int[] select(int[] array, int k) {
        int n = array.length;
        int[] ans = new int[k + 1]; ans[k] = -1;
        for (int i = 0, t = -1; i < n; i++) {
            // 需要保证剩下的数字加当前栈中的数字足够 k 个
            while (t >= 0 && ans[t] < array[i] && (t + 1) + (n - i) > k) t--;
            if (t < k - 1) ans[++t] = array[i];
        }

        return ans;
    }

    private int[] concatAndCompare(int[] ans, int[] a, int[] b) {
        int[] curr = new int[ans.length]; boolean gt = false;
        for (int i = 0, j = 0, k = 0; i < ans.length; i++) {
            if (!gt && ans[i] > a[j] && ans[i] > b[k]) return ans;

            gt = gt || a[j] > ans[i] || b[k] > ans[i];
            if (compare(a, j, b, k) > 0) curr[i] = a[j++]; else curr[i] = b[k++];
        }
        return curr;
    }

    private int compare(int[] a, int i, int[] b, int j) {
        for (; i < a.length && j < b.length; i++, j++) {
            int cmp = a[i] - b[j];
            if (cmp != 0) return cmp;
        }
        return (a.length - i) - (b.length - j);
    }

    public static void main(String[] args) {
        assert Checker.check(new Solution().maxNumber(new int[]{3,4,6,5}, new int[]{9,1,2,5,8,3}, 5), new int[]{9,8,6,5,3});
        assert Checker.check(new Solution().maxNumber(new int[]{6,0,4}, new int[]{6,7}, 5), new int[]{6,7,6,0,4});
        assert Checker.check(new Solution().maxNumber(new int[]{6,7}, new int[]{6,0,4}, 5), new int[]{6,7,6,0,4});
        assert Checker.check(new Solution().maxNumber(new int[]{3, 9}, new int[]{8, 9}, 3), new int[]{9, 8, 9});
    }

}
