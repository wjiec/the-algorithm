package problem.p1752checkifarrayissortedandrotated;

/**
 * 1752. Check if Array Is Sorted and Rotated
 *
 * https://leetcode-cn.com/problems/check-if-array-is-sorted-and-rotated/
 *
 * Given an array nums, return true if the array was originally sorted in non-decreasing order,
 * then rotated some number of positions (including zero). Otherwise, return false.
 *
 * There may be duplicates in the original array.
 *
 * Note: An array A rotated by x positions results in an array B of the same length such
 * that A[i] == B[(i+x) % A.length], where % is the modulo operation.
 */

public class Solution {

    public boolean check(int[] nums) {
        int l = 1, r = nums.length;
        while (l < r && nums[l] >= nums[l - 1]) l++;
        if (l == r) return true;
        l++;
        while (l < r && nums[l] >= nums[l - 1]) l++;
        return l == r && nums[r - 1] <= nums[0];
    }

    public static void main(String[] args) {
        assert new Solution().check(new int[]{5,1,2,3,4});

        assert new Solution().check(new int[]{3,4,5,1,2});
        assert !new Solution().check(new int[]{2,1,3,4});
        assert new Solution().check(new int[]{1,2,3});
        assert new Solution().check(new int[]{1,1,1});
        assert new Solution().check(new int[]{2,1});
    }

}
