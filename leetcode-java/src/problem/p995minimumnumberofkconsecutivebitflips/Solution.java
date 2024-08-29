package problem.p995minimumnumberofkconsecutivebitflips;

/**
 * 995. Minimum Number of K Consecutive Bit Flips
 *
 * https://leetcode.cn/problems/minimum-number-of-k-consecutive-bit-flips/
 *
 * You are given a binary array nums and an integer k.
 *
 * A k-bit flip is choosing a subarray of length k from nums and simultaneously changing
 * every 0 in the subarray to 1, and every 1 in the subarray to 0.
 *
 * Return the minimum number of k-bit flips required so that there is no 0 in the array.
 *
 * If it is not possible, return -1.
 *
 * A subarray is a contiguous part of an array.
 */

public class Solution {

    public int minKBitFlips(int[] nums, int k) {
        int ans = 0, n = nums.length;
        boolean[] turns = new boolean[n + k + 1];
        for (int i = 0, curr = 0; i < n; i++) {
            if (turns[i]) curr ^= 1;

            if ((curr ^ nums[i]) == 0) {
                if (i + k > n) return -1;
                turns[i + k] = true;
                curr ^= 1;
                ans++;
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().minKBitFlips(new int[]{0,1,0}, 1) == 2;
        assert new Solution().minKBitFlips(new int[]{1,1,0}, 2) == -1;
        assert new Solution().minKBitFlips(new int[]{0,0,0,1,0,1,1,0}, 3) == 3;
    }

}
