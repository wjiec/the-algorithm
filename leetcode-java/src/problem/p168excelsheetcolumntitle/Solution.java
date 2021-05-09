package problem.p168excelsheetcolumntitle;

public class Solution {

    public String convertToTitle(int columnNumber) {
        StringBuilder sb = new StringBuilder();
        while (columnNumber >= 1) {
            sb.append((char) ('A' + ((columnNumber - 1) % 26)));
            columnNumber = (columnNumber - 1) / 26;
        }
        return sb.reverse().toString();
    }

    public static void main(String[] args) {
        assert new Solution().convertToTitle(1).equals("A");
        assert new Solution().convertToTitle(28).equals("AB");
        assert new Solution().convertToTitle(701).equals("ZY");
    }

}
