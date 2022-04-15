package problem.p565arraynesting;

/**
 * 565. Array Nesting
 *
 * https://leetcode-cn.com/problems/array-nesting/
 *
 * You are given an integer array nums of length n where nums is a permutation of the numbers in the range [0, n - 1].
 *
 * You should build a set s[k] = {nums[k], nums[nums[k]], nums[nums[nums[k]]], ... } subjected to the following rule:
 *
 * The first element in s[k] starts with the selection of the element nums[k] of index = k.
 * The next element in s[k] should be nums[nums[k]], and then nums[nums[nums[k]]], and so on.
 * We stop adding right before a duplicate element occurs in s[k].
 * Return the longest length of a set s[k].
 */

public class Solution {

    public int arrayNesting(int[] nums) {
        int ans = 0, n = nums.length;
        for (int i = 0; i < n; i++) {
            if (nums[i] != Integer.MAX_VALUE) {
                int curr = nums[i], count = 0;
                while (nums[curr] != Integer.MAX_VALUE) {
                    int temp = curr;
                    curr = nums[curr];
                    count++;
                    nums[temp] = Integer.MAX_VALUE;
                }
                ans = Math.max(ans, count);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().arrayNesting(new int[]{5,4,0,3,1,6,2}) == 4;
        assert new Solution().arrayNesting(new int[]{0,1,2}) == 1;
    }

}
