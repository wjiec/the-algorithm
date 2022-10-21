package problem.p2381shiftinglettersii;

/**
 * 2381. Shifting Letters II
 *
 * https://leetcode.cn/problems/shifting-letters-ii/
 *
 * You are given a string s of lowercase English letters and a 2D integer array shifts
 * where shifts[i] = [starti, endi, directioni]. For every i, shift the characters in s
 * from the index starti to the index endi (inclusive) forward if directioni = 1, or shift
 * the characters backward if directioni = 0.
 *
 * Shifting a character forward means replacing it with the next letter in the alphabet
 * (wrapping around so that 'z' becomes 'a'). Similarly, shifting a character backward
 * means replacing it with the previous letter in the alphabet (wrapping around so
 * that 'a' becomes 'z').
 *
 * Return the final string after all such shifts to s are applied.
 */

public class Solution {

    public String shiftingLetters(String s, int[][] shifts) {
        int[] diff = new int[s.length() + 1];
        for (var shift : shifts) {
            int base = shift[2] == 1 ? 1 : -1;
            diff[shift[0]] += base;
            diff[shift[1] + 1] -= base;
        }

        for (int i = 1; i < diff.length; i++) {
            diff[i] += diff[i - 1];
        }

        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            int offset = ((chars[i] - 'a') + (diff[i] % 26) + 26) % 26;
            chars[i] = (char) ('a' + offset);
        }
        return new String(chars);
    }

    public static void main(String[] args) {
        assert new Solution().shiftingLetters("abc", new int[][]{{0,1,0},{1,2,1},{0,2,1}}).equals("ace");
        assert new Solution().shiftingLetters("dztz", new int[][]{{0,0,0},{1,1,1}}).equals("catz");
    }

}
