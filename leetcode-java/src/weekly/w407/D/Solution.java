package weekly.w407.D;

/**
 * 3229. Minimum Operations to Make Array Equal to Target
 *
 * https://leetcode.cn/contest/weekly-contest-407/problems/minimum-operations-to-make-array-equal-to-target/
 *
 * You are given two positive integer arrays nums and target, of the same length.
 *
 * In a single operation, you can select any subarray of nums and increment or decrement
 * each element within that subarray by 1.
 *
 * Return the minimum number of operations required to make nums equal to the array target.
 */

public class Solution {

    public long minimumOperations(int[] nums, int[] target) {
        long ans = 0, prev = 0;
        // 叠叠乐, 先一排一排叠起来, 有断开的单独处理
        for (int i = 0; i < nums.length; i++) {
            long curr = target[i] - nums[i];
            if (sameSign(curr, prev)) {
                if (prev < 0) ans += Math.max(0, prev - curr);
                else ans += Math.max(0, curr - prev);
            } else ans += Math.abs(curr);
            prev = curr;
        }
        return ans;
    }

    private boolean sameSign(long a, long b) { return (a > 0 && b > 0) || (a < 0 && b < 0); }

    public static void main(String[] args) {
        assert new Solution().minimumOperations(
            new int[]{9, 2, 6, 10, 4, 8, 3, 4, 2, 3},
            new int[]{9, 5, 5,  1, 7, 9, 8, 7, 6, 5}
        ) == 20;
    }

}
