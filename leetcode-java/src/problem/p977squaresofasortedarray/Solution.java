package problem.p977squaresofasortedarray;

import common.Checker;

/**
 * 977. Squares of a Sorted Array
 *
 * https://leetcode-cn.com/problems/squares-of-a-sorted-array/
 *
 * Given an integer array nums sorted in non-decreasing order,
 * return an array of the squares of each number sorted in non-decreasing order.
 */

public class Solution {

    public int[] sortedSquares(int[] nums) {
        int[] ans = new int[nums.length];
        int n = nums.length, l = -1, r = n, i = 0;
        for (int k = 0; k < n; k++) {
            if (nums[k] < 0) l = k;
            else if (nums[k] == 0) i++;
            else { r = k; break; }
        }

        while (l >= 0 && r < n) {
            if (-nums[l] < nums[r]) {
                ans[i++] = nums[l] * nums[l];
                l--;
            } else {
                ans[i++] = nums[r] * nums[r];
                r++;
            }
        }

        for (; l >= 0; l--) ans[i++] = nums[l] * nums[l];
        for (; r < n; r++) ans[i++] = nums[r] * nums[r];

        return ans;
    }

    public static void main(String[] args) {
        assert Checker.check(new Solution().sortedSquares(new int[]{-4,-1,0,0,0,3,10}), new int[]{0,0,0,1,9,16,100});
        assert Checker.check(new Solution().sortedSquares(new int[]{-4,-1,0,3,10}), new int[]{0,1,9,16,100});
        assert Checker.check(new Solution().sortedSquares(new int[]{-7,-3,2,3,11}), new int[]{4,9,9,49,121});
        assert Checker.check(new Solution().sortedSquares(new int[]{1,2,3,4,5}), new int[]{1,4,9,16,25});
        assert Checker.check(new Solution().sortedSquares(new int[]{0,1,2,3,4,5}), new int[]{0,1,4,9,16,25});
        assert Checker.check(new Solution().sortedSquares(new int[]{-5,-4,-3,-2,-1}), new int[]{1,4,9,16,25});
        assert Checker.check(new Solution().sortedSquares(new int[]{-5,-4,-3,-2,-1,0}), new int[]{0,1,4,9,16,25});
    }

}
