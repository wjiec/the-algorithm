package problem.p204countprimes;

import java.util.ArrayList;
import java.util.List;

/**
 * 204. Count Primes
 *
 * https://leetcode-cn.com/problems/count-primes/
 *
 * Count the number of prime numbers less than a non-negative number, n.
 */

public class Solution {

    public int countPrimes(int n) {
        if (n <= 2) {
            return 0;
        }

        List<Integer> primes = new ArrayList<>();
        boolean[] numbers = new boolean[n];
        for (int i = 2; i < n; i++) {
            if (!numbers[i]) {
                primes.add(i);
            }
            for (int j = 0; j < primes.size() && i * primes.get(j) < n; j++) {
                numbers[i * primes.get(j)] = true;
                if (i % primes.get(j) == 0) {
                    break;
                }
            }
        }

        return primes.size();
    }

    public int countPrimes1(int n) {
        if (n <= 2) {
            return 0;
        }

        List<Integer> primes = new ArrayList<>();
        primes.add(2);

        for (int i = 3; i < n; i++) {
            int v = (int) Math.sqrt(i);
            for (var prime : primes) {
                if (i % prime == 0) {
                    break;
                } else if (prime >= v) {
                    primes.add(i);
                    break;
                }
            }
        }

        return primes.size();
    }

    public static void main(String[] args) {
        assert new Solution().countPrimes(0) == 0;
        assert new Solution().countPrimes(1) == 0;
        assert new Solution().countPrimes(10) == 4;
        assert new Solution().countPrimes(100) == 25;
        assert new Solution().countPrimes(5000000) == 25;
    }

}
