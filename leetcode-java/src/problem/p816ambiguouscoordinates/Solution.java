package problem.p816ambiguouscoordinates;

import java.util.ArrayList;
import java.util.List;

/**
 * 816. Ambiguous Coordinates
 *
 * https://leetcode.cn/problems/ambiguous-coordinates/
 *
 * We had some 2-dimensional coordinates, like "(1, 3)" or "(2, 0.5)". Then,
 * we removed all commas, decimal points, and spaces and ended up with the string s.
 *
 * For example, "(1, 3)" becomes s = "(13)" and "(2, 0.5)" becomes s = "(205)".
 * Return a list of strings representing all possibilities for what our original coordinates could have been.
 *
 * Our original representation never had extraneous zeroes, so we never started with numbers like
 * "00", "0.0", "0.00", "1.0", "001", "00.01", or any other number that can be represented with fewer digits.
 * Also, a decimal point within a number never occurs without at least one digit occurring before it,
 * so we never started with numbers like ".1".
 *
 * The final answer list can be returned in any order. All coordinates in the final answer have
 * exactly one space between them (occurring after the comma.)
 */

public class Solution {

    public List<String> ambiguousCoordinates(String s) {
        List<String> ans = new ArrayList<>();
        for (int i = 2, n = s.length() - 1; i < n; i++) {
            for (String left : ambiguous(s, 1, i)) {
                for (String right : ambiguous(s, i, n)) {
                    ans.add("(" + left + ", " + right + ")");
                }
            }
        }
        return ans;
    }

    private List<String> ambiguous(String s, int l, int r) {
        List<String> ans = new ArrayList<>();
        for (int d = 1; d <= r - l; d++) {
            String left = s.substring(l, l + d);
            String right = s.substring(l + d, r);
            if ((!left.startsWith("0") || left.equals("0")) && !right.endsWith("0")) {
                ans.add(left + (d < r - l ? "." : "") + right);
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().ambiguousCoordinates("(123)"));
        System.out.println(new Solution().ambiguousCoordinates("(0123)"));
        System.out.println(new Solution().ambiguousCoordinates("(00011)"));
        System.out.println(new Solution().ambiguousCoordinates("(100)"));
    }

}
