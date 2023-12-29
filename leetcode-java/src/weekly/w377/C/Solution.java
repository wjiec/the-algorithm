package weekly.w377.C;

import java.util.*;

/**
 * 2976. Minimum Cost to Convert String I
 *
 * https://leetcode.cn/contest/weekly-contest-377/problems/minimum-cost-to-convert-string-i/
 *
 * You are given two 0-indexed strings source and target, both of length n and
 * consisting of lowercase English letters. You are also given two 0-indexed
 * character arrays original and changed, and an integer array cost, where
 * cost[i] represents the cost of changing the character original[i] to the character changed[i].
 *
 * You start with the string source. In one operation, you can pick a character x from
 * the string and change it to the character y at a cost of z if there exists any
 * index j such that cost[j] == z, original[j] == x, and changed[j] == y.
 *
 * Return the minimum cost to convert the string source to the string target using
 * any number of operations. If it is impossible to convert source to target, return -1.
 *
 * Note that there may exist indices i, j such that original[j] == original[i] and changed[j] == changed[i].
 */

public class Solution {

    private final Map<Character, Map<Character, Integer>> graph = new HashMap<>();
    private final long[][] minCost = new long[128][128];
    { for (var row : minCost) Arrays.fill(row, Long.MAX_VALUE); }
    { for (char c = 'a'; c <= 'z'; c++) minCost[c][c] = 0; }

    public long minimumCost(String source, String target, char[] original, char[] changed, int[] cost) {
        Queue<long[]> queue = new ArrayDeque<>();
        for (int i = 0; i < original.length; i++) {
            graph.computeIfAbsent(original[i], v -> new HashMap<>())
                .merge(changed[i], cost[i], Integer::min);
        }

        for (var from : graph.entrySet()) {
            for (var to : from.getValue().entrySet()) {
                minCost[from.getKey()][to.getKey()] = to.getValue();
                queue.add(new long[]{from.getKey(), to.getKey(), to.getValue()});
            }
        }

        while (!queue.isEmpty()) {
            long[] curr = queue.remove(); long currCost = curr[2];
            char from = (char) curr[0], to = (char) curr[1];

            if (graph.containsKey(to)) {
                for (var next : graph.get(to).entrySet()) {
                    long nextCost = currCost + next.getValue();
                    if (nextCost < minCost[from][next.getKey()]) {
                        minCost[from][next.getKey()] = nextCost;
                        queue.add(new long[]{from, next.getKey(), nextCost});
                    }
                }
            }
        }

        long ans = 0;
        for (int i = 0; i < source.length(); i++) {
            char a = source.charAt(i), b = target.charAt(i);
            if (minCost[a][b] == Long.MAX_VALUE) return -1;
            ans += minCost[a][b];
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().minimumCost("abcd", "acbe", new char[]{'a','b','c','c','e','d'}, new char[]{'b','c','b','e','b','e'}, new int[]{2,5,5,1,2,20}) == 28;
    }

}
