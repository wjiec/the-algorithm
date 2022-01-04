package lcci.s10.p1sortedmergelcci;

import java.util.Arrays;

/**
 * 面试题 10.01. 合并排序的数组
 *
 * https://leetcode-cn.com/problems/sorted-merge-lcci/
 *
 * 给定两个排序后的数组 A 和 B，其中 A 的末端有足够的缓冲空间容纳 B。 编写一个方法，将 B 合并入 A 并排序。
 *
 * 初始化 A 和 B 的元素数量分别为 m 和 n。
 */

public class Solution {

    public void merge(int[] A, int m, int[] B, int n) {
        int a = m - 1, b = n - 1, i = m + n - 1;
        while (a >= 0 && b >= 0) {
            if (A[a] >= B[b]) A[i--] = A[a--];
            else A[i--] = B[b--];
        }
        while (b >= 0) A[i--] = B[b--];
    }

    public static void main(String[] args) {
        var list = new int[]{1,2,3,0,0,0};
        new Solution().merge(list, 3, new int[]{2,5,6}, 3);
        System.out.println(Arrays.toString(list));

        var list1 = new int[]{1,2,2,0,0,0};
        new Solution().merge(list1, 3, new int[]{3,5,6}, 3);
        System.out.println(Arrays.toString(list1));

        var list3 = new int[]{6,6,6,0,0,0};
        new Solution().merge(list3, 3, new int[]{3,5,5}, 3);
        System.out.println(Arrays.toString(list3));
    }

}
