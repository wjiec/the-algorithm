package problem.p1502canmakearithmeticprogressionfromsequence;

import java.util.Arrays;

/**
 * 1502. Can Make Arithmetic Progression From Sequence
 *
 * https://leetcode-cn.com/problems/can-make-arithmetic-progression-from-sequence/
 *
 * A sequence of numbers is called an arithmetic progression if the difference between
 * any two consecutive elements is the same.
 *
 * Given an array of numbers arr, return true if the array can be rearranged to form an arithmetic progression.
 * Otherwise, return false.
 */

public class Solution {

    public boolean canMakeArithmeticProgression(int[] arr) {
        if (arr.length == 2) return true;

        Arrays.sort(arr);
        int base = arr[1] - arr[0];
        for (int i = 2; i < arr.length; i++) {
            if (arr[i] - arr[i - 1] != base) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        assert new Solution().canMakeArithmeticProgression(new int[]{3,5,1});
        assert !new Solution().canMakeArithmeticProgression(new int[]{1,2,4});
    }

}
