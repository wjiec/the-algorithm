package daily.d220112p334increasingtripletsubsequence;

/**
 * 334. Increasing Triplet Subsequence
 *
 * https://leetcode-cn.com/problems/increasing-triplet-subsequence/
 *
 * Given an integer array nums, return true if there exists a triple of indices (i, j, k) such
 * that i < j < k and nums[i] < nums[j] < nums[k]. If no such indices exists, return false.
 */

public class Solution {

    public boolean increasingTriplet(int[] nums) {
        if (nums.length < 3) return false;
        int a = nums[0], b = Integer.MAX_VALUE;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > b) return true;
            else if (nums[i] > a) b = nums[i];
            else a = nums[i];
        }
        return false;
    }

    public static void main(String[] args) {
        assert new Solution().increasingTriplet(new int[]{1,2,3,4,5});
        assert !new Solution().increasingTriplet(new int[]{5,4,3,2,1});
        assert new Solution().increasingTriplet(new int[]{2,1,5,0,4,6});
    }

}
