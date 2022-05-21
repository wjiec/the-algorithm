package problem.p848shiftingletters;

/**
 * 848. Shifting Letters
 *
 * https://leetcode.cn/problems/shifting-letters/
 *
 * You are given a string s of lowercase English letters and an integer array shifts of the same length.
 *
 * Call the shift() of a letter, the next letter in the alphabet, (wrapping around so that 'z' becomes 'a').
 *
 * For example, shift('a') = 'b', shift('t') = 'u', and shift('z') = 'a'.
 * Now for each shifts[i] = x, we want to shift the first i + 1 letters of s, x times.
 *
 * Return the final string after all such shifts to s are applied.
 */

public class Solution {

    public String shiftingLetters(String s, int[] shifts) {
        long sum = 0;
        char[] chars = s.toCharArray();
        for (int i = chars.length - 1; i >= 0; i--) {
            sum += shifts[i];
            chars[i] = (char) (((chars[i] - 'a' + sum) % 26) + 'a');
        }
        return new String(chars);
    }

    public static void main(String[] args) {
        assert new Solution().shiftingLetters("abc", new int[]{3,5,9}).equals("rpl");
        assert new Solution().shiftingLetters("aaa", new int[]{1,2,3}).equals("gfd");
    }

}
