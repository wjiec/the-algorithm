package weekly.w399.B;

/**
 * 3163. String Compression III
 *
 * https://leetcode.cn/contest/weekly-contest-399/problems/string-compression-iii/
 *
 * Given a string word, compress it using the following algorithm:
 *
 * Begin with an empty string comp. While word is not empty, use the following operation:
 * Remove a maximum length prefix of word made of a single character c repeating at most 9 times.
 * Append the length of the prefix followed by c to comp.
 *
 * Return the string comp.
 */

public class Solution {

    public String compressedString(String word) {
        StringBuilder sb = new StringBuilder();
        int cnt = 1; char curr = word.charAt(0);
        for (int i = 1; i <= word.length(); i++) {
            if (i == word.length() || word.charAt(i) != curr) {
                for (; cnt > 0; cnt -= 9) sb.append(Math.min(cnt, 9)).append(curr);
                cnt = 1; if (i < word.length()) curr = word.charAt(i);
            } else cnt++;
        }
        return sb.toString();
    }

    public static void main(String[] args) {
    }

}
