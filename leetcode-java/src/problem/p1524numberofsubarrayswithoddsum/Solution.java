package problem.p1524numberofsubarrayswithoddsum;

/**
 * 1524. Number of Sub-arrays With Odd Sum
 *
 * https://leetcode.cn/problems/number-of-sub-arrays-with-odd-sum/
 *
 * Given an array of integers arr, return the number of subarrays with an odd sum.
 *
 * Since the answer can be very large, return it modulo 109 + 7.
 */

public class Solution {

    public int numOfSubarrays(int[] arr) {
        long ans = 0, MOD = 1_000_000_007;
        int sum = 0, odd = 0, even = 1; // 0
        for (int val : arr) {
            ans += (sum += val) % 2 == 0 ? odd : even;
            if (sum % 2 == 0) even++;
            else odd++;
        }
        return (int) (ans % MOD);
    }

    public static void main(String[] args) {
        assert new Solution().numOfSubarrays(new int[]{1,3,5}) == 4;
        assert new Solution().numOfSubarrays(new int[]{2,4,6}) == 0;
        assert new Solution().numOfSubarrays(new int[]{1,2,3,4,5,6,7}) == 16;
        assert new Solution().numOfSubarrays(new int[]{100,100,99,99}) == 4;
        assert new Solution().numOfSubarrays(new int[]{7}) == 1;
    }

}
