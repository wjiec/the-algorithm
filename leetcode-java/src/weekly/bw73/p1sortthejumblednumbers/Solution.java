package weekly.bw73.p1sortthejumblednumbers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * 5217. Sort the Jumbled Numbers
 *
 * https://leetcode-cn.com/contest/biweekly-contest-73/problems/sort-the-jumbled-numbers/
 *
 * You are given a 0-indexed integer array mapping which represents the mapping rule of a shuffled decimal system.
 * mapping[i] = j means digit i should be mapped to digit j in this system.
 *
 * The mapped value of an integer is the new integer obtained by replacing each occurrence of digit i
 * in the integer with mapping[i] for all 0 <= i <= 9.
 *
 * You are also given another integer array nums. Return the array nums sorted in non-decreasing order
 * based on the mapped values of its elements.
 *
 * Notes:
 *
 * Elements with the same mapped values should appear in the same relative order as in the input.
 * The elements of nums should only be sorted based on their mapped values and not be replaced by them.
 */

public class Solution {

    public int[] sortJumbled(int[] mapping, int[] nums) {
        List<int[]> list = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            list.add(new int[]{i, nums[i], convert(mapping, nums[i])});
        }

        list.sort(Comparator.comparingInt((int[] v) -> v[2]).thenComparingInt(v -> v[0]));
        for (int i = 0; i < nums.length; i++) {
            nums[i] = list.get(i)[1];
        }

        return nums;
    }

    private int convert(int[] mapping, int n) {
        if (n == 0) return mapping[0];

        int v = 0;
        for (int base = 1; n != 0; n /= 10, base *= 10) {
            v += mapping[n % 10] * base;
        }
        return v;
    }

    public static void main(String[] args) {
        // [9,8,7,6,5,4,3,2,1,0]
        System.out.println(Arrays.toString(new Solution().sortJumbled(new int[]{9,8,7,6,5,4,3,2,1,0},
            new int[]{0,1,2,3,4,5,6,7,8,9})));

        System.out.println(Arrays.toString(new Solution().sortJumbled(new int[]{8, 9, 4, 0, 2, 1, 3, 5, 7, 6},
            new int[]{991, 338, 38})));

        System.out.println(Arrays.toString(new Solution().sortJumbled(new int[]{0,1,2,3,4,5,6,7,8,9},
            new int[]{789,456,123})));
    }

}
