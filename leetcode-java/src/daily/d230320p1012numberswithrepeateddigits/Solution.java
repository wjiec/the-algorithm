package daily.d230320p1012numberswithrepeateddigits;

import java.util.Arrays;

/**
 * 1012. Numbers With Repeated Digits
 *
 * https://leetcode.cn/problems/numbers-with-repeated-digits/
 *
 * Given an integer n, return the number of positive integers
 * in the range [1, n] that have at least one repeated digit.
 */

@SuppressWarnings("DuplicatedCode")
public class Solution {

    public int numDupDigitsAtMostN(int n) {
        char[] chars = String.valueOf(n).toCharArray();
        // 初始情况下, 应该从第 1 个数位开始选择, 默认所有数字都是可选的
        // 且第一位为受限状态, 同时当前不是一个有效的数字
        return n - dfs(chars, 0, 0, true, false);
    }

    private final int[][] memo = new int[10][1 << 10];
    { for (var row : memo) Arrays.fill(row, -1); }

    // digits 表示所有边界数位
    // i 表示当前正在选择哪一个数位
    // mask 表示当前已选择的数字位图表示
    // limited 表示当前位是否有限制, 如果为 true 表示最大只能选择 digits[i]
    // valid 表示当前是否是一个有效的数字, 如果为 false 则当前可以继续跳过, 或者选择从 1 开始(无前导 0)
    private int dfs(char[] digits, int i, int mask, boolean limited, boolean valid) {
        // 如果所有数位都选择完成了, 说明这是一个符合条件的数字
        // 需要注意, 如果全部数位都跳过了, 那这里就不是一个有效的数字
        if (i == digits.length) return valid ? 1 : 0;
        // 如果当前位没有受到限制, 则尝试从记忆数组中获得答案
        // 当前位受到限制的情况只有 1 种, 不会出现重复计算的问题
        if (!limited && memo[i][mask] >= 0) return memo[i][mask];

        int ans = 0;
        // 如果前一位已经跳过的话, 这一位也可以考虑跳过并得到结果
        // 如果当前位也跳过的话, 意味着最终的结果高位就少了 1 位, 所以后续的位应该是无限制的
        if (!valid) ans += dfs(digits, i + 1, mask, false, false);

        // 如果当前是一个有效的数字的话, 那么当前位就可以选择从 0 开始
        int lower = valid ? 0 : 1;
        // 如果前一位是受限情况的话, 当前位可选择的数字也要受到限制
        int upper = limited ? (digits[i] - '0') : 9;

        // 枚举所有可选择的位
        for (int j = lower; j <= upper; j++) {
            if (((mask >> j) & 1) == 0) {
                ans += dfs(digits, i + 1, mask | (1 << j), limited && j == upper, true);
            }
        }

        return memo[i][mask] = ans;
    }

    public static void main(String[] args) {
        assert new Solution().numDupDigitsAtMostN(20) == 1;
        assert new Solution().numDupDigitsAtMostN(100) == 10;
        assert new Solution().numDupDigitsAtMostN(1000) == 262;
    }

}
