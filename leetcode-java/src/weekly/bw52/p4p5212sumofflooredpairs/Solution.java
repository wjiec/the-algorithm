package weekly.bw52.p4p5212sumofflooredpairs;

import java.util.Arrays;

/**
 * 5212. Sum of Floored Pairs
 *
 * https://leetcode-cn.com/contest/biweekly-contest-52/problems/sum-of-floored-pairs/
 *
 * Given an integer array nums, return the sum of floor(nums[i] / nums[j]) for all pairs of indices
 * 0 <= i, j < nums.length in the array. Since the answer may be too large, return it modulo 109 + 7.
 *
 * The floor() function returns the integer part of the division.
 */

public class Solution {

    public int sumOfFlooredPairs(int[] nums) {
        int rs = 0, sz = nums.length, mod = 1000000007, len = 0;
        if (sz == 1) {
            return 1;
        }

        Arrays.sort(nums);
        int[] ns = new int[sz], cs = new int[sz]; ns[0] = nums[0];
        for (int i = 0; i < sz; i++) {
            if (ns[len] == nums[i]) {
                cs[len]++;
            } else {
                ns[++len] = nums[i];
                cs[len] = 1;
            }
        }

        len++;
        for (int i = 0; i < len; i++) {
            rs = (rs + cs[i] * cs[i]) % mod;
        }

        for (int i = 1; i < len; i++) {
            for (int j = 0; j < i; j++) {
                rs = (rs + (ns[i] / ns[j] * cs[i] * cs[j])) % mod;
            }
        }
        return rs;
    }

    public static void main(String[] args) {
        assert new Solution().sumOfFlooredPairs(new int[]{2,5,9}) == 10;
        assert new Solution().sumOfFlooredPairs(new int[]{7,7,7,7,7,7,7}) == 49;
    }

}
