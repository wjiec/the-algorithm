package weekly.bw131.C;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

/**
 * 3160. Find the Number of Distinct Colors Among the Balls
 *
 * https://leetcode.cn/contest/biweekly-contest-131/problems/find-the-number-of-distinct-colors-among-the-balls
 *
 * You are given an integer limit and a 2D array queries of size n x 2.
 *
 * There are limit + 1 balls with distinct labels in the range [0, limit]. Initially, all balls are uncolored.
 * For every query in queries that is of the form [x, y], you mark ball x with the color y.
 * After each query, you need to find the number of distinct colors among the balls.
 *
 * Return an array result of length n, where result[i] denotes the number of distinct colors after ith query.
 *
 * Note that when answering a query, lack of a color will not be considered as a color.
 */

public class Solution {

    public int[] queryResults(int limit, int[][] queries) {
        Map<Integer, Integer> bolls = new HashMap<>();
        Map<Integer, Integer> colors = new HashMap<>();

        int[] ans = new int[queries.length];
        BiFunction<Integer, Integer, Integer> sum = (a, b) -> a + b == 0 ? null : a + b;
        for (int i = 0; i < queries.length; i++) {
            int x = queries[i][0], c = queries[i][1];
            if (bolls.containsKey(x)) colors.merge(bolls.get(x), -1, sum);
            bolls.put(x, queries[i][1]); colors.merge(bolls.get(x), 1, sum);
            ans[i] = colors.size();
        }

        return ans;
    }

    public static void main(String[] args) {
    }

}
