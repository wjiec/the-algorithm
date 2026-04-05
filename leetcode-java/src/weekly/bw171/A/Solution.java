package weekly.bw171.A;

import static ability.Prime.isPrime;

/**
 * Q1. Complete Prime Number
 *
 * https://leetcode.cn/contest/biweekly-contest-171/problems/complete-prime-number/
 *
 * You are given an integer num.
 *
 * A number num is called a Complete Prime Number if every prefix and every suffix of num is prime.
 *
 * Return true if num is a Complete Prime Number, otherwise return false.
 *
 * Note:
 *
 * A prefix of a number is formed by the first k digits of the number.
 * A suffix of a number is formed by the last k digits of the number.
 *
 * Single-digit numbers are considered Complete Prime Numbers only if they are prime.
 */

public class Solution {

    public boolean completePrime(int num) {
        if (num % 2 == 0) return num == 2;
        for (int suf = 0, base = 1; num != 0; num /= 10, base *= 10) {
            int mod = num % 10; suf += mod * base;
            if (!isPrime(num) || !isPrime(suf)) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        assert !new Solution().completePrime(888974767);
    }

}
