package problem.p2507smallestvalueafterreplacingwithsumofprimefactors;

import java.util.*;

/**
 * 2507. Smallest Value After Replacing With Sum of Prime Factors
 *
 * https://leetcode.cn/problems/smallest-value-after-replacing-with-sum-of-prime-factors/
 *
 * You are given a positive integer n.
 *
 * Continuously replace n with the sum of its prime factors.
 *
 * Note that if a prime factor divides n multiple times, it should be included in
 * the sum as many times as it divides n.
 *
 * Return the smallest value n will take on.
 */

public class Solution {

    private final Set<Integer> set = new HashSet<>();
    private final List<Integer> list = new ArrayList<>();

    public int smallestValue(int n) {
        boolean[] visited = new boolean[100_000];
        for (int i = 2; i < visited.length; i++) {
            if (!visited[i]) {
                list.add(i); set.add(i);
                for (int j = 2; i * j < visited.length; j++) {
                    visited[i * j] = true;
                }
            }
        }

        return smallest(n);
    }

    private int smallest(int n) {
        if (set.contains(n)) return n;

        int s = split(n);
        if (s >= n) return n;
        return Math.min(s, smallest(s));
    }

    private final Map<Integer, Integer> memo = new HashMap<>();

    private int split(int n) {
        if (memo.containsKey(n)) return memo.get(n);

        int ans = 0;
        for (var prime : list) {
            if (n % prime == 0) {
                ans += prime + split(n / prime);
                break;
            }
        }

        memo.put(n, ans);
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().smallestValue(99951) == 7;
        assert new Solution().smallestValue(4) == 4;

        assert new Solution().smallestValue(15) == 5;
        assert new Solution().smallestValue(3) == 3;
    }

}
