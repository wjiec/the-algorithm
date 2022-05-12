package problem.p777swapadjacentinlrstring;

/**
 * 777. Swap Adjacent in LR String
 *
 * https://leetcode.cn/problems/swap-adjacent-in-lr-string/
 *
 * In a string composed of 'L', 'R', and 'X' characters, like "RXXLRXRXL",
 * a move consists of either replacing one occurrence of "XL" with "LX",
 * or replacing one occurrence of "RX" with "XR".
 *
 * Given the starting string start and the ending string end,
 * return True if and only if there exists a sequence of moves to
 * transform one string to the other.
 */

public class Solution {

    public boolean canTransform(String start, String end) {
        int n = start.length(), l = 0, r = 0;
        while (l < n && r < n) {
            while (l < n && start.charAt(l) == 'X') l++;
            while (r < n && end.charAt(r) == 'X') r++;

            if (l < n && r < n) {
                if (start.charAt(l) != end.charAt(r)) return false;
                if (start.charAt(l) == 'L' && l < r) return false;
                if (start.charAt(l) == 'R' && l > r) return false;

                l++; r++;
            }
        }

        while (l < n && start.charAt(l) == 'X') l++;
        while (r < n && end.charAt(r) == 'X') r++;

        return l == n && r == n;
    }

    public static void main(String[] args) {
        assert new Solution().canTransform("XXXLXXXXXX", "XXXLXXXXXX");

        assert new Solution().canTransform("RXXLRXRXL", "XRLXXRRLX");
        assert !new Solution().canTransform("X", "L");
    }

}
