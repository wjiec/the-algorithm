package daily.d210809p313superuglynumber;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * 313. Super Ugly Number
 *
 * https://leetcode-cn.com/problems/super-ugly-number/
 *
 * A super ugly number is a positive integer whose prime factors are in the array primes.
 *
 * Given an integer n and an array of integers primes, return the nth super ugly number.
 *
 * The nth super ugly number is guaranteed to fit in a 32-bit signed integer.
 */

public class Solution {

    public int nthSuperUglyNumber(int n, int[] primes) {
        Set<Long> visited = new HashSet<>();
        PriorityQueue<Long> seq = new PriorityQueue<>();
        seq.add(1L);

        for (int i = 1; i < n; i++) {
            var curr = seq.remove();
            for (var prime : primes) {
                long next = curr * prime;
                if (!visited.contains(next)) {
                    seq.add(next);
                    visited.add(next);
                }
            }
        }

        long ret = seq.remove();
        return (int) ret;
    }

    public static void main(String[] args) {
        assert new Solution().nthSuperUglyNumber(12, new int[]{2,7,13,19}) == 32;
        assert new Solution().nthSuperUglyNumber(1, new int[]{2,3,5}) == 1;
    }

}
