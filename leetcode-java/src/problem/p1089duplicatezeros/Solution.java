package problem.p1089duplicatezeros;

import java.util.Arrays;

/**
 * 1089. Duplicate Zeros
 *
 * https://leetcode-cn.com/problems/duplicate-zeros/
 *
 * Given a fixed length array arr of integers, duplicate each occurrence of zero,
 * shifting the remaining elements to the right.
 *
 * Note that elements beyond the length of the original array are not written.
 *
 * Do the above modifications to the input array in place, do not return anything from your function.
 */

public class Solution {

    public void duplicateZeros(int[] arr) {
        int l = arr.length, c = 0, i = 0;
        while (c < l) c += arr[i++] == 0 ? 2 : 1;
        if (i == l) return;

        int j = arr.length - 1;
        if (arr[--i] == 0 && c > l) {
            arr[j--] = 0;
            i--;
        }

        for (; i >= 0; i--) {
            if (arr[i] == 0) {
                arr[j--] = 0;
                arr[j--] = 0;
            } else {
                arr[j--] = arr[i];
            }
        }
    }

    public static void main(String[] args) {
        int[] v2 = new int[]{1,5,2,0,6,8,0,6,0};
        new Solution().duplicateZeros(v2);
        System.out.println(Arrays.toString(v2));

        int[] v1 = new int[]{8,4,5,0,0,0,0,7};
        new Solution().duplicateZeros(v1);
        System.out.println(Arrays.toString(v1));

        int[] v0 = new int[]{0,0,0,0,0,0,0};
        new Solution().duplicateZeros(v0);
        System.out.println(Arrays.toString(v0));

        int[] p0 = new int[]{1,0,2,3,0,4,5,0};
        new Solution().duplicateZeros(p0);
        System.out.println(Arrays.toString(p0));

        int[] p1 = new int[]{1,2,3};
        new Solution().duplicateZeros(p1);
        System.out.println(Arrays.toString(p1));
    }

}
