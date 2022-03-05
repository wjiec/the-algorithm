package problem.p179largestnumber;

import java.util.Arrays;

/**
 * 179. Largest Number
 *
 * https://leetcode-cn.com/problems/largest-number/
 *
 * Given a list of non-negative integers nums,
 * arrange them such that they form the largest number and return it.
 *
 * Since the result may be very large, so you need to return a string instead of an integer.
 */

public class Solution {

    public String largestNumber(int[] nums) {
        Integer[] ns = new Integer[nums.length];
        for (int i = 0; i < nums.length; i++) ns[i] = nums[i];

        Arrays.sort(ns, (a, b) -> {
            long x = 10, y = 10;
            while (x <= a) x *= 10;
            while (y <= b) y *= 10;
            return (int) (-y * a - b + x * b + a);
        });

        if (ns[0] == 0) return "0";
        StringBuilder sb = new StringBuilder();
        for (var n : ns) sb.append(n);
        return sb.toString();
    }

    public static void main(String[] args) {
        assert new Solution().largestNumber(new int[]{10,2}).equals("210");
        assert new Solution().largestNumber(new int[]{3,30,34,5,9}).equals("9534330");
    }

}
