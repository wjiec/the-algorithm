package problem.p1191kconcatenationmaximumsum;

/**
 * 1191. K-Concatenation Maximum Sum
 *
 * https://leetcode.cn/problems/k-concatenation-maximum-sum/
 *
 * Given an integer array arr and an integer k, modify the array by repeating it k times.
 *
 * For example, if arr = [1, 2] and k = 3 then the modified array will be [1, 2, 1, 2, 1, 2].
 *
 * Return the maximum sub-array sum in the modified array.
 * Note that the length of the sub-array can be 0 and its sum in that case is 0.
 *
 * As the answer can be very large, return the answer modulo 109 + 7.
 */

public class Solution {

    public int kConcatenationMaxSum(int[] arr, int k) {
        final int MOD = 1_000_000_007;
        int ans = 0, n = arr.length, sum = 0, c = Math.min(k, 2);
        for (int i = 0, curr = 0; i < c * n; i++) {
            curr = Math.max(curr + arr[i % n], arr[i % n]);
            ans = Math.max(ans, curr);
            if (i < n) sum += arr[i];
        }
        if (sum > 0 && k > 2) {
            long ss = (long) sum * (k - 2);
            ans = Math.max(ans, (int) ((ss + ans) % MOD));
        }
        return ans % MOD;
    }

    public static void main(String[] args) {
        assert new Solution().kConcatenationMaxSum(new int[]{-5,-2,0,0,3,9,-2,-5,4}, 5) == 20;
        assert new Solution().kConcatenationMaxSum(new int[]{1, 2}, 1) == 3;

        assert new Solution().kConcatenationMaxSum(new int[]{1, 2}, 3) == 9;
        assert new Solution().kConcatenationMaxSum(new int[]{1,-2,1}, 5) == 2;
        assert new Solution().kConcatenationMaxSum(new int[]{-1,-2}, 7) == 0;
    }

}
