package problem.p917reverseonlyletters;

/**
 * 917. Reverse Only Letters
 *
 * https://leetcode-cn.com/problems/reverse-only-letters/
 *
 * Given a string s, return the "reversed" string where all characters that are not a letter stay in the same place,
 * and all letters reverse their positions.
 */

public class Solution {

    public String reverseOnlyLetters(String s) {
        char[] chars = s.toCharArray();
        int l = 0, r = chars.length - 1;
        for (; l < r; l++, r--) {
            while (l < r && !Character.isLetter(chars[l])) l++;
            while (l < r && !Character.isLetter(chars[r])) r--;

            char c = chars[l]; chars[l] = chars[r]; chars[r] = c;
        }
        return new String(chars);
    }

    public static void main(String[] args) {
        assert new Solution().reverseOnlyLetters("ab-cd").equals("dc-ba");
        assert new Solution().reverseOnlyLetters("a-bC-dEf-ghIj").equals("j-Ih-gfE-dCba");
        assert new Solution().reverseOnlyLetters("Test1ng-Leet=code-Q!").equals("Qedo1ct-eeLg=ntse-T!");
    }

}
