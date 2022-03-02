package problem.p1427performstringshifts;

/**
 * 1427. Perform String Shifts
 *
 * https://leetcode-cn.com/problems/perform-string-shifts/
 *
 * You are given a string s containing lowercase English letters, and a matrix shift,
 * where shift[i] = [directioni, amounti]:
 *
 * directioni can be 0 (for left shift) or 1 (for right shift).
 * amounti is the amount by which string s is to be shifted.
 * A left shift by 1 means remove the first character of s and append it to the end.
 * Similarly, a right shift by 1 means remove the last character of s and add it to the beginning.
 * Return the final string after all operations.
 */

public class Solution {

    public String stringShift(String s, int[][] shift) {
        int offset = 0, n = s.length();
        for (var item : shift) {
            if (item[0] == 0) offset += item[1];
            else offset -= item[1];
        }
        if (offset < 0) offset = n - (Math.abs(offset) % n);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) sb.append(s.charAt((offset + i) % n));
        return sb.toString();
    }

    public static void main(String[] args) {
        assert new Solution().stringShift("abc", new int[][]{{0, 1}, {1, 2}}).equals("cab");
        assert new Solution().stringShift("abcdefg", new int[][]{
            {1, 1}, {1, 1}, {0, 2}, {1, 3}
        }).equals("efgabcd");
    }

}
