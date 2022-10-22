package problem.p2442countnumberofdistinctintegersafterreverseoperations;

import java.util.HashSet;
import java.util.Set;

/**
 * 2442. Count Number of Distinct Integers After Reverse Operations
 *
 * https://leetcode.cn/problems/count-number-of-distinct-integers-after-reverse-operations/
 *
 * You are given an array nums consisting of positive integers.
 *
 * You have to take each integer in the array, reverse its digits, and add it to the end of the array.
 * You should apply this operation to the original integers in nums.
 *
 * Return the number of distinct integers in the final array.
 */

public class Solution {

    public int countDistinctIntegers(int[] nums) {
        Set<Integer> uniq = new HashSet<>();
        for (var v : nums) { uniq.add(v); uniq.add(reverse(v)); }
        return uniq.size();
    }

    private int reverse(int v) {
        int len = 0;
        int[] digits = new int[32];
        for (; v != 0; v /= 10) digits[len++] = v % 10;

        int ans = 0;
        for (int i = 0; i < len; i++) ans = ans * 10 + digits[i];
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().countDistinctIntegers(new int[]{1,13,10,12,31}) == 6;
        assert new Solution().countDistinctIntegers(new int[]{2,2,2}) == 1;
    }

}
