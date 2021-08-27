package problem.p1221splitastringinbalancedstrings;

/**
 * 1221. Split a String in Balanced Strings
 *
 * https://leetcode-cn.com/problems/split-a-string-in-balanced-strings/
 *
 * Balanced strings are those that have an equal quantity of 'L' and 'R' characters.
 *
 * Given a balanced string s, split it in the maximum amount of balanced strings.
 *
 * Return the maximum amount of split balanced strings.
 */

public class Solution {

    public int balancedStringSplit(String s) {
        int ans = 0, l = 0, r = 0;
        for (var c : s.toCharArray()) {
            if (c == 'L') l++;
            else r++;
            if (l == r) {
                ans++;
                l = 0;
                r = 0;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().balancedStringSplit("RLRRLLRLRL") == 4;
        assert new Solution().balancedStringSplit("RLLLLRRRLR") == 3;
        assert new Solution().balancedStringSplit("LLLLRRRR") == 1;
        assert new Solution().balancedStringSplit("RLRRRLLRLL") == 2;
    }

}
