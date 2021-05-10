package problem.p171excelsheetcolumnnumber;

/**
 * 171. Excel Sheet Column Number
 *
 * https://leetcode-cn.com/problems/excel-sheet-column-number/
 *
 * Given a string columnTitle that represents the column title as appear in an Excel sheet,
 * return its corresponding column number.
 */

public class Solution {

    public int titleToNumber(String columnTitle) {
        int rs = 0, sz = columnTitle.length();
        for (int i = 0; i < sz; i++) {
            rs = rs * 26 + (columnTitle.charAt(i) - 'A' + 1);
        }
        return rs;
    }

    public static void main(String[] args) {
        assert new Solution().titleToNumber("A") == 1;
        assert new Solution().titleToNumber("AA") == 27;
        assert new Solution().titleToNumber("AB") == 28;
        assert new Solution().titleToNumber("ZY") == 701;
    }

}
