package problem.p1911maximumalternatingsubsequencesum;

import common.Tag;

/**
 * 1911. Maximum Alternating Subsequence Sum
 *
 * https://leetcode.cn/problems/maximum-alternating-subsequence-sum/
 *
 * The alternating sum of a 0-indexed array is defined as the sum of the elements
 * at even indices minus the sum of the elements at odd indices.
 *
 * For example, the alternating sum of [4,2,5,3] is (4 + 5) - (2 + 3) = 4.
 * Given an array nums, return the maximum alternating sum of any subsequence of
 * nums (after reindexing the elements of the subsequence).
 *
 * A subsequence of an array is a new array generated from the original array by deleting
 * some elements (possibly none) without changing the remaining elements' relative order.
 *
 * For example, [2,7,4] is a subsequence of [4,2,3,7,2,1,4] (the underlined elements), while
 * [2,4,2] is not.
 */

public class Solution {

    @Tag({"子序列交替"})
    public long maxAlternatingSum(int[] nums) {
        long odd = 0, even = nums[0];
        for (int i = 1; i < nums.length; i++) {
            long nextOdd = Math.max(odd, even - nums[i]);
            long nextEven = Math.max(even, odd + nums[i]);
            odd = nextOdd; even = nextEven;
        }
        return even;
    }

    public static void main(String[] args) {
        assert new Solution().maxAlternatingSum(new int[]{4,2,5,3}) == 7;
        assert new Solution().maxAlternatingSum(new int[]{5,6,7,8}) == 8;
        assert new Solution().maxAlternatingSum(new int[]{6,2,1,2,4,5}) == 10;
    }

}
