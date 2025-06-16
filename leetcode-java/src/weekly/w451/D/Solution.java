package weekly.w451.D;

import ability.Benchmark;

import java.util.*;

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

    /** @noinspection unchecked*/
    public String lexicographicallySmallestString(String s) {
        // 尽量全部移除, 如果无法全部移除则保留尽可能短, 或者是保留字典序最小的
        //  - 如果当前答案的第 i 个字符为 c, 则如果想找到小于等于当前答案字典序的新答案需要这一位 j 为 <= c
        //      - 也就是需要将 [i, j) 都删掉

        char[] chars = s.toCharArray();
        List<Integer>[] g = new List[128];
        Arrays.setAll(g, i -> new ArrayList<>());
        for (int i = 0; i < chars.length; i++) g[chars[i]].add(i);

        for (int l = 0; l < s.length(); l++) {
            char curr = s.charAt(l);
            // 尝试找到能将当前位变得最小的方案
            for (char c = 'a'; c < curr; c++) {
                // 将当前字符替换成 r, 也就是删除 [l, r) 的区域
                for (var r : g[c]) {
                    if (r > l && removable(chars, l, r)) {
                        return lexicographicallySmallestString(s.substring(0, l) + s.substring(r));
                    }
                }
            }
            if (removable(chars, l, chars.length)) return s.substring(0, l);
        }

        return s;
    }

    // 检查 [l, r) 范围内是否都能消除
    private boolean removable(char[] chars, int l, int r) {
        if ((r - l) % 2 == 1) return false; // 必须都要偶数长度
        return removable(new String(chars, l, r - l));
    }

    private final Map<String, Boolean> memo = new HashMap<>();

    private boolean removable(String s) {
        if (s.length() == 0) return true;
        if (memo.containsKey(s)) return memo.get(s);

        int n = s.length();
        boolean ans = false;
        for (int i = 1; i < n; i++) {
            if (consecutive(s.charAt(i - 1), s.charAt(i))) {
                ans = removable(s.substring(0, i-1) + s.substring(i + 1));
                if (ans) break;
            }
        }

        memo.put(s, ans);
        return ans;
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
