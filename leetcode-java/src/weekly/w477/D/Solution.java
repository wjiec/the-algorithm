package weekly.w477.D;

import java.util.Arrays;

/**
 * Q4. Number of Effective Subsequences
 *
 * https://leetcode.cn/contest/weekly-contest-477/problems/number-of-effective-subsequences/
 *
 * You are given an integer array nums.
 *
 * The strength of the array is defined as the bitwise OR of all its elements.
 *
 * A subsequence is considered effective if removing that subsequence strictly
 * decreases the strength of the remaining elements.
 *
 * Return the number of effective subsequences in nums. Since the answer may be large, return it modulo 109 + 7.
 *
 * The bitwise OR of an empty array is 0.
 */

public class Solution {

    public int countEffective(int[] nums) {
        int max = 0;
        for (var v : nums) max = Math.max(max, v);

        int bits = 32 - Integer.numberOfLeadingZeros(max);
        // 完整数组的强度是最大的, 我们需要让剩余数组的强度减少
        //  - 移除所有在 i 位上置位的数字
        //  - 或者时移除所有在 i, j, ... 位上置位的数字 (每一个位必须完整移除)
        //
        // 由于一个数字可能有多个位被置位, 所以他们之间会有关联
        //  - 对于包含某一个位的所有数字进行 & 运算即可知道这一个位与哪些位之间存在关联
        int[] bitset = new int[bits], ands = new int[bits];
        Arrays.fill(ands, (1 << bits) - 1);
        for (var v : nums) {
            for (int i = 0; i < bits; i++) {
                if ((v & (1 << i)) != 0) {
                    bitset[i]++; ands[i] &= v;
                }
            }
        }

        return 3;
    }

    // 当前在选择第 i 位, 剩余可选的位是 mask
    private int dfs(int[] bitset, int[] ands, int i, int mask, int[] remain) {
        if (mask == 0) return 1;
        if (bitset[i] == 0) return dfs(bitset, ands, i + 1, mask, remain);

        int ans = 0;
        for (int j = 0; j < bitset.length; j++) {
            // 去掉这一位之后, 可能会导致后续的位也被一起删掉
        }
        return ans;
    }


    public static void main(String[] args) {
        assert new Solution().countEffective(new int[]{1,2,3}) == 3;
        assert new Solution().countEffective(new int[]{7,4,6}) == 4;
        assert new Solution().countEffective(new int[]{8,8}) == 1;
    }

}
