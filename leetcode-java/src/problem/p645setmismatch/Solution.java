package problem.p645setmismatch;

import common.Checker;

/**
 * 645. Set Mismatch
 *
 * You have a set of integers s, which originally contains all the numbers from 1 to n.
 * Unfortunately, due to some error, one of the numbers in s got duplicated to another number in the set,
 * which results in repetition of one number and loss of another number.
 *
 * You are given an integer array nums representing the data status of this set after the error.
 *
 * Find the number that occurs twice and the number that is missing and return them in the form of an array.
 */

public class Solution {

    public int[] findErrorNums(int[] nums) {
        int missing = 0, duplicate = -1;
        for (var n : nums) {
            int i = Math.abs(n);
            if (nums[i - 1] < 0) {
                duplicate = i;
            } else {
                nums[i - 1] *= -1;
            }
        }

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) {
                missing = i + 1;
            }
        }
        return new int[]{duplicate, missing};
    }

    public static void main(String[] args) {
        assert Checker.check(new Solution().findErrorNums(new int[]{1, 2, 2, 4}), new int[]{2, 3});
        assert Checker.check(new Solution().findErrorNums(new int[]{1,2,2,4}), new int[]{2,3});
    }

}
