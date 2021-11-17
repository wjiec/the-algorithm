package problem.p1837sumofdigitsinbasek;

/**
 * 1837. Sum of Digits in Base K
 *
 * https://leetcode-cn.com/problems/sum-of-digits-in-base-k/
 *
 * Given an integer n (in base 10) and a base k, return the sum of the digits of n
 * after converting n from base 10 to base k.
 *
 * After converting, each digit should be interpreted as a base 10 number,
 * and the sum should be returned in base 10.
 */

public class Solution {

    public int sumBase(int n, int k) {
        if (k != 10) {
            int v = 0, base = 1;
            for (; n != 0; n /= k) {
                v += (n % k) * base;
                base *= 10;
            }
            n = v;
        }

        int ans = 0;
        for (; n != 0; n /= 10) {
            ans += n % 10;
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().sumBase(48, 7) == 12;
        assert new Solution().sumBase(34, 6) == 9;
        assert new Solution().sumBase(10, 10) == 1;
    }

}
