package problem.p1356sortintegersbythenumberof1bits;

import common.Checker;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 1356. Sort Integers by The Number of 1 Bits
 *
 * https://leetcode-cn.com/problems/sort-integers-by-the-number-of-1-bits/
 *
 * Given an integer array arr. You have to sort the integers in the array in ascending
 * order by the number of 1's in their binary representation and in case of two or more integers
 * have the same number of 1's you have to sort them in ascending order.
 *
 * Return the sorted array.
 */

public class Solution {

    public int[] sortByBits(int[] arr) {
        int[][] sorted = new int[arr.length][2];
        for (int i = 0; i < arr.length; i++) {
            sorted[i][0] = arr[i];
            sorted[i][1] = bit1(arr[i]);
        }

        Arrays.sort(sorted, (a, b) -> {
            if (a[1] == b[1]) return a[0] - b[0];
            return a[1] - b[1];
        });

        for (int i = 0; i < arr.length; i++) {
            arr[i] = sorted[i][0];
        }
        return arr;
    }

    private int bit1(int n) {
        int ans = 0;
        for (; n != 0; n >>= 1) {
            if ((n & 1) == 1) ans++;
        }
        return ans;
    }

    public static void main(String[] args) {
        assert Checker.check(new Solution().sortByBits(new int[]{0,1,2,3,4,5,6,7,8}), new int[]{0,1,2,4,8,3,5,6,7});
        assert Checker.check(new Solution().sortByBits(new int[]{1024,512,256,128,64,32,16,8,4,2,1}),
            new int[]{1,2,4,8,16,32,64,128,256,512,1024});
        assert Checker.check(new Solution().sortByBits(new int[]{10000,10000}), new int[]{10000,10000});
        assert Checker.check(new Solution().sortByBits(new int[]{2,3,5,7,11,13,17,19}), new int[]{2,3,5,17,7,11,13,19});
        assert Checker.check(new Solution().sortByBits(new int[]{10,100,1000,10000}), new int[]{10,100,10000,1000});
    }

}
