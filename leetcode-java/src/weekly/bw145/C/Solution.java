package weekly.bw145.C;

import java.util.*;

/**
 * 3377. Digit Operations to Make Two Integers Equal
 *
 * https://leetcode.cn/contest/biweekly-contest-145/problems/digit-operations-to-make-two-integers-equal/
 *
 * You are given two integers n and m that consist of the same number of digits.
 *
 * You can perform the following operations any number of times:
 *
 * Choose any digit from n that is not 9 and increase it by 1.
 * Choose any digit from n that is not 0 and decrease it by 1.
 * The integer n must not be a prime number at any point, including its original value and after each operation.
 *
 * The cost of a transformation is the sum of all values that n takes throughout the operations performed.
 *
 * Return the minimum cost to transform n into m. If it is impossible, return -1.
 *
 * A prime number is a natural number greater than 1 with only two factors, 1 and itself.
 */

public class Solution {

    private static final boolean[] isPrimes = new boolean[10001];
    static {
        Arrays.fill(isPrimes, true); isPrimes[1] = false;
        for (int i = 2; i < isPrimes.length; i++) {
            if (isPrimes[i]) {
                for (int j = 2; i * j < isPrimes.length; j++) {
                    isPrimes[i * j] = false;
                }
            }
        }
    }

    public int minOperations(int n, int m) {
        if (isPrimes[m] || isPrimes[n]) return -1;

        // 从 m 出发开始到 n 的最短路, 做 dijkstra
        int l = 1;
        while (l <= n || l <= m) l *= 10;

        int[] distance = new int[l];
        Arrays.fill(distance, Integer.MAX_VALUE); distance[m] = m;
        // [node, distance]
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(v -> v[1]));
        pq.add(new int[]{m, m});

        while (!pq.isEmpty()) {
            var curr = pq.remove();
            for (var next : move(curr[0])) {
                if (!isPrimes[next]) {
                    if (next + curr[1] < distance[next]) {
                        pq.add(new int[]{next, distance[next] = next + curr[1]});
                    }
                }
            }
        }

        return distance[n] == Integer.MAX_VALUE ? -1 : distance[n];
    }

    private Set<Integer> move(int v) {
        char[] chars = String.valueOf(v).toCharArray();
        Set<Integer> ans = new HashSet<>();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] != '9') {
                chars[i]++; ans.add(Integer.parseInt(new String(chars))); chars[i]--;
            }
            if (chars[i] != '0') {
                chars[i]--; ans.add(Integer.parseInt(new String(chars))); chars[i]++;
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().minOperations(1, 1) == 1;

        assert new Solution().minOperations(10, 12) == 85;
        assert new Solution().minOperations(4, 8) == -1;
        assert new Solution().minOperations(6, 2) == -1;
    }

}
