package weekly.w354.A;

/**
 * 2778. Sum of Squares of Special Elements
 *
 * https://leetcode.cn/contest/weekly-contest-354/problems/sum-of-squares-of-special-elements/
 *
 * You are given a 1-indexed integer array nums of length n.
 *
 * An element nums[i] of nums is called special if i divides n, i.e. n % i == 0.
 *
 * Return the sum of the squares of all special elements of nums.
 */

public class Solution {

    public int sumOfSquares(int[] nums) {
        int sum = 0, n = nums.length;
        for (int i = 0; i < n; i++) {
            if (n % (i + 1) == 0) sum += nums[i] * nums[i];
        }
        return sum;
    }

    public static void main(String[] args) {
    }

}
