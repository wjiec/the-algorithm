package weekly.w352.B;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 6916. Prime Pairs With Target Sum
 *
 * https://leetcode.cn/contest/weekly-contest-352/problems/prime-pairs-with-target-sum/
 *
 * You are given an integer n. We say that two integers x and y form a prime number pair if:
 *
 * 1 <= x <= y <= n
 * x + y == n
 * x and y are prime numbers
 * Return the 2D sorted list of prime number pairs [xi, yi]. The list should be sorted in
 * increasing order of xi. If there are no prime number pairs at all, return an empty array.
 *
 * Note: A prime number is a natural number greater than 1 with only two factors, itself and 1.
 */

public class Solution {

    public List<List<Integer>> findPrimePairs(int n) {
        List<Integer> primes = new ArrayList<>();
        primes.add(2);

        boolean[] skipped = new boolean[n / 2 + 1];
        for (int i = 3; i <= n; i += 2) {
            if (!skipped[i / 2]) primes.add(i);
            for (int j = 1; j <= primes.size() && i * primes.get(j) <= n; j++) {
                int next = i * primes.get(j);
                if (next % 2 == 1) skipped[next / 2] = true;
                if (i % primes.get(j) == 0) break;
            }
        }

        Set<Integer> copy = new HashSet<>(primes);
        List<List<Integer>> ans = new ArrayList<>();
        for (var v : primes) {
            if (n - v < v) break;
            if (copy.contains(n - v)) ans.add(List.of(v, n - v));
        }
        return ans;
    }

    public static void main(String[] args) {
    }

}
