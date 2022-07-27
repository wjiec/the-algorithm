package problem.p1318minimumflipstomakeaorbequaltoc;

/**
 * 1318. Minimum Flips to Make a OR b Equal to c
 *
 * https://leetcode.cn/problems/minimum-flips-to-make-a-or-b-equal-to-c/
 *
 * Given 3 positives numbers a, b and c. Return the minimum flips required in some
 * bits of a and b to make ( a OR b == c ). (bitwise OR operation).
 *
 * Flip operation consists of change any single bit 1 to 0 or change
 * the bit 0 to 1 in their binary representation.
 */

public class Solution {

    public int minFlips(int a, int b, int c) {
        int ans = 0;
        for (int i = 0; i < 32; i++) {
            int ax = a & (1 << i), bx = b & (1 << i), cx = c & (1 << i);
            if (cx == 0) {
                if (ax != 0) ans++;
                if (bx != 0) ans++;
            } else {
                if (ax == 0 && bx == 0) ans++;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().minFlips(2, 6, 5) == 3;
        assert new Solution().minFlips(4, 2, 7) == 1;
        assert new Solution().minFlips(1, 2, 3) == 0;
    }

}
