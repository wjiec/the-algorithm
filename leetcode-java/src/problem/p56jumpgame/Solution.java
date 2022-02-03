package problem.p56jumpgame;

/**
 * 55. Jump Game
 *
 * https://leetcode-cn.com/problems/jump-game/
 *
 * You are given an integer array nums. You are initially positioned at the array's first index,
 * and each element in the array represents your maximum jump length at that position.
 *
 * Return true if you can reach the last index, or false otherwise.
 */

public class Solution {

    public boolean canJump(int[] nums) {
        int reached = 0, l = nums.length;
        for (int i = 0; i < l && i <= reached; i++) {
            reached = Math.max(reached, i + nums[i]);
            if (reached >= l - 1) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        assert new Solution().canJump(new int[]{2,3,1,1,4});
        assert !new Solution().canJump(new int[]{3,2,1,0,4});
    }

}
