package offer2.p96;

/**
 * 剑指 Offer II 096. 字符串交织
 *
 * https://leetcode.cn/problems/IY6buf/
 *
 * 给定三个字符串 s1、s2、s3，请判断 s3 能不能由 s1 和 s2 交织（交错） 组成。
 *
 * 两个字符串 s 和 t 交织 的定义与过程如下，其中每个字符串都会被分割成若干 非空 子字符串：
 *
 * s = s1 + s2 + ... + sn
 * t = t1 + t2 + ... + tm
 * |n - m| <= 1
 * 交织 是 s1 + t1 + s2 + t2 + s3 + t3 + ... 或者 t1 + s1 + t2 + s2 + t3 + s3 + ...
 * 提示：a + b 意味着字符串 a 和 b 连接。
 */

public class Solution {

    public boolean isInterleave(String s1, String s2, String s3) {
        int m = s1.length(), n = s2.length(), l = s3.length();
        if (m + n != l) return false;

        char[] a = s1.toCharArray(), b = s2.toCharArray(), c = s3.toCharArray();
        // dp1[i][j] 表示是否能由 s1[0,i] + s2[0,j] 组成 s3[0,i+j]
        boolean[][] dp = new boolean[m + 1][n + 1]; dp[0][0] = true;
        for (int ai = 0; ai <= m; ai++) {
            for (int bi = 0; bi <= n; bi++) {
                int ci = ai + bi - 1;
                if (ai > 0) dp[ai][bi] = dp[ai][bi] || (dp[ai - 1][bi] && a[ai - 1] == c[ci]);
                if (bi > 0) dp[ai][bi] = dp[ai][bi] || (dp[ai][bi - 1] && b[bi - 1] == c[ci]);
            }
        }

        return dp[m][n];
    }

    public static void main(String[] args) {
        assert !new Solution().isInterleave(
            "aaaaaaaaaaaaaaaaaaaaaaaaaaa",
            "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa",
            "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
        );
        assert !new Solution().isInterleave("", "", "a");
        assert !new Solution().isInterleave("a", "b", "a");

        assert new Solution().isInterleave("aabcc", "dbbca", "aadbbcbcac");
        assert !new Solution().isInterleave("aabcc", "dbbca", "aadbbbaccc");
        assert new Solution().isInterleave("", "", "");
    }

}
