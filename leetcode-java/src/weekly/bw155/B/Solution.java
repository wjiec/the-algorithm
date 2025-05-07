package weekly.bw155.B;

import java.util.HashMap;
import java.util.Map;

/**
 * 3528. Unit Conversion I
 *
 * https://leetcode.cn/contest/biweekly-contest-155/problems/unit-conversion-i/
 *
 * There are n types of units indexed from 0 to n - 1. You are given a 2D integer array
 * conversions of length n - 1, where conversions[i] = [sourceUniti, targetUniti, conversionFactori].
 *
 * This indicates that a single unit of type sourceUniti is equivalent to conversionFactori units of type targetUniti.
 *
 * Return an array baseUnitConversion of length n, where baseUnitConversion[i] is the
 * number of units of type i equivalent to a single unit of type 0. Since the answer
 * may be large, return each baseUnitConversion[i] modulo 1e9 + 7.
 */

public class Solution {

    private final Map<Integer, Map<Integer, Integer>> g = new HashMap<>();

    public int[] baseUnitConversions(int[][] conversions) {
        for (var edge : conversions) {
            g.computeIfAbsent(edge[0], k -> new HashMap<>()).put(edge[1], edge[2]);
        }

        int[] ans = new int[conversions.length + 1];
        dfs(0, 1, ans);
        return ans;
    }

    private static final long MOD = 1_000_000_007;

    private void dfs(int node, long factor, int[] ans) {
        ans[node] = (int) (factor % MOD);
        if (g.containsKey(node)) {
            for (var next : g.get(node).entrySet()) {
                dfs(next.getKey(), (factor * next.getValue()) % MOD, ans);
            }
        }
    }

    public static void main(String[] args) {
    }

}
