package weekly.bw157.A;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

import static ability.Prime.Simple.isPrime;

/**
 * Q1. Sum of Largest Prime Substrings
 *
 * https://leetcode.cn/contest/biweekly-contest-157/problems/sum-of-largest-prime-substrings
 *
 * Given a string s, find the sum of the 3 largest unique prime
 * numbers that can be formed using any of its substrings.
 *
 * Return the sum of the three largest unique prime numbers that can be formed.
 * If fewer than three exist, return the sum of all available primes.
 * If no prime numbers can be formed, return 0.
 *
 * Note: Each prime number should be counted only once, even if it appears in multiple substrings.
 * Additionally, when converting a substring to an integer, any leading zeros are ignored.
 */

public class Solution {

    public long sumOfLargestPrimes(String s) {
        Set<Long> uniq = new HashSet<>();
        PriorityQueue<Long> pq = new PriorityQueue<>();
        for (int i = 0; i < s.length(); i++) {
            for (int j = i + 1; j <= s.length(); j++) {
                long v = Long.parseLong(s.substring(i, j));
                if (isPrime(v) && uniq.add(v) && pq.add(v) && pq.size() > 3) {
                    uniq.remove(pq.remove());
                }
            }
        }

        long ans = 0;
        for (var v : pq) ans += v;
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().sumOfLargestPrimes("6357501617") == 6415753395L;
        assert new Solution().sumOfLargestPrimes("6735992919") == 36412777;
        assert new Solution().sumOfLargestPrimes("12234") == 1469;
    }

}
