package weekly.bw160.D;

/**
 * Q4. Minimum Stability Factor of Array
 *
 * https://leetcode.cn/contest/biweekly-contest-160/problems/minimum-stability-factor-of-array/
 *
 * You are given an integer array nums and an integer maxC.
 *
 * A subarray is called stable if the highest common factor (HCF) of all its elements is greater than or equal to 2.
 *
 * The stability factor of an array is defined as the length of its longest stable subarray.
 *
 * You may modify at most maxC elements of the array to any integer.
 *
 * Return the minimum possible stability factor of the array after at most maxC modifications.
 * If no stable subarray remains, return 0.
 *
 * Note:
 *
 * The highest common factor (HCF) of an array is the largest integer that evenly divides all the array elements.
 * A subarray of length 1 is stable if its only element is greater than or equal to 2, since HCF([x]) = x.
 */

public class Solution {

    public int minStable(int[] nums, int maxC) {
        return 1;
    }

    public static void main(String[] args) {
    }

}
