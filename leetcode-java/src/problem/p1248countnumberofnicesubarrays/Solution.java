package problem.p1248countnumberofnicesubarrays;

/**
 * 1248. Count Number of Nice Subarrays
 *
 * https://leetcode.cn/problems/count-number-of-nice-subarrays/
 *
 * Given an array of integers nums and an integer k. A continuous subarray is called nice if there are k odd numbers on it.
 *
 * Return the number of nice sub-arrays.
 */

public class Solution {

    public int numberOfSubarrays(int[] nums, int k) {
        int n = nums.length, sum = 0, ans = 0;

        int[] map = new int[n + 1]; map[0] = 1;
        for (var v : nums) map[sum += v % 2]++;

        for (int i = k; i <= n; i++) ans += map[i] * map[i - k];
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().numberOfSubarrays(new int[]{1,1,2,1,1}, 3) == 2;
        assert new Solution().numberOfSubarrays(new int[]{2,4,6}, 1) == 0;
        assert new Solution().numberOfSubarrays(new int[]{2,2,2,1,2,2,1,2,2,2}, 2) == 16;
    }

}
