package problem.p1955countnumberofspecialsubsequences;

/**
 * 1955. Count Number of Special Subsequences
 *
 * https://leetcode.cn/problems/count-number-of-special-subsequences/description/
 *
 * A sequence is special if it consists of a positive number of 0s, followed
 * by a positive number of 1s, then a positive number of 2s.
 *
 * For example, [0,1,2] and [0,0,1,1,1,2] are special.
 * In contrast, [2,1,0], [1], and [0,1,2,0] are not special.
 * Given an array nums (consisting of only integers 0, 1, and 2), return the number of different
 * subsequences that are special. Since the answer may be very large, return it modulo 109 + 7.
 *
 * A subsequence of an array is a sequence that can be derived from the array by deleting
 * some or no elements without changing the order of the remaining elements.
 *
 * Two subsequences are different if the set of indices chosen are different.
 */

public class Solution {

    public int countSpecialSubsequences(int[] nums) {
        final long MOD = 1_000_000_007;
        long[] type0 = new long[nums.length + 1];
        long[] type1 = new long[nums.length + 1];
        long[] type2 = new long[nums.length + 1];

        for (int i = 1; i <= nums.length; i++) {
            type0[i] = type0[i - 1];
            type1[i] = type1[i - 1];
            type2[i] = type2[i - 1];

            switch (nums[i - 1]) {
                case 0 -> type0[i] = (2 * type0[i - 1] + 1) % MOD;
                case 1 -> type1[i] = (2 * type1[i - 1] + type0[i - 1]) % MOD;
                case 2 -> type2[i] = (2 * type2[i - 1] + type1[i - 1]) % MOD;
            }
        }

        return (int) type2[nums.length];
    }

    public static void main(String[] args) {
        assert new Solution().countSpecialSubsequences(new int[]{0,1,2,2}) == 3;
        assert new Solution().countSpecialSubsequences(new int[]{2,2,0,0}) == 0;
        assert new Solution().countSpecialSubsequences(new int[]{0,1,2,0,1,2}) == 7;
    }

}
