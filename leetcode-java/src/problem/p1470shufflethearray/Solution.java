package problem.p1470shufflethearray;

import common.Checker;

/**
 * 1470. Shuffle the Array
 *
 * https://leetcode-cn.com/problems/shuffle-the-array/
 *
 * Given the array nums consisting of 2n elements in the form [x1,x2,...,xn,y1,y2,...,yn].
 *
 * Return the array in the form [x1,y1,x2,y2,...,xn,yn].
 */

public class Solution {

    public int[] shuffle(int[] nums, int n) {
        for (int i = 0, l = nums.length; i < l; i++) {
            for (int j = i; nums[i] > 0; ) {
                j = j < n ? 2 * j : 2 * (j - n) + 1;

                int t = nums[i];
                nums[i] = nums[j];
                nums[j] = -t;
            }
        }

        for (int i = 0; i < nums.length; i++) {
            nums[i] = -nums[i];
        }
        return nums;
    }

    public static void main(String[] args) {
        assert Checker.check(new Solution().shuffle(new int[]{2,5,1,3,4,7}, 3), new int[]{2,3,5,4,1,7});
        assert Checker.check(new Solution().shuffle(new int[]{1,2,3,4,4,3,2,1}, 4), new int[]{1,4,2,3,3,2,4,1});
        assert Checker.check(new Solution().shuffle(new int[]{1,1,2,2}, 2), new int[]{1,2,1,2});
    }

}
