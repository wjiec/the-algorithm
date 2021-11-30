package problem.p1991findthemiddleindexinarray;

/**
 * 1991. Find the Middle Index in Array
 *
 * https://leetcode-cn.com/problems/find-the-middle-index-in-array/
 *
 * Given a 0-indexed integer array nums, find the leftmost middleIndex
 * (i.e., the smallest amongst all the possible ones).
 *
 * A middleIndex is an index where nums[0] + nums[1] + ... + nums[middleIndex-1] ==
 * nums[middleIndex+1] + nums[middleIndex+2] + ... + nums[nums.length-1].
 *
 * If middleIndex == 0, the left side sum is considered to be 0. Similarly,
 * if middleIndex == nums.length - 1, the right side sum is considered to be 0.
 *
 * Return the leftmost middleIndex that satisfies the condition, or -1 if there is no such index.
 */

public class Solution {

    public int findMiddleIndex(int[] nums) {
        int left = 0, right = 0;
        for (var n : nums) right += n;
        for (int i = 0; i < nums.length; i++) {
            if (left == right - nums[i]) return i;

            left += nums[i];
            right -= nums[i];
        }
        return -1;
    }

    public static void main(String[] args) {
        assert new Solution().findMiddleIndex(new int[]{2,3,-1,8,4}) == 3;
        assert new Solution().findMiddleIndex(new int[]{1,-1,4}) == 2;
        assert new Solution().findMiddleIndex(new int[]{2,5}) == -1;
        assert new Solution().findMiddleIndex(new int[]{1}) == 0;
    }

}
