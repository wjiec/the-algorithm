package weekly.bw152.B;

/**
 * 3484. Design Spreadsheet
 *
 * https://leetcode.cn/contest/biweekly-contest-152/problems/design-spreadsheet/
 *
 * A spreadsheet is a grid with 26 columns (labeled from 'A' to 'Z') and a given number of rows.
 *
 * Each cell in the spreadsheet can hold an integer value between 0 and 105.
 *
 * Implement the Spreadsheet class:
 *
 * Spreadsheet(int rows) Initializes a spreadsheet with 26 columns (labeled 'A' to 'Z') and the
 * specified number of rows. All cells are initially set to 0.
 *
 * void setCell(String cell, int value) Sets the value of the specified cell. The cell
 * reference is provided in the format "AX" (e.g., "A1", "B10"), where the letter represents
 * the column (from 'A' to 'Z') and the number represents a 1-indexed row.
 *
 * void resetCell(String cell) Resets the specified cell to 0.
 *
 * int getValue(String formula) Evaluates a formula of the form "=X+Y", where X and Y are either
 * cell references or non-negative integers, and returns the computed sum.
 *
 * Note: If getValue references a cell that has not been explicitly set using setCell, its value is considered 0.
 */

public class Solution {

    private static class Spreadsheet {
        private final int[][] sheet;
        public Spreadsheet(int rows) { sheet = new int[rows][26]; }
        public void resetCell(String cell) { setCell(cell, 0); }
        public void setCell(String cell, int value) {
            sheet[Integer.parseInt(cell.substring(1))][cell.charAt(0) - 'A'] = value;
        }
        private int getCell(String cell) {
            if ('A' <= cell.charAt(0) && cell.charAt(0) <= 'Z') {
                return sheet[Integer.parseInt(cell.substring(1))][cell.charAt(0) - 'A'];
            }
            return Integer.parseInt(cell);
        }
        public int getValue(String formula) {
            String a = formula.substring(1, formula.indexOf("+"));
            String b = formula.substring(formula.indexOf("+") + 1);
            return getCell(a) + getCell(b);
        }
    }

    public static void main(String[] args) {
    }

}
