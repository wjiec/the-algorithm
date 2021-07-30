package daily.d210730p171excelsheetcolumnnumber;

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
        int ans = 0;
        for (char c : columnTitle.toCharArray()) ans = (ans * 26) + c - 'A' + 1;
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().titleToNumber("A") == 1;
        assert new Solution().titleToNumber("AB") == 28;
        assert new Solution().titleToNumber("ZY") == 701;
        assert new Solution().titleToNumber("FXSHRXW") == 2147483647;
    }

}
