package weekly.w317.A;

/**
 * 6220. Average Value of Even Numbers That Are Divisible by Three
 *
 * https://leetcode.cn/contest/weekly-contest-317/problems/average-value-of-even-numbers-that-are-divisible-by-three/
 *
 * Given an integer array nums of positive integers, return the average
 * value of all even integers that are divisible by 3.
 *
 * Note that the average of n elements is the sum of the n elements
 * divided by n and rounded down to the nearest integer.
 */

public class Solution {

    public int averageValue(int[] nums) {
        int total = 0, cnt = 0;
        for (var v : nums) {
            if (v % 3 == 0 && v % 2 == 0) {
                total += v; cnt++;
            }
        }
        return cnt == 0 ? 0 : total / cnt;
    }

    public static void main(String[] args) {
    }

}
