package problem.p1780checkifnumberisasumofpowersofthree;

/**
 * 1780. Check if Number is a Sum of Powers of Three
 *
 * https://leetcode.cn/problems/check-if-number-is-a-sum-of-powers-of-three/
 *
 * Given an integer n, return true if it is possible to represent n as the sum of distinct
 * powers of three. Otherwise, return false.
 *
 * An integer y is a power of three if there exists an integer x such that y == 3x.
 */

public class Solution {

    public boolean checkPowersOfThree(int n) {
        if (n % 3 == 2) return false;

        int[] powers = new int[15]; powers[0] = 1;
        for (int i = 1; i < powers.length; i++) {
            powers[i] = powers[i - 1] * 3;
            if (powers[i] == n) return true;
        }

        int shift = 0;
        while (powers[shift] <= n) shift++;

        int bound = 1 << shift;
        for (int i = 1; i < bound; i++) {
            int curr = 0, state = i;
            for (int j = 0; state != 0; state >>= 1, j++) {
                if ((state & 1) == 1) {
                    curr += powers[j];
                }
                if (curr > n) break;
            }
            if (curr == n) return true;
        }
        return false;
    }

    private static class Mathematics {
        public boolean checkPowersOfThree(int n) {
            while (n != 0) {
                if (n % 3 == 2) return false;
                n /= 3;
            }
            return true;
        }
    }

    public static void main(String[] args) {
        assert new Solution().checkPowersOfThree(12);
        assert new Solution().checkPowersOfThree(91);
        assert !new Solution().checkPowersOfThree(21);

        assert new Mathematics().checkPowersOfThree(12);
        assert new Mathematics().checkPowersOfThree(91);
        assert !new Mathematics().checkPowersOfThree(21);
    }

}
