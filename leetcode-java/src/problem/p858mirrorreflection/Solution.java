package problem.p858mirrorreflection;

import common.TODO;

/**
 * 858. Mirror Reflection
 *
 * https://leetcode.cn/problems/mirror-reflection/
 *
 * There is a special square room with mirrors on each of the four walls.
 * Except for the southwest corner, there are receptors on
 * each of the remaining corners, numbered 0, 1, and 2.
 *
 * The square room has walls of length p and a laser ray from the southwest corner
 * first meets the east wall at a distance q from the 0th receptor.
 *
 * Given the two integers p and q, return the number of the receptor that the ray meets first.
 *
 * The test cases are guaranteed so that the ray will meet a receptor eventually.
 */

public class Solution {

    @TODO(tips = "Math")
    public int mirrorReflection(int p, int q) {
        int g = gcd(p, q);
        p /= g; p %= 2;
        q /= g; q %= 2;

        return p == 1 && q == 1 ? 1 : (p == 1 ? 0 : 2);
    }

    private int gcd(int a, int b) {
        return a % b == 0 ? b : gcd(b, a % b);
    }

    public static void main(String[] args) {
        assert new Solution().mirrorReflection(2, 1) == 2;
        assert new Solution().mirrorReflection(3, 1) == 1;
    }

}
