package weekly.bw158.A;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.TreeMap;

/**
 * Q1. Maximize Y‑Sum by Picking a Triplet of Distinct X‑Values
 *
 * https://leetcode.cn/contest/biweekly-contest-158/problems/maximize-ysum-by-picking-a-triplet-of-distinct-xvalues
 *
 * You are given two integer arrays x and y, each of length n.
 *
 * You must choose three distinct indices i, j, and k such that:
 *
 * x[i] != x[j]
 * x[j] != x[k]
 * x[k] != x[i]
 * Your goal is to maximize the value of y[i] + y[j] + y[k] under these conditions.
 *
 * Return the maximum possible sum that can be obtained by choosing such a triplet of indices.
 * If no such triplet exists, return -1.
 */

public class Solution {

    public int maxSumDistinctTriplet(int[] x, int[] y) {
        TreeMap<Integer, Set<Integer>> m = new TreeMap<>();
        for (int i = 0; i < y.length; i++) {
            m.computeIfAbsent(y[i], k -> new HashSet<>()).add(x[i]);
        }
        return dfs(m, Integer.MAX_VALUE, 3, 0, new HashSet<>());
    }

    private int dfs(TreeMap<Integer, Set<Integer>> m, int last, int remain, int sum, Set<Integer> seen) {
        if (remain == 0) return sum;
        if (m.lowerKey(last) == null) return -1;

        var curr = m.lowerEntry(last);
        Set<Integer> all = new HashSet<>(seen);
        all.addAll(curr.getValue());

        int u = all.size() - seen.size();
        if (u >= remain) return sum + remain * curr.getKey();
        return dfs(m, curr.getKey(), remain - u, sum + u * curr.getKey(), all);
    }

    private static class Optimization {
        public int maxSumDistinctTriplet(int[] x, int[] y) {
            TreeMap<Integer, Integer> m1 = new TreeMap<>();
            for (int i = 0; i < x.length; i++) m1.merge(x[i], y[i], Integer::max);
            if (m1.size() < 3) return -1;

            PriorityQueue<Integer> pq = new PriorityQueue<>();
            for (var v : m1.values()) {
                pq.add(v);
                if (pq.size() > 3) pq.remove();
            }

            int ans = 0;
            while (!pq.isEmpty()) ans += pq.remove();
            return ans;
        }
    }

    public static void main(String[] args) {
    }

}
