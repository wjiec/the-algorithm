package problem.p788rotateddigits;

/**
 * 788. Rotated Digits
 *
 * https://leetcode-cn.com/problems/rotated-digits/
 *
 * x is a good number if after rotating each digit individually by 180 degrees,
 * we get a valid number that is different from x.
 * Each digit must be rotated - we cannot choose to leave it alone.
 *
 * A number is valid if each digit remains a digit after rotation. 0, 1, and 8 rotate to themselves;
 * 2 and 5 rotate to each other (on this case they are rotated in a different direction,
 * in other words 2 or 5 gets mirrored); 6 and 9 rotate to each other,
 * and the rest of the numbers do not rotate to any other number and become invalid.
 *
 * Now given a positive number n, how many numbers x from 1 to n are good?
 */

public class Solution {

    private static int[] map = new int[10];

    static {
        map[0] = 0; map[1] = 1; map[8] = 8;
        map[2] = 5; map[5] = 2; map[6] = 9; map[9] = 6;
        map[3] = -1; map[4] = -1; map[7] = -1;
    }

    // 5 <-> 2, 6 <-> 9, 0-1-8
    public int rotatedDigits(int n) {
        int ans = 0;
        for (int i = 2; i <= n; i++) {
            if (isGood(i)) ans++;
        }
        return ans;
    }

    private boolean isGood(int n) {
        boolean rotated = false;
        for (; n != 0; n /= 10) {
            int mod = n % 10, v = map[mod];
            if (v == -1) return false;

            rotated = rotated || mod != v;
        }
        return rotated;
    }

    public static void main(String[] args) {
        assert new Solution().rotatedDigits(10) == 4;
    }

}
