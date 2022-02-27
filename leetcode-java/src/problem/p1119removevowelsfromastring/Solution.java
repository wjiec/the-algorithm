package problem.p1119removevowelsfromastring;

/**
 * 1119. Remove Vowels from a String
 *
 * https://leetcode-cn.com/problems/remove-vowels-from-a-string/
 *
 * Given a string s, remove the vowels 'a', 'e', 'i', 'o', and 'u' from it, and return the new string.
 */

public class Solution {

    public String removeVowels(String s) {
        char[] chars = s.toCharArray();
        int l = 0, n = chars.length;
        for (int r = 0; r < n; r++) {
            if (chars[r] == 'a' || chars[r] == 'e' || chars[r] == 'i' || chars[r] == 'o' || chars[r] == 'u') {
                continue;
            }
            chars[l++] = chars[r];
        }
        return new String(chars, 0, l);
    }

    public static void main(String[] args) {
        assert new Solution().removeVowels("leetcodeisacommunityforcoders").equals("ltcdscmmntyfrcdrs");
        assert new Solution().removeVowels("aeiou").equals("");
    }

}
