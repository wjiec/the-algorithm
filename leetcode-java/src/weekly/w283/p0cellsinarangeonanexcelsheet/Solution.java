package weekly.w283.p0cellsinarangeonanexcelsheet;

import java.util.ArrayList;
import java.util.List;

/**
 * 6016. Cells in a Range on an Excel Sheet
 *
 * https://leetcode-cn.com/contest/weekly-contest-283/problems/cells-in-a-range-on-an-excel-sheet/
 *
 * A cell (r, c) of an excel sheet is represented as a string "<col><row>" where:
 *
 * <col> denotes the column number c of the cell. It is represented by alphabetical letters.
 * For example, the 1st column is denoted by 'A', the 2nd by 'B', the 3rd by 'C', and so on.
 * <row> is the row number r of the cell. The rth row is represented by the integer r.
 * You are given a string s in the format "<col1><row1>:<col2><row2>", where <col1> represents the column c1,
 * <row1> represents the row r1, <col2> represents the column c2, and <row2> represents the row r2,
 * such that r1 <= r2 and c1 <= c2.
 *
 * Return the list of cells (x, y) such that r1 <= x <= r2 and c1 <= y <= c2. The cells should be
 * represented as strings in the format mentioned above and be sorted in non-decreasing
 * order first by columns and then by rows.
 */

public class Solution {

    public List<String> cellsInRange(String s) {
        String start = s.split(":")[0];
        String end = s.split(":")[1];
        char x = start.charAt(0), y = end.charAt(0);
        int a = start.charAt(1) - '0', b = end.charAt(1) - '0';

        List<String> ans = new ArrayList<>();
        for (char c = x; c <= y; c++) {
            for (int i = a; i <= b; i++) {
                ans.add("" + c + i);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().cellsInRange("K1:L2"));
        System.out.println(new Solution().cellsInRange("A1:F1"));
    }

}
