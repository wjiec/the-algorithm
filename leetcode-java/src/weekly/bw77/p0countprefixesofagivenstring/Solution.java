package weekly.bw77.p0countprefixesofagivenstring;

/**
 * 6051. Count Prefixes of a Given String
 *
 * https://leetcode-cn.com/contest/biweekly-contest-77/problems/count-prefixes-of-a-given-string/
 *
 * You are given a string array words and a string s, where words[i] and s comprise only of lowercase English letters.
 *
 * Return the number of strings in words that are a prefix of s.
 *
 * A prefix of a string is a substring that occurs at the beginning of the string.
 *
 * A substring is a contiguous sequence of characters within a string.
 */

public class Solution {

    public int countPrefixes(String[] words, String s) {
        int ans = 0;
        for (var word : words) {
            if (s.indexOf(word) == 0) ans++;
        }
        return ans;
    }

    public static void main(String[] args) {
    }

}
