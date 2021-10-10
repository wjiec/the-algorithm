package problem.p1460maketwoarraysequalbyreversingsubarrays;

import java.util.Arrays;

/**
 * 1460. Make Two Arrays Equal by Reversing Sub-arrays
 *
 * https://leetcode-cn.com/problems/make-two-arrays-equal-by-reversing-sub-arrays/
 *
 * Given two integer arrays of equal length target and arr.
 *
 * In one step, you can select any non-empty sub-array of arr and reverse it.
 * You are allowed to make any number of steps.
 *
 * Return True if you can make arr equal to target, or False otherwise.
 */

public class Solution {

    public boolean canBeEqual(int[] target, int[] arr) {
        if (target.length != arr.length) return false;
        Arrays.sort(target);
        Arrays.sort(arr);

        for (int i = 0; i < target.length; i++) {
            if (target[i] != arr[i]) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        assert new Solution().canBeEqual(new int[]{1,2,3,4}, new int[]{2,4,1,3});
        assert new Solution().canBeEqual(new int[]{7}, new int[]{7});
        assert new Solution().canBeEqual(new int[]{1,12}, new int[]{12,1});
        assert !new Solution().canBeEqual(new int[]{3,7,9}, new int[]{3,7,11});
        assert !new Solution().canBeEqual(new int[]{3,7,9}, new int[]{3,7,11});
        assert new Solution().canBeEqual(new int[]{1,1,1,1,1}, new int[]{1,1,1,1,1});
    }

}
