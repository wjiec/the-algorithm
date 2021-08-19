package daily.d210819p345reversevowelsofastring;

/**
 * 345. Reverse Vowels of a String
 *
 * https://leetcode-cn.com/problems/reverse-vowels-of-a-string/
 *
 * Given a string s, reverse only all the vowels in the string and return it.
 *
 * The vowels are 'a', 'e', 'i', 'o', and 'u', and they can appear in both cases.
 */

public class Solution {

    public String reverseVowels(String s) {
        char[] chars = s.toCharArray();
        int l = 0, r = chars.length - 1;
        while (l < r) {
            if (isVowel(chars[l]) && isVowel(chars[r])) {
                char tmp = chars[l];
                chars[l] = chars[r];
                chars[r] = tmp;

                l++; r--;
            }

            if (!isVowel(chars[l])) l++;
            if (!isVowel(chars[r])) r--;
        }
        return new String(chars);
    }

    private boolean isVowel(char c) {
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u'
            || c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U';
    }

    public static void main(String[] args) {
        assert new Solution().reverseVowels("hello").equals("holle");
        assert new Solution().reverseVowels("leetcode").equals("leotcede");
    }

}
