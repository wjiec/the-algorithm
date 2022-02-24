package problem.p246strobogrammaticnumber;

/**
 * 246. Strobogrammatic Number
 *
 * https://leetcode-cn.com/problems/strobogrammatic-number/
 *
 * Given a string num which represents an integer, return true if num is a strobogrammatic number.
 *
 * A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).
 */

public class Solution {

    public boolean isStrobogrammatic(String num) {
        int l = 0, r = num.length() - 1;
        for (; l < r; l++, r--) {
            if (num.charAt(l) == '0' && num.charAt(r) == '0') {
                continue;
            } else if (num.charAt(l) == '1' && num.charAt(r) == '1') {
                continue;
            } else if (num.charAt(l) == '6' && num.charAt(r) == '9') {
                continue;
            } else if (num.charAt(l) == '8' && num.charAt(r) == '8') {
                continue;
            } else if (num.charAt(l) == '9' && num.charAt(r) == '6') {
                continue;
            }
            return false;
        }
        return num.length() % 2 == 0 || num.charAt(l) == '8' || num.charAt(l) == '1' || num.charAt(l) == '0';
    }

    public static void main(String[] args) {
        assert new Solution().isStrobogrammatic("60809");

        assert new Solution().isStrobogrammatic("69");
        assert new Solution().isStrobogrammatic("88");
        assert !new Solution().isStrobogrammatic("962");
        assert new Solution().isStrobogrammatic("1");
        assert !new Solution().isStrobogrammatic("25");
    }

}
