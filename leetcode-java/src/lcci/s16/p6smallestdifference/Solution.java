package lcci.s16.p6smallestdifference;

import java.util.Arrays;

/**
 * 面试题 16.06. 最小差
 *
 * https://leetcode.cn/problems/smallest-difference-lcci/
 *
 * 给定两个整数数组a和b，计算具有最小差绝对值的一对数值（每个数组中取一个值），并返回该对数值的差
 */

@SuppressWarnings("DuplicatedCode")
public class Solution {

    public int smallestDifference(int[] a, int[] b) {
        Arrays.sort(b);

        long ans = Long.MAX_VALUE;
        for (var v : a) {
            int l = 0, r = b.length;
            while (l < r) {
                int mid = l + (r - l) / 2;
                ans = Math.min(ans, Math.abs((long) v - (long) b[mid]));
                if (b[mid] < v) l = mid + 1;
                else r = mid;
            }
        }

        return (int) ans;
    }

    public static void main(String[] args) {
        assert new Solution().smallestDifference(new int[]{1, 3, 15, 11, 2}, new int[]{23, 127, 235, 19, 8}) == 3;
    }

}
