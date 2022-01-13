package problem.p6zigzagconversion;

/**
 * 6. ZigZag Conversion
 *
 * https://leetcode-cn.com/problems/zigzag-conversion/
 *
 * The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this:
 *  (you may want to display this pattern in a fixed font for better legibility)
 */

public class Solution {

    public String convert(String s, int numRows) {
        if (numRows == 1 || s.length() <= numRows) return s;

        StringBuilder sb = new StringBuilder();
        int step = numRows + numRows - 2, n = s.length();
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j + i < n; j += step) {
                sb.append(s.charAt(j + i));
                if (i != 0 && i != numRows - 1 && j + step - i < n) {
                    sb.append(s.charAt(j + step - i));
                }
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        assert new Solution().convert("PAYPALISHIRING", 3).equals("PAHNAPLSIIGYIR");
        assert new Solution().convert("PAYPALISHIRING", 4).equals("PINALSIGYAHRPI");
        assert new Solution().convert("A", 1).equals("A");
    }

}
