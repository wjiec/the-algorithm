package problem.p1704determineifstringhalvesarealike;

/**
 * 1704. Determine if String Halves Are Alike
 *
 * https://leetcode-cn.com/problems/determine-if-string-halves-are-alike/
 *
 * You are given a string s of even length. Split this string into two halves of equal lengths,
 * and let a be the first half and b be the second half.
 *
 * Two strings are alike if they have the same number of vowels ('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U').
 *
 * Notice that s contains uppercase and lowercase letters.
 *
 * Return true if a and b are alike. Otherwise, return false.
 */

public class Solution {

    private final String vowels = "aeiouAEIOU";

    public boolean halvesAreAlike(String s) {
        int mid = s.length() / 2;
        char[] chars = s.toCharArray();
        return countVowels(chars, 0, mid) == countVowels(chars, mid, s.length());
    }

    private int countVowels(char[] chars, int l, int r) {
        int c = 0;
        for (; l < r; l++) {
            if (vowels.indexOf(chars[l]) != -1) {
                c++;
            }
        }
        return c;
    }

    public static void main(String[] args) {
        assert new Solution().halvesAreAlike("book");
        assert !new Solution().halvesAreAlike("textbook");
        assert !new Solution().halvesAreAlike("MerryChristmas");
        assert new Solution().halvesAreAlike("AbCdEfGh");
    }

}
