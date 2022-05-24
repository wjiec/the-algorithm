package problem.p866primepalindrome;

import common.TODO;

/**
 * 866. Prime Palindrome
 *
 * https://leetcode.cn/problems/prime-palindrome/
 *
 * Given an integer n, return the smallest prime palindrome greater than or equal to n.
 *
 * An integer is prime if it has exactly two divisors: 1 and itself. Note that 1 is not a prime number.
 *
 * For example, 2, 3, 5, 7, 11, and 13 are all primes.
 * An integer is a palindrome if it reads the same from left to right as it does from right to left.
 *
 * For example, 101 and 12321 are palindromes.
 * The test cases are generated so that the answer always exists and is in the range [2, 2 * 108].
 */

public class Solution {

    @TODO
    public int primePalindrome(int n) {
        for (int len = 1; len <= 5; len++) {
            int l = (int) Math.pow(10, len - 1), r = (int) Math.pow(10, len);
            for (int root = l; root < r; root++) {
                StringBuilder sb = new StringBuilder(String.valueOf(root));
                for (int k = len - 2; k >= 0; k--) sb.append(sb.charAt(k));

                int val = Integer.parseInt(sb.toString());
                if (val >= n && isPrime(val)) return val;
            }

            for (int root = l; root < r; root++) {
                StringBuilder sb = new StringBuilder(String.valueOf(root));
                for (int k = len - 1; k >= 0; k--) sb.append(sb.charAt(k));

                int val = Integer.parseInt(sb.toString());
                if (val >= n && isPrime(val)) return val;
            }
        }
        return -1; // unreached code
    }

    private boolean isPrime(int v) {
        if (v < 2) return false;

        int sqrt = (int) Math.sqrt(v);
        for (int i = 2; i <= sqrt; i++) {
            if (v % i == 0) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        assert new Solution().primePalindrome(6) == 7;
        assert new Solution().primePalindrome(8) == 11;
        assert new Solution().primePalindrome(13) == 101;
    }

}
