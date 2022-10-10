package daily.d221010p801minimumswapstomakesequencesincreasing;

import common.Tag;

/**
 * 801. Minimum Swaps To Make Sequences Increasing
 *
 * https://leetcode.cn/problems/minimum-swaps-to-make-sequences-increasing/
 *
 * You are given two integer arrays of the same length nums1 and nums2.
 * In one operation, you are allowed to swap nums1[i] with nums2[i].
 *
 * For example, if nums1 = [1,2,3,8], and nums2 = [5,6,7,4], you can swap the element
 * at i = 3 to obtain nums1 = [1,2,3,4] and nums2 = [5,6,7,8].
 *
 * Return the minimum number of needed operations to make nums1 and nums2 strictly increasing.
 * The test cases are generated so that the given input always makes it possible.
 *
 * An array arr is strictly increasing if and only if arr[0] < arr[1] < arr[2] < ... < arr[arr.length - 1].
 */

public class Solution {

    @Tag({"数组之间相同位置交换"})
    public int minSwap(int[] nums1, int[] nums2) {
        int n = nums1.length;
        // keep[i] 表示从 0 ~ i 且不交换 i 的最小交换次数
        // swap[i] 表示从 0 ~ i 且交换 i 的最小交换次数
        int[] keep = new int[n], swap = new int[n];
        // 初始状态 swap[0] = 1, 表示在第一位进行了一次交换
        swap[0] = 1;

        for (int i = 1; i < n; i++) {
            boolean g1 = nums1[i] > nums1[i - 1] && nums2[i] > nums2[i - 1];
            boolean g2 = nums1[i] > nums2[i - 1] && nums2[i] > nums1[i - 1];

            // 无法交换 s1[i] 和 s2[i] 使得严格递增成立
            // 所以需要维持前一个交换的状态，即
            // 不交换的同时不交换，交换的同时一起交换
            if (g1 && !g2) {
                keep[i] = keep[i - 1];
                swap[i] = swap[i - 1] + 1;
            }

            // 当前位置必须进行交换才能使得严格递增成立
            // 所以需要交换两个状态，即
            // 前面的不交换，交换当前位置
            // 前面的交换，不交换当前位置
            if (!g1 && g2) {
                keep[i] = swap[i - 1];
                swap[i] = keep[i - 1] + 1;
            }

            // 当交换或者不交换都可以时，直接取最小值就好
            if (g1 && g2) {
                keep[i] = swap[i] = Math.min(keep[i - 1], swap[i - 1]);
                swap[i] += 1; // 执行交换
            }
        }

        return Math.min(keep[n - 1], swap[n - 1]);
    }

    public static void main(String[] args) {
        assert new Solution().minSwap(new int[]{0,3,4,9,10}, new int[]{2,3,7,5,6}) == 1;

        assert new Solution().minSwap(new int[]{0,4,4,5,9}, new int[]{0,1,6,8,10}) == 1;
        assert new Solution().minSwap(new int[]{3,3,8,9,10}, new int[]{1,7,4,6,8}) == 1;

        assert new Solution().minSwap(new int[]{1,3,5,4}, new int[]{1,2,3,7}) == 1;
        assert new Solution().minSwap(new int[]{0,3,5,8,9}, new int[]{2,1,4,6,9}) == 1;
    }

}
