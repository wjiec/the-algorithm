package problem.p376wigglesubsequence;

/**
 * 376. Wiggle Subsequence
 *
 * https://leetcode-cn.com/problems/wiggle-subsequence/
 *
 * A wiggle sequence is a sequence where the differences between successive numbers strictly alternate
 * between positive and negative. The first difference (if one exists) may be either positive or negative.
 * A sequence with one element and a sequence with two non-equal elements are trivially wiggle sequences.
 *
 * For example, [1, 7, 4, 9, 2, 5] is a wiggle sequence because the differences (6, -3, 5, -7, 3) alternate
 * between positive and negative.
 *
 * In contrast, [1, 4, 7, 2, 5] and [1, 7, 4, 5, 5] are not wiggle sequences.
 * The first is not because its first two differences are positive,
 * and the second is not because its last difference is zero.
 *
 * A subsequence is obtained by deleting some elements (possibly zero) from the original sequence,
 * leaving the remaining elements in their original order.
 *
 * Given an integer array nums, return the length of the longest wiggle subsequence of nums.
 */

public class Solution {

    // @TODO
    public int wiggleMaxLength(int[] nums) {
        if (nums.length < 2) return nums.length;

        int[] up = new int[nums.length], down = new int[nums.length];
        up[0] = down[0] = 1;

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[i - 1]) {
                up[i] = Math.max(up[i - 1], down[i - 1] + 1);
                down[i] = down[i - 1];
            } else if (nums[i] < nums[i - 1]) {
                up[i] = up[i - 1];
                down[i] = Math.max(up[i - 1] + 1, down[i - 1]);
            } else {
                up[i] = up[i - 1];
                down[i] = down[i - 1];
            }
        }
        return Math.max(up[nums.length - 1], down[nums.length - 1]);
    }

    public static void main(String[] args) {
        assert new Solution().wiggleMaxLength(new int[]{1,7,4,9,2,5}) == 6;
        assert new Solution().wiggleMaxLength(new int[]{1,17,5,10,13,15,10,5,16,8}) == 7;
        assert new Solution().wiggleMaxLength(new int[]{1,2,3,4,5,6,7,8,9}) == 2;
    }

}
