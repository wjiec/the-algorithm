package weekly.bw177.C;

import common.PrettyPrinter;

import java.util.TreeSet;

/**
 * Q3. Minimum Operations to Make Array Parity Alternating
 *
 * https://leetcode.cn/contest/biweekly-contest-177/problems/minimum-operations-to-make-array-parity-alternating/
 *
 * You are given an integer array nums.
 *
 * An array is called parity alternating if for every index i where 0 <= i < n - 1, nums[i] and nums[i + 1]
 * have different parity (one is even and the other is odd).
 *
 * In one operation, you may choose any index i and either increase nums[i] by 1 or decrease nums[i] by 1.
 *
 * Return an integer array answer of length 2 where:
 *
 * answer[0] is the minimum number of operations required to make the array parity alternating.
 * answer[1] is the minimum possible value of max(nums) - min(nums) taken over all arrays that are parity
 * alternating and can be obtained by performing exactly answer[0] operations.
 *
 * An array of length 1 is considered parity alternating.
 */

public class Solution {

    public int[] makeParityAlternating(int[] nums) {
        // 首先检查首位是奇数和偶数的情况下对应的操作次数
        int firstOdd = 0, firstEven = 0;
        for (int i = 0; i < nums.length; i++) {
            // 如果首位是奇数, 需要和下标的奇偶性不同
            firstOdd += ((nums[i] & 1) == (i & 1)) ? 1 : 0;
            // 如果首位是偶数, 需要和下标的奇偶性相同
            firstEven += ((nums[i] & 1) != (i & 1)) ? 1 : 0;
        }

        int ans = Integer.MAX_VALUE;
        if ((firstOdd < firstEven && (nums[0] & 1) == 1) || (firstEven < firstOdd && (nums[0] & 1) == 0)) {
            ans = Math.min(ans, makeParityAlternating(nums, nums[0]));
        } else {
            ans = Math.min(ans, makeParityAlternating(nums, nums[0] + 1));
            ans = Math.min(ans, makeParityAlternating(nums, nums[0] - 1));
            if (firstOdd == firstEven) ans = Math.min(ans, makeParityAlternating(nums, nums[0]));
        }
        return new int[]{Math.min(firstOdd, firstEven), ans};
    }

    private int makeParityAlternating(int[] nums, int first) {
        // 所有要修改的值的位置已经确定, 我们先找到不变的位置的 [mx, mn] 以及所有需要变的数
        int mx = first, mn = first;
        TreeSet<Integer> seen = new TreeSet<>();
        for (int i = 1, b = first & 1; i < nums.length; i++, b ^= 1) {
            if ((nums[i] & 1) != b) {
                mx = Math.max(mx, nums[i]);
                mn = Math.min(mn, nums[i]);
            } else seen.add(nums[i]);
        }

        // 现在我们可以修改 m 中的所有数一次, 所以是 lower + 1, higher - 1
        if (seen.isEmpty()) return mx - mn;
        if (seen.size() == 1) return Math.min(
            Math.max(mx, seen.first() + 1) - Math.min(mn, seen.first() + 1),
            Math.max(mx, seen.first() - 1) - Math.min(mn, seen.first() - 1)
        );
        return Math.max(mx, seen.last() - 1) - Math.min(mn, seen.first() + 1);
    }

    public static void main(String[] args) {
        PrettyPrinter.println(new Solution().makeParityAlternating(new int[]{9, -8, -2, 9})); // 2, 16
        PrettyPrinter.println(new Solution().makeParityAlternating(new int[]{7, 7})); // 1, 1
        // 5 -2 5/7 -6/-8
        PrettyPrinter.println(new Solution().makeParityAlternating(new int[]{5, -2, 6, -7})); // 2, 11
        // -4 -3/-5 -2/-4 -5 -4
        PrettyPrinter.println(new Solution().makeParityAlternating(new int[]{-4, -4, -3, -5, -4})); // 2, 1
        // 9 0/-2 -1 4 -1/-3
        // -2 -1 -1 4 9
        PrettyPrinter.println(new Solution().makeParityAlternating(new int[]{9,-1,-1,4,-2})); // 2, 10

        PrettyPrinter.println(new Solution().makeParityAlternating(new int[]{-2,-3,1,4})); // 2, 6
        PrettyPrinter.println(new Solution().makeParityAlternating(new int[]{0, 2, -2})); // 1, 3
        PrettyPrinter.println(new Solution().makeParityAlternating(new int[]{7})); // 0, 0
    }

}
