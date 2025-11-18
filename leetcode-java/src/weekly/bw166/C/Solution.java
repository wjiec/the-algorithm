package weekly.bw166.C;

import java.util.HashSet;
import java.util.Set;

/**
 * Q3. Distinct Points Reachable After Substring Removal
 *
 * https://leetcode.cn/contest/biweekly-contest-166/problems/distinct-points-reachable-after-substring-removal/
 *
 * You are given a string s consisting of characters 'U', 'D', 'L', and 'R', representing moves on an infinite 2D Cartesian grid.
 *
 * 'U': Move from (x, y) to (x, y + 1).
 * 'D': Move from (x, y) to (x, y - 1).
 * 'L': Move from (x, y) to (x - 1, y).
 * 'R': Move from (x, y) to (x + 1, y).
 * You are also given a positive integer k.
 *
 * You must choose and remove exactly one contiguous substring of length k from s. Then, start from
 * coordinate (0, 0) and perform the remaining moves in order.
 *
 * Return an integer denoting the number of distinct final coordinates reachable.
 */

public class Solution {

    public int distinctPoints(String s, int k) {
        int x = 0, y = 0;
        Set<String> ans = new HashSet<>();
        for (int l = 0, r = 0; r < s.length(); r++) {
            switch (s.charAt(r)) {
                case 'U' -> y++;
                case 'D' -> y--;
                case 'L' -> x--;
                case 'R' -> x++;
            }
            if (r >= k) {
                switch (s.charAt(l++)) {
                    case 'U' -> y--;
                    case 'D' -> y++;
                    case 'L' -> x++;
                    case 'R' -> x--;
                }
            }
            if (r + 1 >= k) ans.add("x" + x + "Y" + y);
        }
        return ans.size();
    }

    public static void main(String[] args) {
        assert new Solution().distinctPoints("DURLU", 2) == 3;
        assert new Solution().distinctPoints("UDLR", 4) == 1;
    }

}
