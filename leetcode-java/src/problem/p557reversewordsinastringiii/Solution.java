package problem.p557reversewordsinastringiii;

/**
 * 557. Reverse Words in a String III
 *
 * https://leetcode-cn.com/problems/reverse-words-in-a-string-iii/
 *
 * Given a string s, reverse the order of characters in each word
 * within a sentence while still preserving whitespace and initial word order.
 */

public class Solution {

    public String reverseWords(String s) {
        int sz = s.length(), last = -1;
        StringBuilder sb = new StringBuilder(s.length());
        for (int i = 0; i <= sz; i++) {
            if (i == sz || s.charAt(i) == ' ') {
                for (int j = i - 1; j > last; j--) {
                    sb.append(s.charAt(j));
                }

                last = i;
                if (i != sz) {
                    sb.append(' ');
                }
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        assert new Solution().reverseWords("Let's take LeetCode contest").equals("s'teL ekat edoCteeL tsetnoc");
        assert new Solution().reverseWords("God Ding").equals("doG gniD");
    }

}
