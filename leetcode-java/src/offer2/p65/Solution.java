package offer2.p65;

import java.util.Arrays;

/**
 * 剑指 Offer II 065. 最短的单词编码
 *
 * https://leetcode.cn/problems/iSwD2y/
 *
 * 单词数组 words 的 有效编码 由任意助记字符串 s 和下标数组 indices 组成，且满足：
 *
 * words.length == indices.length
 * 助记字符串 s 以 '#' 字符结尾
 * 对于每个下标 indices[i] ，s 的一个从 indices[i] 开始、到下一个 '#' 字符结束（但不包括 '#'）的 子字符串 恰好与 words[i] 相等
 * 给定一个单词数组 words ，返回成功对 words 进行编码的最小助记字符串 s 的长度 。
 */

public class Solution {

    private static class BackTrie {
        private final BackTrie[] tries = new BackTrie[26];
        private BackTrie get(char c) {return tries[c - 'a']; }
        private BackTrie set(char c) {
            if (tries[c - 'a'] == null) tries[c - 'a'] = new BackTrie();
            return tries[c - 'a'];
        }
        public BackTrie get(char[] chars) {
            BackTrie curr = this;
            for (int i = chars.length - 1; i >= 0; i--) {
                if ((curr = curr.get(chars[i])) == null) break;
            }
            return curr;
        }
        public BackTrie set(char[] chars) {
            BackTrie curr = this;
            for (int i = chars.length - 1; i >= 0; i--) {
                curr = curr.set(chars[i]);
            }
            return curr;
        }
    }

    public int minimumLengthEncoding(String[] words) {
        Arrays.sort(words, (a, b) -> b.length() - a.length());

        int ans = 0;
        BackTrie root = new BackTrie();
        for (var word : words) {
            BackTrie curr = root.get(word.toCharArray());
            if (curr == null) {
                ans += word.length() + 1;
                root.set(word.toCharArray());
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().minimumLengthEncoding(new String[]{"time", "me", "bell"}) == 10;
        assert new Solution().minimumLengthEncoding(new String[]{"t"}) == 2;
    }

}
