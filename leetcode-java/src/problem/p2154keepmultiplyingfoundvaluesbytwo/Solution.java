package problem.p2154keepmultiplyingfoundvaluesbytwo;

import java.util.Arrays;

/**
 * 2154. Keep Multiplying Found Values by Two
 *
 * https://leetcode-cn.com/problems/keep-multiplying-found-values-by-two/
 *
 * You are given an array of integers nums. You are also given an integer original
 * which is the first number that needs to be searched for in nums.
 *
 * You then do the following steps:
 *
 * If original is found in nums, multiply it by two (i.e., set original = 2 * original).
 * Otherwise, stop the process.
 * Repeat this process with the new number as long as you keep finding the number.
 * Return the final value of original.
 */

public class Solution {

    public int findFinalValue(int[] nums, int original) {
        Arrays.sort(nums);
        while (binarySearch(nums, original)) {
            original *= 2;
        }
        return original;
    }

    private boolean binarySearch(int[] nums, int value) {
        int l = 0, r = nums.length;
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (nums[mid] == value) return true;
            else if (nums[mid] > value) r = mid;
            else l = mid + 1;
        }
        return false;
    }

    public static void main(String[] args) {
        assert new Solution().findFinalValue(new int[]{8,19,4,2,15,3}, 2) == 16;

        assert new Solution().findFinalValue(new int[]{5,3,6,1,12}, 3) == 24;
        assert new Solution().findFinalValue(new int[]{2,7,9}, 4) == 4;
    }

}
