package problem.p724findpivotindex;

/**
 * 724. Find Pivot Index
 *
 * https://leetcode-cn.com/problems/find-pivot-index/
 *
 * Given an array of integers nums, calculate the pivot index of this array.
 *
 * The pivot index is the index where the sum of all the numbers strictly to the
 * left of the index is equal to the sum of all the numbers strictly to the index's right.
 *
 * If the index is on the left edge of the array, then the left sum is 0 because there are
 * no elements to the left. This also applies to the right edge of the array.
 *
 * Return the leftmost pivot index. If no such index exists, return -1.
 */

public class Solution {

    public int pivotIndex(int[] nums) {
        int n = nums.length, sum = 0, ans = -1, left = 0;
        for (var num : nums) {
            sum += num;
        }

        for (int i = 0; i < n; i++) {
            if (sum - nums[i] == left) {
                ans = i;
                break;
            }

            sum -= nums[i];
            left += nums[i];
        }

        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().pivotIndex(new int[]{1, 7, 3, 6, 5, 6}) == 3;
        assert new Solution().pivotIndex(new int[]{1, 2, 3}) == -1;
        assert new Solution().pivotIndex(new int[]{2, 1, -1}) == 0;
    }

}
