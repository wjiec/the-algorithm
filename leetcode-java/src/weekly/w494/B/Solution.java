package weekly.w494.B;

/**
 * Q2. Construct Uniform Parity Array II
 *
 * https://leetcode.cn/contest/weekly-contest-494/problems/construct-uniform-parity-array-ii/
 *
 * You are given an array nums1 of n distinct integers.
 *
 * You want to construct another array nums2 of length n such that
 * the elements in nums2 are either all odd or all even.
 *
 * For each index i, you must choose exactly one of the following (in any order):
 *
 * nums2[i] = nums1[i]
 * nums2[i] = nums1[i] - nums1[j], for an index j != i, such that nums1[i] - nums1[j] >= 1
 *
 * Return true if it is possible to construct such an array, otherwise return false.
 */

public class Solution {

    public boolean uniformArray(int[] nums1) {
        int n = nums1.length, odd = 0, mnOdd = Integer.MAX_VALUE, mnEven = Integer.MAX_VALUE;
        // 否则我们需要让里面的所有奇数都变成偶数, 或者让所有的偶数变成奇数
        //  - 让所有奇数都变成偶数: 奇数 - 奇数 = 偶数, 也就是至少存在 2 个奇数
        //      - 要求 nums[i] - nums[j] 要 >= 1, 那么对于最小的奇数, 我们最大只能完成 min_odd - min_odd = 0 < 1, 无法满足
        //  - 让所有的偶数变成奇数: 偶数 - 奇数 = 奇数, 也就是至少存在 1 个奇数
        //      - 要求 nums[i] - nums[j] 要 >= 1, 也就是要求最小的偶数 - 最小的奇数 >= 1
        for (var v : nums1) {
            if ((v & 1) == 1) { odd++; mnOdd = Math.min(mnOdd, v); }
            else mnEven = Math.min(mnEven, v);
        }
        return odd == n || odd == 0 || mnEven - mnOdd >= 1;
    }

    public static void main(String[] args) {
    }

}
