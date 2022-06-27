package problem.p1053previouspermutationwithoneswap;

import common.Checker;

/**
 * 1053. Previous Permutation With One Swap
 *
 * https://leetcode.cn/problems/previous-permutation-with-one-swap/
 *
 * Given an array of positive integers arr (not necessarily distinct), return the lexicographically
 * largest permutation that is smaller than arr, that can be made with exactly one swap (A swap
 * exchanges the positions of two numbers arr[i] and arr[j]).
 *
 * If it cannot be done, then return the same array.
 */

public class Solution {

    public int[] prevPermOpt1(int[] arr) {
        int l = -1, n = arr.length;
        for (int i = n - 1; i > 0; i--) {
            if (arr[i - 1] > arr[i]) {
                l = i - 1;
                break;
            }
        }
        if (l == -1) return arr;

        int r = 0, v = 0;
        for (int i = l + 1; i < n; i++) {
            if (arr[i] < arr[l] && arr[i] > v) {
                r = i; v = arr[i];
            }
        }

        int temp = arr[l];
        arr[l] = arr[r];
        arr[r] = temp;

        return arr;
    }

    public static void main(String[] args) {
        assert Checker.check(new Solution().prevPermOpt1(new int[]{3,2,1}), new int[]{3,1,2});
        assert Checker.check(new Solution().prevPermOpt1(new int[]{1,1,5}), new int[]{1,1,5});
        assert Checker.check(new Solution().prevPermOpt1(new int[]{1,9,4,6,7}), new int[]{1,7,4,6,9});
        assert Checker.check(new Solution().prevPermOpt1(new int[]{3,1,1,3}), new int[]{1,3,1,3});
    }

}
