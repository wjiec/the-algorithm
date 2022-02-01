package problem.p45jumpgameii;

/**
 * 45. Jump Game II
 *
 * https://leetcode-cn.com/problems/jump-game-ii/
 *
 * Given an array of non-negative integers nums, you are initially positioned at the first index of the array.
 *
 * Each element in the array represents your maximum jump length at that position.
 *
 * Your goal is to reach the last index in the minimum number of jumps.
 *
 * You can assume that you can always reach the last index.
 */

public class Solution {

    public int jump(int[] nums) {
        int curr = nums.length - 1, ans = 0;
        while (curr > 0) {
            for (int i = 0; i < curr; i++) {
                if (i + nums[i] >= curr) {
                    curr = i;
                    ans++;
                    break;
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().jump(new int[]{2,3,1}) == 1;
        assert new Solution().jump(new int[]{1,2,3}) == 2;
        assert new Solution().jump(new int[]{2,1}) == 1;

        assert new Solution().jump(new int[]{2,3,1,1,4}) == 2;
        assert new Solution().jump(new int[]{2,3,0,1,4}) == 2;
    }

}
