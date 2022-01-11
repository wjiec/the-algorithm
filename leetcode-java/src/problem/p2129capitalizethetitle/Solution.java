package problem.p2129capitalizethetitle;

import javax.crypto.spec.ChaCha20ParameterSpec;

/**
 * 2129. Capitalize the Title
 *
 * https://leetcode-cn.com/problems/capitalize-the-title/
 *
 * You are given a string title consisting of one or more words separated by a single space,
 * where each word consists of English letters.
 *
 * Capitalize the string by changing the capitalization of each word such that:
 *
 * If the length of the word is 1 or 2 letters, change all letters to lowercase.
 * Otherwise, change the first letter to uppercase and the remaining letters to lowercase.
 * Return the capitalized title.
 */

public class Solution {

    public String capitalizeTitle(String title) {
        title = title + " ";
        StringBuilder sb = new StringBuilder();
        int l = 0, r = 0, n = title.length();
        while (r < n) {
            while (title.charAt(r) != ' ') r++;

            if (r - l > 2) sb.append(Character.toUpperCase(title.charAt(l++)));
            while (l <= r) sb.append(Character.toLowerCase(title.charAt(l++)));
            l = ++r;
        }
        return sb.deleteCharAt(sb.length() - 1).toString();
    }

    public static void main(String[] args) {
        assert new Solution().capitalizeTitle("capiTalIze tHe titLe").equals("Capitalize The Title");
        assert new Solution().capitalizeTitle("First leTTeR of EACH Word").equals("First Letter of Each Word");
        assert new Solution().capitalizeTitle("i lOve leetcode").equals("i Love Leetcode");
    }

}
