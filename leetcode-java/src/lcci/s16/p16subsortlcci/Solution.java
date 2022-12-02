package lcci.s16.p16subsortlcci;

import common.Checker;

import java.util.Arrays;

/**
 * 面试题 16.16. 部分排序
 *
 * https://leetcode.cn/problems/sub-sort-lcci/
 *
 * 给定一个整数数组，编写一个函数，找出索引m和n，只要将索引区间[m,n]的元素排好序，整个数组就是有序的。
 * 注意：n-m尽量最小，也就是说，找出符合条件的最短序列。
 *
 * 函数返回值为[m,n]，若不存在这样的m和n（例如整个数组是有序的），请返回[-1,-1]。
 */

public class Solution {

    public int[] subSort(int[] array) {
        int[] sorted = new int[array.length];
        System.arraycopy(array, 0, sorted, 0, array.length);

        Arrays.sort(sorted);
        int m = 0, n = sorted.length - 1;
        while (m < n && sorted[m] == array[m]) m++;
        while (n > m && sorted[n] == array[n]) n--;

        if (m >= n) return new int[]{-1, -1};
        return new int[]{m, n};
    }

    public static void main(String[] args) {
        assert Checker.check(new Solution().subSort(new int[]{1,2,4,7,10,11,7,12,6,7,16,18,19}), new int[]{3, 9});
        assert Checker.check(new Solution().subSort(new int[]{1,2,3}), new int[]{-1, -1});
    }

}
