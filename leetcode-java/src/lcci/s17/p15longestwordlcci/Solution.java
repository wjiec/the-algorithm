package lcci.s17.p15longestwordlcci;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 面试题 17.15. 最长单词
 *
 * https://leetcode.cn/problems/longest-word-lcci/
 *
 * 给定一组单词words，编写一个程序，找出其中的最长单词，且该单词由这组单词中的其他单词组合而成。
 * 若有多个长度相同的结果，返回其中字典序最小的一项，若没有符合要求的单词则返回空字符串。
 */

public class Solution {


    public String longestWord(String[] words) {
        Set<String> set = new HashSet<>(Arrays.asList(words));
        Arrays.sort(words, (a, b) -> a.length() == b.length() ? a.compareTo(b) : a.length() - b.length());

        String ans = "";
        for (var word : words) {
            if (word.length() == ans.length()) continue;
            if (dfs(word.toCharArray(), 0, set)) ans = word;
        }
        return ans;
    }

    private boolean dfs(char[] chars, int l, Set<String> set) {
        if (l == chars.length) return true;

        for (int r = l + 1; r <= chars.length; r++) {
            if (set.contains(new String(chars, l, r - l))) {
                if (l == 0 && r == chars.length) return false;
                if (dfs(chars, r, set)) return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        assert new Solution().longestWord(new String[]{"cat","banana","dog","nana","walk","walker","dogwalker"}).equals("dogwalker");
    }

}
