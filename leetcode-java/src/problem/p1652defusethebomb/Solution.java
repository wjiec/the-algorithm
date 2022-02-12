package problem.p1652defusethebomb;

import common.Checker;

/**
 * 1652. Defuse the Bomb
 *
 * https://leetcode-cn.com/problems/defuse-the-bomb/
 *
 * You have a bomb to defuse, and your time is running out! Your informer will provide you
 * with a circular array code of length of n and a key k.
 *
 * To decrypt the code, you must replace every number. All the numbers are replaced simultaneously.
 *
 * If k > 0, replace the ith number with the sum of the next k numbers.
 * If k < 0, replace the ith number with the sum of the previous k numbers.
 * If k == 0, replace the ith number with 0.
 *
 * As code is circular, the next element of code[n-1] is code[0], and the previous element of code[0] is code[n-1].
 *
 * Given the circular array code and an integer key k, return the decrypted code to defuse the bomb!
 */

public class Solution {

    public int[] decrypt(int[] code, int k) {
        int[] ans = new int[code.length];
        for (int i = 0, l = code.length; i < l; i++) {
            ans[i] = 0;
            if (k > 0) {
                for (int j = i + l + 1; j <= i + l + k; j++) {
                    ans[i] += code[j % l];
                }
            } else if (k < 0) {
                for (int j = i + l - 1; j >= i + l + k; j--) {
                    ans[i] += code[j % l];
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        assert Checker.check(new Solution().decrypt(new int[]{5,7,1,4}, 3), new int[]{12,10,16,13});
        assert Checker.check(new Solution().decrypt(new int[]{1,2,3,4}, 0), new int[]{0,0,0,0});
        assert Checker.check(new Solution().decrypt(new int[]{2,4,9,3}, -2), new int[]{12,5,6,13});
    }

}
