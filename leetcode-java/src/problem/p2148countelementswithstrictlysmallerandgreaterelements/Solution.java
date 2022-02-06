package problem.p2148countelementswithstrictlysmallerandgreaterelements;

/**
 * 2148. Count Elements With Strictly Smaller and Greater Elements
 *
 * https://leetcode-cn.com/problems/count-elements-with-strictly-smaller-and-greater-elements/
 *
 * Given an integer array nums, return the number of elements that have both a strictly smaller
 * and a strictly greater element appear in nums.
 */

public class Solution {

    public int countElements(int[] nums) {
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        for (var n : nums) {
            min = Math.min(min, n);
            max = Math.max(max, n);
        }

        int ans = 0;
        for (var n : nums) {
            if (min < n && n < max) {
                ans++;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().countElements(new int[]{11,7,2,15}) == 2;
        assert new Solution().countElements(new int[]{-3,3,3,90}) == 2;
    }

}
