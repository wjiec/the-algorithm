package problem.p1365howmanynumbersaresmallerthanthecurrentnumber;

import common.Checker;

/**
 * 1365. How Many Numbers Are Smaller Than the Current Number
 *
 * https://leetcode-cn.com/problems/how-many-numbers-are-smaller-than-the-current-number/
 *
 * Given the array nums, for each nums[i] find out how many numbers in the array are smaller than it.
 * That is, for each nums[i] you have to count the number of valid j's such that j != i and nums[j] < nums[i].
 *
 * Return the answer in an array.
 */

public class Solution {

    public int[] smallerNumbersThanCurrent(int[] nums) {
        int[] map = new int[101];
        for (var n : nums) map[n]++;

        for (int i = 0, prev = 0; i < map.length; i++) {
            int t = map[i];
            map[i] = prev + (i == 0 ? 0 : map[i - 1]);
            prev = t;
        }

        for (int i = 0; i < nums.length; i++) {
            nums[i] = map[nums[i]];
        }
        return nums;
    }

    public static void main(String[] args) {
        assert Checker.check(new Solution().smallerNumbersThanCurrent(new int[]{5,0,10,0,10,6}), new int[]{2,0,4,0,4,3});

        assert Checker.check(new Solution().smallerNumbersThanCurrent(new int[]{8,1,2,2,3}), new int[]{4,0,1,1,3});
        assert Checker.check(new Solution().smallerNumbersThanCurrent(new int[]{6,5,4,8}), new int[]{2,1,0,3});
        assert Checker.check(new Solution().smallerNumbersThanCurrent(new int[]{7,7,7,7}), new int[]{0,0,0,0});
    }

}
