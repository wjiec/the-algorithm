package daily.d210911p600nonnegativeintegerswithoutconsecutiveones;

/**
 * 600. Non-negative Integers without Consecutive Ones
 *
 * https://leetcode-cn.com/problems/non-negative-integers-without-consecutive-ones/
 *
 * Given a positive integer n, return the number of the integers in the range [0, n]
 * whose binary representations do not contain consecutive ones.
 */

public class Solution {

    public int findIntegers(int n) {
        int[] bits = new int[31];
        bits[0] = bits[1] = 1;

        for (int i = 2; i < 31; i++) {
            bits[i] = bits[i - 1] + bits[i - 2];
        }

        int ans = 0, pre = 0;
        for (int i = 29; i >= 0; i--) {
            if ((n & (1 << i)) != 0) {
                ans += bits[i + 1];
                if (pre == 1) break;
                pre = 1;
            } else {
                pre = 0;
            }

            if (i == 0) ans++;
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().findIntegers(5) == 5;
    }

}
