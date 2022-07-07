package problem.p1131maximumofabsolutevalueexpression;

/**
 * 1131. Maximum of Absolute Value Expression
 *
 * https://leetcode.cn/problems/maximum-of-absolute-value-expression/
 *
 * Given two arrays of integers with equal lengths, return the maximum value of:
 *
 * |arr1[i] - arr1[j]| + |arr2[i] - arr2[j]| + |i - j|
 *
 * where the maximum is taken over all 0 <= i, j < arr1.length.
 */

public class Solution {

    public int maxAbsValExpr(int[] arr1, int[] arr2) {
        int amin = Integer.MAX_VALUE, bmin = Integer.MAX_VALUE,
            cmin = Integer.MAX_VALUE, dmin = Integer.MAX_VALUE;
        int amax = Integer.MIN_VALUE, bmax = Integer.MIN_VALUE,
            cmax = Integer.MIN_VALUE, dmax = Integer.MIN_VALUE;

        for(int i = 0; i < arr1.length; i++) {
            amin = Math.min(amin, arr1[i] + arr2[i] + i);
            amax = Math.max(amax, arr1[i] + arr2[i] + i);

            bmin = Math.min(bmin, arr1[i] + arr2[i] - i);
            bmax = Math.max(bmax, arr1[i] + arr2[i] - i);

            cmin = Math.min(cmin, arr1[i] - arr2[i] + i);
            cmax = Math.max(cmax, arr1[i] - arr2[i] + i);

            dmin = Math.min(dmin, arr1[i] - arr2[i] - i);
            dmax = Math.max(dmax, arr1[i] - arr2[i] - i);
        }

        return Math.max(
            Math.max(amax - amin, bmax - bmin),
            Math.max(cmax - cmin, dmax - dmin)
        );
    }

    public static void main(String[] args) {
        assert new Solution().maxAbsValExpr(new int[]{1,2,3,4}, new int[]{-1,4,5,6}) == 13;
        assert new Solution().maxAbsValExpr(new int[]{1,-2,-5,0,10}, new int[]{0,-2,-1,-7,-4}) == 20;
    }

}
