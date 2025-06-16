package weekly.w451.D;

import ability.Benchmark;

/**
 * Q4. Lexicographically Smallest String After Adjacent Removals
 *
 * https://leetcode.cn/contest/weekly-contest-451/problems/lexicographically-smallest-string-after-adjacent-removals
 *
 * You are given a string s consisting of lowercase English letters.
 *
 * You can perform the following operation any number of times (including zero):
 *
 * Remove any pair of adjacent characters in the string that are consecutive in the alphabet,
 * in either order (e.g., 'a' and 'b', or 'b' and 'a').
 *
 * Shift the remaining characters to the left to fill the gap.
 *
 * Return the lexicographically smallest string that can be obtained after performing the operations optimally.
 *
 * Note: Consider the alphabet as circular, thus 'a' and 'z' are consecutive.©leetcode
 */

public class Solution {

    public String lexicographicallySmallestString(String s) {
        return dfs(s.toCharArray(), 0, new String[s.length()]);
    }

    // 在 chars 中从 [i, n) 的最小字典序
    private String dfs(char[] chars, int i, String[] memo) {
        if (i == chars.length) return "";
        if (memo[i] != null) return memo[i];

        // 保留当前字符的话
        String ans = chars[i] + dfs(chars, i + 1, memo);
        // 否则不保留当前字符, 需要找一段可以全消除的包括 i 的子字符串
        for (int j = i + 1; j < chars.length; j++) {
            if (removable(chars, i, j)) {
                String curr = dfs(chars, j + 1, memo);
                if (curr.compareTo(ans) < 0) ans = curr;
            }
        }
        return memo[i] = ans;
    }

    private final Boolean[] memo = new Boolean[1 << 16];

    // 考虑 chars 在 [l, r] 范围内是否可以完全消除, 等价于求是否一个合法的括号序列
    private boolean removable(char[] chars, int l, int r) {
        int key = (l << 8) | r;
        if (memo[key] != null) return memo[key];

        // 空字符串
        if (r < l) return memo[key] = true;
        // 前后组合的形式 (()) 形式
        if (consecutive(chars[l], chars[r]) && removable(chars, l + 1, r - 1)) return memo[key] = true;

        // 分成 () () 这种形式
        for (int i = l + 1; i < r; i++) {
            if (removable(chars, l, i) && removable(chars, i + 1, r)) return memo[key] = true;
        }

        return memo[key] = false;
    }

    private boolean consecutive(char a, char b) {
        return Math.abs(a - b) == 1 || (a == 'a' && b == 'z') || (a == 'z' && b == 'a');
    }

    public static void main(String[] args) {
        Benchmark.benchmark("", () -> {
            assert new Solution().lexicographicallySmallestString("cccbdcbeacaa").equals("ccacaa");
            assert new Solution().lexicographicallySmallestString("aabefdcbfafeabbaadfbbbfcacabdadcbfddcaccfeddffbeabbbfefde").equals("aabbfaabadfbbbfcaabfdaccddffbeabbbf");
        });

        assert new Solution().lexicographicallySmallestString("abc").equals("a");
        assert new Solution().lexicographicallySmallestString("bcda").equals("");
        assert new Solution().lexicographicallySmallestString("zdce").equals("zdce");
    }

}
