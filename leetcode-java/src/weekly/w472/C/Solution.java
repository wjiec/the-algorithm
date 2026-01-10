package weekly.w472.C;

import java.util.Arrays;

/**
 * Q3. Lexicographically Smallest Permutation Greater Than Target
 *
 * https://leetcode.cn/contest/weekly-contest-472/problems/lexicographically-smallest-permutation-greater-than-target/
 *
 * You are given two strings s and target, both having length n, consisting of lowercase English letters.
 *
 * Return the lexicographically smallest permutation of s that is strictly greater than target.
 * If no permutation of s is lexicographically strictly greater than target, return an empty string.
 *
 * A string a is lexicographically strictly greater than a string b (of the same length)
 * if in the first position where a and b differ, string a has a letter that appears later
 * in the alphabet than the corresponding letter in b.
 */

public class Solution {

    public String lexGreaterPermutation(String s, String target) {
        // 返回的是 s 的一个排列, 所以和 s 中字符的顺序是无关的
        int[] freq = new int[128];
        for (var c : s.toCharArray()) freq[c]++;

        // 我们需要找到大于 target 的最小字典序排列
        //  - 如果我们在 i 位置所选择的字母 c 大于 target[i], 那么后面的位置只需要按最小的排列方式即可
        //  - 如果我们在 i 位置所选择的字母 c 等于 target[i], 那么后面的位置需要满足大于 target[i+1:]
        //  - 如果我们在 i 位置所选择的字母 c 小于 target[i], 不符合答案的要求
        //
        // 也就是说我们需要在 ans[i] 填入大于等于 target[i] 的字母, 如果无法填入则无法找到一个排列
        //  - 填入大于 target[i] 的字母位置越靠后越好
        dfs(target.toCharArray(), 0, freq, new char[s.length()], false);
        return minAns;
    }

    private String minAns = "";

    private void dfs(char[] target, int i, int[] freq, char[] ans, boolean gt) {
        if (gt) {
            for (char c = 'a'; c <= 'z'; c++) {
                while (freq[c]-- > 0) ans[i++] = c;
            }

            String curr = new String(ans);
            if (minAns.isEmpty() || curr.compareTo(minAns) < 0) minAns = curr;
            return;
        }
        if (i >= target.length) return;

        for (var c = target[i]; c <= 'z'; c++) {
            if (freq[c] == 0) continue;

            ans[i] = c; freq[c]--;
            dfs(target, i + 1, freq, ans, c > target[i]);
            freq[c]++;
        }
    }

    private static class Optimization {
        public String lexGreaterPermutation(String s, String target) {
            // freq 中保存的是 s 字符串中剩余可用字符的频率
            int[] freq = new int[128];
            for (var c : s.toCharArray()) freq[c]++;

            // 从后往前考虑, 假设要使得第 i 位恰好大于 target[i], 那么 s[0:i] 就需要
            // 与 target[0:i] 一致, 否则无法实现恰好在 i 位置大于 target
            //
            // 我们从后往前枚举, 先把所有字母都从 s 里删掉, 然后再枚举一个个加回来
            for (var c : target.toCharArray()) freq[c]--;
            for (int i = target.length() - 1; i >= 0; i--) {
                freq[target.charAt(i)]++;
                // 检查 s 前面是否能与 target 保持一致, 也就是在 freq 中不存在负频率
                if (Arrays.stream(freq).anyMatch(a -> a < 0)) continue;

                // 现在我们可以使得 s 前面与 target 保持一致, 尝试增加当前位的字符
                for (char c = (char) (target.charAt(i) + 1); c <= 'z'; c++) {
                    // 我们需要 s 中存在这个字符
                    if (freq[c] == 0) continue;

                    freq[c]--;
                    // 然后开始构建字符串, 也就是 [0, i) 是 target, i 是 c, 之后的按照 freq 从小到大排序
                    StringBuilder sb = (new StringBuilder(target.substring(0, i))).append(c);
                    for (char x = 'a'; x <= 'z'; x++) sb.append(String.valueOf(x).repeat(freq[x]));
                    return sb.toString();
                }
            }
            return ""; // unreached
        }
    }

    public static void main(String[] args) {
        assert new Solution().lexGreaterPermutation("z", "a").equals("z");
        assert new Solution().lexGreaterPermutation("abc", "bba").equals("bca");
        assert new Solution().lexGreaterPermutation("leet", "code").equals("eelt");
        assert new Solution().lexGreaterPermutation("baba", "bbaa").equals("");
    }

}
