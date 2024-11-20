package weekly.w424.A;

/**
 * 3354. Make Array Elements Equal to Zero
 *
 * https://leetcode.cn/contest/weekly-contest-424/problems/make-array-elements-equal-to-zero/
 *
 * You are given an integer array nums.
 *
 * Start by selecting a starting position curr such that nums[curr] == 0, and choose a
 * movement direction of either left or right.
 *
 * After that, you repeat the following process:
 *
 * If curr is out of the range [0, n - 1], this process ends.
 *
 * If nums[curr] == 0, move in the current direction by incrementing curr if you are moving
 * right, or decrementing curr if you are moving left.
 *
 * Else if nums[curr] > 0:
 *  Decrement nums[curr] by 1.
 *  Reverse your movement direction (left becomes right and vice versa).
 *  Take a step in your new direction.
 *
 * A selection of the initial position curr and movement direction is considered
 * valid if every element in nums becomes 0 by the end of the process.
 *
 * Return the number of possible valid selections.
 */

public class Solution {

    public int countValidSelections(int[] nums) {
        int ans = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                if (validSelection(nums, i , 1)) ans++;
                if (validSelection(nums, i , -1)) ans++;
            }
        }
        return ans;
    }

    private boolean validSelection(int[] nums, int i, int dx) {
        int[] curr = new int[nums.length];
        System.arraycopy(nums, 0, curr, 0, nums.length);

        for (; i >= 0 && i < curr.length; i += dx) {
            if (curr[i] > 0) {
                curr[i]--;
                dx *= -1;
            }
        }

        for (var v : curr) if (v != 0) return false;
        return true;
    }

    public static void main(String[] args) {
    }

}
