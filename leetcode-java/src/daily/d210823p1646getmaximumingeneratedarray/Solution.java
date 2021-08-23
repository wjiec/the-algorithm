package daily.d210823p1646getmaximumingeneratedarray;

/**
 * 1646. Get Maximum in Generated Array
 *
 * https://leetcode-cn.com/problems/get-maximum-in-generated-array/
 *
 * You are given an integer n. An array nums of length n + 1 is generated in the following way:
 *
 * nums[0] = 0
 * nums[1] = 1
 * nums[2 * i] = nums[i] when 2 <= 2 * i <= n
 * nums[2 * i + 1] = nums[i] + nums[i + 1] when 2 <= 2 * i + 1 <= n
 *
 * Return the maximum integer in the array nums​​​.
 */

public class Solution {

    public int getMaximumGenerated(int n) {
        if (n <= 1) return n;
        int[] nums = new int[n + 1];
        nums[0] = 0; nums[1] = 1;

        int ans = 0;
        for (int i = 2; i <= n; i++) {
            if (i % 2 == 0) nums[i] = nums[i / 2];
            else nums[i] = nums[i / 2] + nums[i / 2 + 1];

            ans = Math.max(ans, nums[i]);
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().getMaximumGenerated(7) == 3;
        assert new Solution().getMaximumGenerated(2) == 1;
        assert new Solution().getMaximumGenerated(3) == 2;
    }

}
