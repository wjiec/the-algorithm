package problem.p2000reverseprefixofword;

/**
 * 2000. Reverse Prefix of Word
 *
 * https://leetcode-cn.com/problems/reverse-prefix-of-word/
 *
 * Given a 0-indexed string word and a character ch, reverse the segment of word that
 * starts at index 0 and ends at the index of the first occurrence of ch (inclusive).
 *
 * If the character ch does not exist in word, do nothing.
 *
 * For example, if word = "abcdefd" and ch = "d", then you should reverse the segment
 * that starts at 0 and ends at 3 (inclusive). The resulting string will be "dcbaefd".
 *
 * Return the resulting string.
 */

public class Solution {

    public String reversePrefix(String word, char ch) {
        int pos = 0, l = word.length();
        while (pos < l && word.charAt(pos) != ch) pos++;
        if (pos == l) return word;

        StringBuilder sb = new StringBuilder();
        for (int i = pos; i >= 0; i--) sb.append(word.charAt(i));
        return sb.append(word, pos + 1, l).toString();
    }

    public static void main(String[] args) {
        assert new Solution().reversePrefix("abcdefd", 'd').equals("dcbaefd");
        assert new Solution().reversePrefix("xyxzxe", 'z').equals("zxyxxe");
        assert new Solution().reversePrefix("abcd", 'z').equals("abcd");
    }

}
