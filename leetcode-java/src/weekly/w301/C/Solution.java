package weekly.w301.C;

/**
 * 6114. Move Pieces to Obtain a String
 *
 * https://leetcode.cn/contest/weekly-contest-301/problems/move-pieces-to-obtain-a-string/
 *
 * You are given two strings start and target, both of length n.
 * Each string consists only of the characters 'L', 'R', and '_' where:
 *
 * The characters 'L' and 'R' represent pieces, where a piece 'L' can move to the left only
 * if there is a blank space directly to its left, and a piece 'R' can move to the right only
 * if there is a blank space directly to its right.
 *
 * The character '_' represents a blank space that can be occupied by any of the 'L' or 'R' pieces.
 * Return true if it is possible to obtain the string target by moving the pieces of the string start
 * any number of times. Otherwise, return false.
 */

public class Solution {

    public boolean canChange(String start, String target) {
        int a = 0, b = 0, l = start.length();
        while (a < l && b < l) {
            while (a < l && start.charAt(a) == '_') a++;
            while (b < l && target.charAt(b) == '_') b++;
            if (a == l || b == l) break;
            if (start.charAt(a) != target.charAt(b)) return false;
            if (start.charAt(a) == 'L' && a < b) return false;
            if (start.charAt(a) == 'R' && a > b) return false;
            a++; b++;
        }
        while (a < l && start.charAt(a) == '_') a++;
        while (b < l && target.charAt(b) == '_') b++;
        return a == l && b == l;
    }

    public static void main(String[] args) {
        assert new Solution().canChange("__", "__");
        assert !new Solution().canChange("_L__R__R_L", "L______RR_");
        assert !new Solution().canChange("_LL__R__R_", "L___L___RR");

        assert new Solution().canChange("_L__R__R_", "L______RR");
        assert !new Solution().canChange("R_L_", "__LR");
        assert !new Solution().canChange("_R", "R_");
    }

}
