package weekly.w484.D;

import java.util.Arrays;

/**
 * Q4. Maximum Bitwise AND After Increment Operations
 *
 * https://leetcode.cn/contest/weekly-contest-484/problems/maximum-bitwise-and-after-increment-operations/
 *
 * You are given an integer array nums and two integers k and m.
 *
 * You may perform at most k operations. In one operation, you may choose any index i and increase nums[i] by 1.
 *
 * Return an integer denoting the maximum possible bitwise AND of any subset of size m
 * after performing up to k operations optimally.
 */

public class Solution {

    public int maximumAND(int[] nums, int k, int m) {
        int mx = 0, ans = 0;
        for (var v : nums) mx = Math.max(mx, v);
        // 从高到低考虑每一位是否能为 1, 同时考虑经过 k 次操作是否能完成
        int[] ops = new int[nums.length]; // 记录没个数需要操作的次数
        int w = 32 - Integer.numberOfLeadingZeros(mx + k / m); // 每个数都恰好增加 k / m
        for (int b = w - 1; b >= 0; b--) {
            int target = ans | (1 << b); // 连带着高位的 1 一起计算
            for (int i = 0; i < nums.length; i++) {
                // 我们首先要找到当前数 x 与 target 的最高不一致位的位置
                //  - target = 10101
                //  -      x = 11001
                //               ^ 找到这一位
                // 我们可以将 x 取反此时 x 中的 0 都会变成 1, 再将其与 target 做与运算即可找到最高为 0 的位置
                int x = target & (~nums[i]), mask = (1 << (32 - Integer.numberOfLeadingZeros(x))) - 1;
                ops[i] = (target & mask) - (nums[i] & mask);
            }

            Arrays.sort(ops); // 排序之后取最小的 m 个并计算是否能在 k 次修改之内完成
            long needs = 0;
            for (int i = 0; i < m; i++) needs += ops[i];
            if (needs <= k) ans = target; // 当前位可以填 1
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().maximumAND(new int[]{21,12}, 9, 2) == 21;
        assert new Solution().maximumAND(new int[]{11,18}, 14, 2) == 21;
        assert new Solution().maximumAND(new int[]{11}, 2, 1) == 13;
        assert new Solution().maximumAND(new int[]{8}, 10, 1) == 18;
        assert new Solution().maximumAND(new int[]{3,1,2}, 8, 2) == 6;
        assert new Solution().maximumAND(new int[]{1,2,8,4}, 7, 3) == 4;
        assert new Solution().maximumAND(new int[]{1,1}, 3, 2) == 2;
    }

}
