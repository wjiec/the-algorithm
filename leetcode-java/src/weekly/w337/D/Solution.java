package weekly.w337.D;

/**
 * 2598. Smallest Missing Non-negative Integer After Operations
 *
 * https://leetcode.cn/problems/smallest-missing-non-negative-integer-after-operations/
 *
 * You are given a 0-indexed integer array nums and an integer value.
 *
 * In one operation, you can add or subtract value from any element of nums.
 *
 * For example, if nums = [1,2,3] and value = 2, you can choose to subtract value from nums[0] to make nums = [-1,2,3].
 * The MEX (minimum excluded) of an array is the smallest missing non-negative integer in it.
 *
 * For example, the MEX of [-1,2,3] is 0 while the MEX of [1,0,3] is 2.
 * Return the maximum MEX of nums after applying the mentioned operation any number of times.
 */

public class Solution {

    public int findSmallestInteger(int[] nums, int value) {
        int[] mods = new int[value + 1];
        for (int v : nums) mods[((v % value) + value) % value]++;
        for (int i = 0; i <= nums.length; i++) {
            if (--mods[i % value] < 0) return i;
        }
        return -1; // unreached
    }

    public static void main(String[] args) {
        assert new Solution().findSmallestInteger(new int[]{1,-10,7,13,6,8}, 5) == 4;
        assert new Solution().findSmallestInteger(new int[]{1,-10,7,13,6,8}, 7) == 2;
    }

}
