package weekly.w302.D;

import java.util.Arrays;

/**
 * 6122. Minimum Deletions to Make Array Divisible
 *
 * https://leetcode.cn/contest/weekly-contest-302/problems/minimum-deletions-to-make-array-divisible/
 *
 * You are given two positive integer arrays nums and numsDivide. You can delete any number of elements from nums.
 *
 * Return the minimum number of deletions such that the smallest element in nums divides all the
 * elements of numsDivide. If this is not possible, return -1.
 *
 * Note that an integer x divides y if y % x == 0.
 */

public class Solution {

    public int minOperations(int[] nums, int[] numsDivide) {
        int g = numsDivide[0];
        for (int div : numsDivide) g = gcd(g, div);

        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            if (g % nums[i] == 0) return i;
        }
        return -1;
    }

    private int gcd(int a, int b) {
        return a % b == 0 ? b : gcd(b, a % b);
    }

    public static void main(String[] args) {
    }

}
