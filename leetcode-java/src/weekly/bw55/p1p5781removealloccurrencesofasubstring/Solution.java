package weekly.bw55.p1p5781removealloccurrencesofasubstring;

/**
 * 5781. Remove All Occurrences of a Substring
 *
 * https://leetcode-cn.com/contest/biweekly-contest-55/problems/remove-all-occurrences-of-a-substring/
 *
 * Given two strings s and part, perform the following operation on s
 * until all occurrences of the substring part are removed:
 *
 * Find the leftmost occurrence of the substring part and remove it from s.
 * Return s after removing all occurrences of part.
 *
 * A substring is a contiguous sequence of characters in a string.
 */

public class Solution {

    public String removeOccurrences(String s, String part) {
        int pos;

        do {
            pos = s.indexOf(part);
            if (pos != -1) s = s.substring(0, pos) + s.substring(pos + part.length());
        } while (pos != -1);
        return s;
    }

    public static void main(String[] args) {
        assert new Solution().removeOccurrences("daabcbaabcbc", "abc").equals("dab");
        assert new Solution().removeOccurrences("axxxxyyyyb", "xy").equals("ab");
    }

}
