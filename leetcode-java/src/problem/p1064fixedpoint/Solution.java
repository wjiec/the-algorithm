package problem.p1064fixedpoint;

/**
 * 1064. Fixed Point
 *
 * https://leetcode-cn.com/problems/fixed-point/
 *
 * Given an array of distinct integers arr, where arr is sorted in ascending order,
 * return the smallest index i that satisfies arr[i] == i.
 *
 * If there is no such index, return -1.
 */

public class Solution {

    public int fixedPoint(int[] arr) {
        int l = 0, r = arr.length, n = -1;
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (arr[mid] == mid) n = mid;
            if (arr[mid] >= mid) {
                r = mid;
            } else l = mid + 1;
        }
        return n;
    }

    public static void main(String[] args) {
        assert new Solution().fixedPoint(new int[]{0,1,3,7,8,9}) == 0;
        assert new Solution().fixedPoint(new int[]{-10}) == -1;

        assert new Solution().fixedPoint(new int[]{-10,-5,0,3,7}) == 3;
        assert new Solution().fixedPoint(new int[]{0,2,5,8,17}) == 0;
        assert new Solution().fixedPoint(new int[]{-10,-5,3,4,7,9}) == -1;
    }

}
