package offer2.p75a0H97ZC;

import java.util.Arrays;

/**
 * 剑指 Offer II 075. 数组相对排序
 *
 * https://leetcode-cn.com/problems/0H97ZC/
 *
 * 给定两个数组，arr1 和 arr2，
 *
 * arr2 中的元素各不相同
 * arr2 中的每个元素都出现在 arr1 中
 *
 * 对 arr1 中的元素进行排序，使 arr1 中项的相对顺序和 arr2 中的相对顺序相同。
 *
 * 未在 arr2 中出现过的元素需要按照升序放在 arr1 的末尾。
 */

public class Solution {

    public int[] relativeSortArray(int[] arr1, int[] arr2) {
        int[] map = new int[1001];
        for (var n : arr1) map[n]++;

        int b = 0;
        for (int n : arr2) {
            for (; map[n] != 0; map[n]--) {
                arr1[b++] = n;
            }
        }

        for (int i = 0; i < map.length; i++) {
            for (; map[i] != 0; map[i]--) {
                arr1[b++] = i;
            }
        }

        return arr1;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new Solution().relativeSortArray(
            new int[]{2, 3, 1, 3, 2, 4, 6, 7, 9, 2, 19}, new int[]{2, 1, 4, 3, 9, 6})));
    }

}
