package weekly.bw141.B;

import java.util.List;

/**
 * 3315. Construct the Minimum Bitwise Array II
 *
 * https://leetcode.cn/contest/biweekly-contest-141/problems/construct-the-minimum-bitwise-array-ii/
 *
 * You are given an array nums consisting of n prime integers.
 *
 * You need to construct an array ans of length n, such that, for each index i, the
 * bitwise OR of ans[i] and ans[i] + 1 is equal to nums[i], i.e. ans[i] OR (ans[i] + 1) == nums[i].
 *
 * Additionally, you must minimize each value of ans[i] in the resulting array.
 *
 * If it is not possible to find such a value for ans[i] that satisfies the condition, then set ans[i] = -1.
 */

/** @noinspection DuplicatedCode*/
public class Solution {

    // ans[i] OR (ans[i] + 1) == nums[i]
    public int[] minBitwiseArray(List<Integer> nums) {
        int[] ans = new int[nums.size()];
        for (int i = 0; i <  nums.size(); i++) {
            int v = nums.get(i);
            if (v % 2 != 0) {
                // 如果答案 ans0 的第一位为 0, 答案加 1 之后为 1
                // 那么剩下的所有位置都需要在 ans0 中补, 也就是 v - 1
                //
                // 如果答案 ans1 的第一位为 1, 答案加 1 之后为 10
                // 枚举所有可能剩下的位是什么
                ans[i] = Math.min(v - 1, find(1, v, 1));
            } else ans[i] = -1;
        }
        return ans;
    }

    private int find(int i, int target, int a) {
        int mask = 1 << i;
        if (mask > target) return a;

        if ((target & mask) == mask) {
            // 那这一位要么在 a 中, 要么靠 a + 1 来补
            int ans = find(i + 1, target, a | mask);
            if (((a + 1) & mask) == mask) ans = Math.min(ans, find(i + 1, target, a));
            return ans;
        }

        // 如果这一位没有值, 那么 a 和 a + 1 都不能有这一位
        if ((a & mask) == mask) return target;
        if (((a + 1) & mask) == mask) return target;

        return find(i + 1, target, a);
    }

    public static void main(String[] args) {
    }

}
