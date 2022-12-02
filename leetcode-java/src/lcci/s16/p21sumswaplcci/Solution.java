package lcci.s16.p21sumswaplcci;

import common.Checker;

import java.util.Arrays;

/**
 * 面试题 16.21. 交换和
 *
 * https://leetcode.cn/problems/sum-swap-lcci/
 *
 * 给定两个整数数组，请交换一对数值（每个数组中取一个数值），使得两个数组所有元素的和相等。
 *
 * 返回一个数组，第一个元素是第一个数组中要交换的元素，第二个元素是第二个数组中要交换的元素。
 *
 * 若有多个答案，返回任意一个均可。若无满足条件的数值，返回空数组。
 */

@SuppressWarnings("DuplicatedCode")
public class Solution {

    public int[] findSwapValues(int[] array1, int[] array2) {
        int diff = 0;
        for (var v : array1) diff += v;
        for (var v : array2) diff -= v;

        Arrays.sort(array2);
        for (var v : array1) {
            if ((2 * v - diff) % 2 != 0) continue;
            int swap = search(array2, (2 * v - diff) / 2);
            if (swap != -1) return new int[]{v, array2[swap]};
        }

        return new int[]{};
    }

    private int search(int[] array, int target) {
        int l = 0, r = array.length;
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (array[mid] == target) return mid;
            if (array[mid] > target) r = mid;
            else l = mid + 1;
        }
        return  l < array.length && array[l] == target ? l : -1;
    }

    public static void main(String[] args) {
        assert Checker.check(new Solution().findSwapValues(new int[]{4, 1, 2, 1, 1, 2}, new int[]{3, 6, 3, 3}), new int[]{4,6});
        assert Checker.check(new Solution().findSwapValues(new int[]{1, 2, 3}, new int[]{4, 5, 6}), new int[]{});
    }

}
