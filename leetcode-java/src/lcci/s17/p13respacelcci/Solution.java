package lcci.s17.p13respacelcci;

import ability.Ability;

/**
 * 面试题 17.13. 恢复空格
 *
 * https://leetcode.cn/problems/re-space-lcci/
 *
 * 哦，不！你不小心把一个长篇文章中的空格、标点都删掉了，并且大写也弄成了小写。
 * 像句子"I reset the computer. It still didn’t boot!"已经变成了"iresetthecomputeritstilldidntboot"。
 * 在处理标点符号和大小写之前，你得先把它断成词语。当然了，你有一本厚厚的词典dictionary，不过，有些词没在词典里。
 * 假设文章用sentence表示，设计一个算法，把文章断开，要求未识别的字符最少，返回未识别的字符数。
 *
 * 注意：本题相对原题稍作改动，只需返回未识别的字符数
 */

public class Solution {

    public int respace(String[] dictionary, String sentence) {
        Ability.AlphaTrie trie = new Ability.AlphaTrie();
        for (var word : dictionary) {
            Ability.AlphaTrie curr = trie;
            char[] chars = word.toCharArray();
            for (int i = chars.length - 1; i >= 0; i--) {
                curr = curr.set(chars[i]);
            }
            curr.asLeaf();
        }

        char[] chars = sentence.toCharArray();
        int[] dp = new int[chars.length + 1];

        for (int i = 1; i <= chars.length; i++) {
            dp[i] = dp[i - 1] + 1;

            Ability.AlphaTrie curr = trie;
            for (int j = i; j > 0; j--) {
                curr = curr.get(chars[j - 1]);
                if (curr == null) break;
                if (curr.isLeaf()) dp[i] = Math.min(dp[i], dp[j - 1]);
            }
        }

        return dp[chars.length];
    }

    public static void main(String[] args) {
        assert new Solution().respace(new String[]{
            "looked","just","like","her","brother"
        }, "jesslookedjustliketimherbrother") == 7;
    }

}
