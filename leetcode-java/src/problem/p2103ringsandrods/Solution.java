package problem.p2103ringsandrods;

/**
 * 2103. Rings and Rods
 *
 * https://leetcode-cn.com/problems/rings-and-rods/
 *
 * There are n rings and each ring is either red, green, or blue. The rings are distributed
 * across ten rods labeled from 0 to 9.
 *
 * You are given a string rings of length 2n that describes the n rings that are placed onto the rods.
 *
 * Every two characters in rings forms a color-position pair that is used to describe each ring where:
 *
 * The first character of the ith pair denotes the ith ring's color ('R', 'G', 'B').
 * The second character of the ith pair denotes the rod that the ith ring is placed on ('0' to '9').
 *
 * For example, "R3G2B1" describes n == 3 rings: a red ring placed onto the rod labeled 3,
 * a green ring placed onto the rod labeled 2, and a blue ring placed onto the rod labeled 1.
 *
 * Return the number of rods that have all three colors of rings on them.
 */

public class Solution {

    public int countPoints(String rings) {
        int[] map = new int[10];
        for (int i = 0; i < rings.length(); i += 2) {
            map[rings.charAt(i + 1) - '0'] |= 1 << (rings.charAt(i) - 'A');
        }

        int ans = 0;
        int expected = 1 << ('R' - 'A') | 1 << ('G' - 'A') | 1 << ('B' - 'A');
        for (var n : map) if (n == expected) ans++;
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().countPoints("B0B6G0R6R0R6G9") == 1;
        assert new Solution().countPoints("B0R0G0R9R0B0G0") == 1;
        assert new Solution().countPoints("G4") == 0;
    }

}
