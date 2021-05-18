package problem.p345reversevowelsofastring;

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
        char[] vowels = new char[255];
        vowels['A'] = vowels['E'] = vowels['I'] = vowels['O'] = vowels['U'] = 1;
        vowels['a'] = vowels['e'] = vowels['i'] = vowels['o'] = vowels['u'] = 1;

        int sz = s.length(), l = 0, r = sz - 1;
        char[] chars = new char[sz];
        for (int i = 0; i < sz; i++) {
            chars[i] = s.charAt(i);
        }

        while (l < r) {
            while (vowels[s.charAt(l)] == 0 && l < r) {
                l++;
            }
            while (vowels[s.charAt(r)] == 0 && l < r) {
                r--;
            }

            if (l < r) {
                chars[l++] = s.charAt(r);
                chars[r--] = s.charAt(l - 1);
            }
        }

        return new String(chars);
    }

    public static void main(String[] args) {
        assert new Solution().reverseVowels("hello").equals("holle");
        assert new Solution().reverseVowels(" ").equals(" ");
        assert new Solution().reverseVowels(".,").equals(".,");
        assert new Solution().reverseVowels("leetcode").equals("leotcede");
    }

}
