package problem.p1015smallestintegerdivisiblebyk;

/**
 * 1015. Smallest Integer Divisible by K
 *
 * https://leetcode.cn/problems/smallest-integer-divisible-by-k/
 *
 * Given a positive integer k, you need to find the length of the smallest positive integer n
 * such that n is divisible by k, and n only contains the digit 1.
 *
 * Return the length of n. If there is no such n, return -1.
 *
 * Note: n may not fit in a 64-bit signed integer.
 */

public class Solution {

    public int smallestRepunitDivByK(int k) {
        if (k % 2 == 0 || k % 5 == 0) return -1;

        int ans = 1, curr = 1;
        while (true) {
            if (curr % k == 0) return ans;
            curr = (curr * 10 + 1) % k;
            ans++;
        }
    }

    public static void main(String[] args) {
        assert new Solution().smallestRepunitDivByK(1) == 1;
        assert new Solution().smallestRepunitDivByK(2) == -1;
        assert new Solution().smallestRepunitDivByK(3) == 3;
    }

}
