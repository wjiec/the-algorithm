package weekly.w376.C;

import java.util.Arrays;

/**
 * 2967. Minimum Cost to Make Array Equalindromic
 *
 * https://leetcode.cn/contest/weekly-contest-376/problems/minimum-cost-to-make-array-equalindromic/
 *
 * You are given a 0-indexed integer array nums having length n.
 *
 * You are allowed to perform a special move any number of times (including zero) on nums.
 * In one special move you perform the following steps in order:
 *
 * Choose an index i in the range [0, n - 1], and a positive integer x.
 * Add |nums[i] - x| to the total cost.
 * Change the value of nums[i] to x.
 * A palindromic number is a positive integer that remains the same when its digits are reversed.
 * For example, 121, 2552 and 65756 are palindromic numbers whereas 24, 46, 235 are not palindromic numbers.
 *
 * An array is considered equalindromic if all the elements in the array are equal
 * to an integer y, where y is a palindromic number less than 109.
 *
 * Return an integer denoting the minimum possible total cost to make nums
 * equalindromic by performing any number of special moves.
 */

public class Solution {

    private static final int[] palindromic = new int[109999];

    static {
        int idx = 0;
        for (int base = 1; base <= 10000; base *= 10) {
            // 奇数长度的回文数
            for (int i = base; i < base * 10; i++) {
                int x = i;
                for (int r = i / 10; r != 0; r /= 10) {
                    x = x * 10 + r % 10;
                }
                palindromic[idx++] = x;
            }

            if (base <= 1000) {
                // 偶数长度的回文数
                for (int i = base; i < base * 10; i++) {
                    int x = i;
                    for (int r = i; r != 0; r /= 10) {
                        x = x * 10 + r % 10;
                    }
                    palindromic[idx++] = x;
                }
            }
        }
        palindromic[idx++] = 1_000_000_001; // 哨兵，防止下面代码中的 i 下标越界
    }

    public long minimumCost(int[] nums) {
        Arrays.sort(nums);

        int i = Arrays.binarySearch(palindromic, nums[nums.length / 2]);
        if (i < 0) i = -i - 1;

        return Math.min(
            cost(nums, i != 0 ? palindromic[i - 1] : 0),
            Math.min(
                cost(nums, palindromic[i]),
                cost(nums, i + 1 < palindromic.length ?palindromic[i + 1] : 0)
            )
        );
    }

    private long cost(int[] nums, int x) {
        long ans = 0;
        for (var v : nums) ans += Math.abs(v - x);
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().minimumCost(new int[]{1,2,3,4,5}) == 6;
        assert new Solution().minimumCost(new int[]{10,12,13,14,15}) == 11;
        assert new Solution().minimumCost(new int[]{22,33,22,33,22}) == 22;
    }

}
