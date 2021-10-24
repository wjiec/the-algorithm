package problem.p1556thousandseparator;

/**
 * 1556. Thousand Separator
 *
 * https://leetcode-cn.com/problems/thousand-separator/
 *
 * Given an integer n, add a dot (".") as the thousands separator and return it in string format.
 */

public class Solution {

    public String thousandSeparator(int n) {
        if (n == 0) return "0";
        StringBuilder sb = new StringBuilder();
        for (; n != 0; n /= 1000) {
            if (n >= 1000) sb.insert(0, String.format("%03d.", n % 1000));
            else sb.insert(0, (n % 1000) + ".");
        }
        return sb.deleteCharAt(sb.length() - 1).toString();
    }

    public static void main(String[] args) {
        assert new Solution().thousandSeparator(51040).equals("51.040");

        assert new Solution().thousandSeparator(987).equals("987");
        assert new Solution().thousandSeparator(1234).equals("1.234");
        assert new Solution().thousandSeparator(123456789).equals("123.456.789");
        assert new Solution().thousandSeparator(0).equals("0");
    }

}
