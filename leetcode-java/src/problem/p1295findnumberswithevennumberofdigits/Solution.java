package problem.p1295findnumberswithevennumberofdigits;

/**
 * 1295. Find Numbers with Even Number of Digits
 *
 * https://leetcode-cn.com/problems/find-numbers-with-even-number-of-digits/
 *
 * Given an array nums of integers, return how many of them contain an even number of digits.
 */

public class Solution {

    public int findNumbers(int[] nums) {
        int ans = 0;
        for (var n : nums) if (bits(n) % 2 == 0) ans++;
        return ans;
    }

    public int bits(int v) {
        if (v < 10) return 1;
        if (v < 100) return 2;
        if (v < 1000) return 3;
        if (v < 10000) return 4;
        if (v < 100000) return 5;
        return 6;
    }

    public static void main(String[] args) {
        assert new Solution().findNumbers(new int[]{12,345,2,6,7896}) == 2;
        assert new Solution().findNumbers(new int[]{555,901,482,1771}) == 1;
    }

}
