package weekly.w327.A;

/**
 * 6283. Maximum Count of Positive Integer and Negative Integer
 *
 * https://leetcode.cn/contest/weekly-contest-327/problems/maximum-count-of-positive-integer-and-negative-integer/
 *
 * Given an array nums sorted in non-decreasing order, return the maximum
 * between the number of positive integers and the number of negative integers.
 *
 * In other words, if the number of positive integers in nums is pos and the
 * number of negative integers is neg, then return the maximum of pos and neg.
 *
 * Note that 0 is neither positive nor negative.
 */

public class Solution {

    public int maximumCount(int[] nums) {
        int pos = 0, neg = 0;
        for (var v : nums) {
            if (v > 0) pos++;
            if (v < 0) neg++;
        }
        return Math.max(pos, neg);
    }

    public static void main(String[] args) {
    }

}
