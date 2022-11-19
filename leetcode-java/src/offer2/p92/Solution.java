package offer2.p92;

/**
 * 剑指 Offer II 092. 翻转字符
 *
 * https://leetcode.cn/problems/cyJERH/
 *
 * 如果一个由 '0' 和 '1' 组成的字符串，是以一些 '0'（可能没有 '0'）后面跟着一些 '1'（也可能没有 '1'）
 * 的形式组成的，那么该字符串是 单调递增 的。
 *
 * 我们给出一个由字符 '0' 和 '1' 组成的字符串 s，我们可以将任何 '0' 翻转为 '1' 或者将 '1' 翻转为 '0'。
 *
 * 返回使 s 单调递增 的最小翻转次数。
 */

public class Solution {

    public int minFlipsMonoIncr(String s) {
        char[] chars = s.toCharArray();

        int[] dp = new int[chars.length + 1];
        for (int i = 1, ones = 0; i <= chars.length; i++) {
            if (chars[i - 1] == '0') {
                // 把前面的 1 全变成 0, 或者把当前的 0 变成 1
                dp[i] = Math.min(dp[i - 1] + 1, ones);
            } else {
                ones++;
                dp[i] = dp[i - 1];
            }
        }

        return dp[chars.length];
    }

    private static class StateCompress {
        public int minFlipsMonoIncr(String s) {
            int ans = 0, ones = 0;
            for (var c : s.toCharArray()) {
                if (c == '0') ans = Math.min(ans + 1, ones);
                else ones++;
            }
            return ans;
        }
    }

    public static void main(String[] args) {
        assert new Solution().minFlipsMonoIncr("00110") == 1;
        assert new Solution().minFlipsMonoIncr("010110") == 2;
        assert new Solution().minFlipsMonoIncr("00011000") == 2;

        assert new StateCompress().minFlipsMonoIncr("00110") == 1;
        assert new StateCompress().minFlipsMonoIncr("010110") == 2;
        assert new StateCompress().minFlipsMonoIncr("00011000") == 2;
    }

}
