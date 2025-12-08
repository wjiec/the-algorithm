package weekly.w470.D;

import java.util.Arrays;

/**
 * Q4. Count No-Zero Pairs That Sum to N
 *
 * https://leetcode.cn/contest/weekly-contest-470/problems/count-no-zero-pairs-that-sum-to-n/
 *
 * A no-zero integer is a positive integer that does not contain the digit 0 in its decimal representation.
 *
 * Given an integer n, count the number of pairs (a, b) where:
 *  - a and b are no-zero integers.
 *  - a + b = n
 *
 * Return an integer denoting the number of such pairs.
 */

public class Solution {

    public long countNoZeroPairs(long n) {
        char[] chars = String.valueOf(n).toCharArray();
        int[] digits = new int[chars.length];
        for (int i = 0; i < chars.length; i++) digits[i] = chars[i] - '0';
        // 存在以下三种情况:
        //  - 借一位给更低的位   -1
        //  - 没有发生任何借位    0
        //  - 从更高位借了一位  +10
        //
        // 数字的前导 0 不影响方案的选择
        return dfs(digits, digits.length - 1, false, false);
    }

    private final long[] memo = new long[1 << (Integer.numberOfTrailingZeros(16) + 2)];
    { Arrays.fill(memo, -1); }

    // borrowed 表示当前位被低位借了一个 1, zeroFirst 表示当前的 a 或者 b 是否存在一个前导 0
    private long dfs(int[] digits, int i, boolean borrowed, boolean zeroFirst) {
        if (i < 0) return borrowed ? 0 : 1; // 已经是最高位了, 不能再借位了
        int key = (i << 2) | (borrowed ? 2 : 0) | (zeroFirst ? 1 : 0);
        if (memo[key] != -1) return memo[key];

        // 当前位实际可用数字需要考虑借位给更低的位, 当前位可以是 -1 或者 0
        int curr = digits[i] - (borrowed ? 1 : 0);

        // 第一种情况: 如果当前已经是带前导 0 的话, 我们无法再分出方案, 只能维持前导 0
        if (zeroFirst) {
            // 我们已经有一个数已经带前导 0 了, 如果当前位为 0, 那么就是不合法的
            if (i > 0 && curr == 0) return 0;
            // 如果当前位小于 0 了, 则我们必须向更高的位借位
            return dfs(digits, i - 1, curr < 0, true);
        }

        long ans = 0;
        // 第二种情况: 我们想让其中的一个数变为带前导 0 的情况
        if (i < digits.length - 1) { // 最低位不能为 0
            // 当前位如果不等于 0, 我们使得其中一个数带上前导 0, 并且 a 或者 b 选择其中一个成为前导 0
            if (curr != 0) ans = dfs(digits, i - 1, curr < 0, true) * 2;
            // 否则最高位都被借走的情况下, 我们只有当前这一种选择
            else if (i == 0) ans = 1;
        }

        // 第三种情况: 不产生任何借位
        ans += dfs(digits, i - 1, false, false) * splitWays(curr);
        // 第三种情况: 产生一个借位
        ans += dfs(digits, i - 1, true, false) * splitWays(curr + 10);

        return memo[key] = ans;
    }

    private int splitWays(int target) {
        if (target < 2) return 0;
        return Math.min(target - 1, 19 - target);
    }

    public static void main(String[] args) {
        assert new Solution().countNoZeroPairs(12) == 9;

        assert new Solution().countNoZeroPairs(2) == 1;
        assert new Solution().countNoZeroPairs(3) == 2;
        assert new Solution().countNoZeroPairs(11) == 8;
    }

}
