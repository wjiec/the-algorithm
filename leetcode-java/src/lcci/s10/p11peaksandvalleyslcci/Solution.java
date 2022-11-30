package lcci.s10.p11peaksandvalleyslcci;

import common.PrettyPrinter;

import java.util.Arrays;

/**
 * 面试题 10.11. 峰与谷
 *
 * https://leetcode.cn/problems/peaks-and-valleys-lcci/
 *
 * 在一个整数数组中，“峰”是大于或等于相邻整数的元素，相应地，“谷”是小于或等于相邻整数的元素。
 * 例如，在数组{5, 8, 4, 2, 3, 4, 6}中，{8, 6}是峰， {5, 2}是谷。
 *
 * 现在给定一个整数数组，将该数组按峰与谷的交替顺序排序。
 */

@SuppressWarnings("DuplicatedCode")
public class Solution {

    public void wiggleSort(int[] nums) {
        Arrays.sort(nums);
        if (nums.length <= 2) return;

        int n = nums.length, half = (n / 2) + (n % 2);
        int[] left = new int[half], right = new int[half];
        // 得到 sorted[n/2], sorted[n/2-1], ..., sorted[0] 的序列
        for (int i = 0, j = half - 1; j >= 0; i++, j--) left[i] = nums[j];
        // 得到 sorted[n], sorted[n-1], ..., sorted[n/2] 的序列
        for (int i = 0, j = n - 1; j >= half; i++, j--) right[i] = nums[j];
        // 逐个插入数据
        for (int i = 0, j = 0; i < half && j < n; i++, j += 2) nums[j] = left[i];
        for (int i = 0, j = 1; i < half && j < n; i++, j += 2) nums[j] = right[i];
    }

    public static void main(String[] args) {
        int[] s1 = new int[]{5, 3, 1, 2, 3};
        new Solution().wiggleSort(s1);
        PrettyPrinter.println(s1);
    }

}
