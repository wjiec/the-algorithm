package daily.d220304p2104sumofsubarrayranges;

/**
 * 2104. Sum of Subarray Ranges
 *
 * https://leetcode-cn.com/problems/sum-of-subarray-ranges/
 *
 * You are given an integer array nums. The range of a subarray of nums is the difference
 * between the largest and smallest element in the subarray.
 *
 * Return the sum of all subarray ranges of nums.
 *
 * A subarray is a contiguous non-empty sequence of elements within an array.
 */

public class Solution {

    public long subArrayRanges(int[] nums) {
        long ans = 0, n = nums.length;
        for (int i = 0; i < n; i++) {
            int min = nums[i], max = nums[i];
            for (int j = i + 1; j < n; j++) {
                if (nums[j] < min) min = nums[j];
                else if (nums[j] > max) max = nums[j];
                ans += max - min;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().subArrayRanges(new int[]{1,2,3}) == 4;
        assert new Solution().subArrayRanges(new int[]{1,3,3}) == 4;
        assert new Solution().subArrayRanges(new int[]{4,-2,-3,4,1}) == 59;
    }

}
