package weekly.w479.B;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.TreeSet;

/**
 * Q2. Largest Prime from Consecutive Prime Sum
 *
 * https://leetcode.cn/contest/weekly-contest-479/problems/largest-prime-from-consecutive-prime-sum/
 *
 * You are given an integer n.
 *
 * Return the largest prime number less than or equal to n that can be expressed as
 * the sum of one or more consecutive prime numbers starting from 2.
 *
 * If no such number exists, return 0.
 */

public class Solution {

    private static final int MAX_N = 500_001;
    private static final boolean[] primes = new boolean[MAX_N];
    private static final TreeSet<Integer> numbers = new TreeSet<>();
    static { Arrays.fill(primes, true); primes[0] = primes[1] = false; }
    static {
        Queue<Integer> q = new ArrayDeque<>();
        for (int i = 2, sum = 0; i < MAX_N; i++) {
            if (!primes[i]) continue;

            q.add(sum += i);
            while (!q.isEmpty() && q.peek() <= i) {
                if (q.remove() == i) numbers.add(i);
            }
            if (MAX_N / i < i) continue;
            for (int j = i * i; j < MAX_N; j += i) primes[j] = false;
        }
    }

    public int largestPrime(int n) {
        Integer found = numbers.floor(n);
        return found != null ? found : 0;
    }

    public static void main(String[] args) {
        assert new Solution().largestPrime(20) == 17;
        assert new Solution().largestPrime(2) == 2;
    }

}
