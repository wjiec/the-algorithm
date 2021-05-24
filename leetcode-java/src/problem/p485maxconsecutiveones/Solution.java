package problem.p485maxconsecutiveones;

/**
 * 485. Max Consecutive Ones
 *
 * https://leetcode-cn.com/problems/max-consecutive-ones/
 *
 * Given a binary array nums, return the maximum number of consecutive 1's in the array.
 */

public class Solution {

    public int findMaxConsecutiveOnes(int[] nums) {
        int max = 0, curr = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 1) {
                curr++;
            } else {
                max = Math.max(max, curr);
                curr = 0;
            }
        }
        return Math.max(max, curr);
    }

    public static void main(String[] args) {
        assert new Solution().findMaxConsecutiveOnes(new int[]{1,1,0,1,1,1}) == 3;
        assert new Solution().findMaxConsecutiveOnes(new int[]{1,0,1,1,0,1}) == 2;
    }

}
