package weekly.bw154.C;

/**
 * 3514. Number of Unique XOR Triplets II
 *
 * https://leetcode.cn/contest/biweekly-contest-154/problems/number-of-unique-xor-triplets-ii/
 *
 * You are given an integer array nums.
 *
 * A XOR triplet is defined as the XOR of three elements nums[i] XOR nums[j] XOR nums[k] where i <= j <= k.
 *
 * Return the number of unique XOR triplet values from all possible triplets (i, j, k).
 */

public class Solution {

    // 0 < nums.length <= 1500
    // 0 < nums[i] <= 1500
    public int uniqueXorTriplets(int[] nums) {
        int n = nums.length;
        if (n < 3) return n;
        // i <= j <= k 也就是最少有 nums.length 个
        //  - 还需要检查是否能出现不在 nums 中的数字
        // 直接枚举值域

        int mx = Integer.highestOneBit(1500) << 1;
        // dp[i][j] 表示是否能通过 i + 1 个数字组成异或值为 j
        boolean[][] dp = new boolean[3][mx];

        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                dp[1][nums[i] ^ nums[j]] = true;
            }
        }

        for (var v : nums) {
            for (int i = 0; i < mx; i++) {
                dp[2][i ^ v] = dp[2][i ^ v] || dp[1][i];
            }
        }

        int ans = 0;
        for (var v : dp[2]) ans += v ? 1 : 0;
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().uniqueXorTriplets(new int[]{1, 3}) == 2;
        assert new Solution().uniqueXorTriplets(new int[]{6,7,8,9}) == 4;
    }

}
