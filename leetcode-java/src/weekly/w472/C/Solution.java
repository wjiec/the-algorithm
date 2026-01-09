package weekly.w472.C;

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

    public static void main(String[] args) {
        assert new Solution().lexGreaterPermutation("z", "a").equals("z");
        assert new Solution().lexGreaterPermutation("abc", "bba").equals("bca");
        assert new Solution().lexGreaterPermutation("leet", "code").equals("eelt");
        assert new Solution().lexGreaterPermutation("baba", "bbaa").equals("");
    }

}
