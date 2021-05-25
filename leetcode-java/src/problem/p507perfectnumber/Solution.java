package problem.p507perfectnumber;

/**
 * 507. Perfect Number
 *
 * https://leetcode-cn.com/problems/perfect-number/
 *
 * A perfect number is a positive integer that is equal to the sum of its positive divisors,
 * excluding the number itself. A divisor of an integer x is an integer that can divide x evenly.
 *
 * Given an integer n, return true if n is a perfect number, otherwise return false.
 */

public class Solution {

    public boolean checkPerfectNumber(int num) {
        int[] primes = new int[]{2, 3, 5, 7, 11, 13, 17, 19, 31};
        for (int prime : primes) {
            if (pn(prime) == num) {
                return true;
            }
        }
        return false;
    }

    private int pn(int p) {
        // 2^(p - 1) * (2^p - 1)
        return (1 << (p - 1)) * ((1 << p) - 1);
    }

    public boolean checkPerfectNumber1(int num) {
        if (num <= 2) return false;
        int sum = 1 + (num % 2 == 0 ? (2 + num / 2) : 0);
        for (int i = 3; i < Math.sqrt(num); i++) {
            if (num % i == 0) {
                int div = num / i;
                if (div < i) {
                    break;
                }
                sum += i + div;
            }
        }
        return sum == num;
    }

    public static void main(String[] args) {
        assert new Solution().checkPerfectNumber(28);
        assert new Solution().checkPerfectNumber(6);
        assert new Solution().checkPerfectNumber(496);
        assert !new Solution().checkPerfectNumber(2);

        assert new Solution().checkPerfectNumber1(28);
        assert new Solution().checkPerfectNumber1(6);
        assert new Solution().checkPerfectNumber1(496);
        assert !new Solution().checkPerfectNumber1(2);
    }

}
