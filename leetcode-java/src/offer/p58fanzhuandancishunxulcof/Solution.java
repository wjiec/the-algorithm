package offer.p58fanzhuandancishunxulcof;

import java.util.Stack;

/**
 * 剑指 Offer 58 - I. 翻转单词顺序
 *
 * https://leetcode-cn.com/problems/fan-zhuan-dan-ci-shun-xu-lcof/
 *
 * 输入一个英文句子，翻转句子中单词的顺序，但单词内字符的顺序不变。为简单起见，标点符号和普通字母一样处理。
 *
 * 例如输入字符串"I am a student. "，则输出"student. a am I"。
 */

public class Solution {

    public String reverseWords(String s) {
        StringBuilder sb = new StringBuilder();
        for (int r = s.length() - 1; r >= 0; r--) {
            if (s.charAt(r) != ' ') {
                int l = r - 1;
                while (l >= 0 && s.charAt(l) != ' ') l--;
                if (sb.length() != 0) sb.append(' ');
                sb.append(s, l + 1, r + 1);
                r = l;
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        assert new Solution().reverseWords("the sky is blue").equals("blue is sky the");
        assert new Solution().reverseWords("  hello world!  ").equals("world! hello");
        assert new Solution().reverseWords("a good   example").equals("example good a");
    }

}
