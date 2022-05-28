package weekly.bw79.C;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * 6085. Maximum Total Importance of Roads
 *
 * https://leetcode.cn/contest/biweekly-contest-79/problems/maximum-total-importance-of-roads/
 *
 * You are given an integer n denoting the number of cities in a country. The cities are numbered from 0 to n - 1.
 *
 * You are also given a 2D integer array roads where roads[i] = [ai, bi] denotes that
 * there exists a bidirectional road connecting cities ai and bi.
 *
 * You need to assign each city with an integer value from 1 to n, where each value can only be used once.
 * The importance of a road is then defined as the sum of the values of the two cities it connects.
 *
 * Return the maximum total importance of all roads possible after assigning the values optimally.
 */

public class Solution {

    public long maximumImportance(int n, int[][] roads) {
        Map<Integer, Integer> weights = new HashMap<>();
        for (var road : roads) {
            weights.merge(road[0], 1, Integer::sum);
            weights.merge(road[1], 1, Integer::sum);
        }

        PriorityQueue<Map.Entry<Integer, Integer>> queue = new PriorityQueue<>((a, b) -> b.getValue().compareTo(a.getValue()));
        queue.addAll(weights.entrySet());

        int[] importance = new int[n];
        while (!queue.isEmpty()) {
            importance[queue.remove().getKey()] = n--;
        }
        for (int i = importance.length - 1; i >= 0; i--) {
            if (importance[i] == 0) importance[i] = n--;
        }

        long ans = 0;
        for (var road : roads) {
            ans += importance[road[0]] + importance[road[1]];
        }
        return ans;
    }

    public static void main(String[] args) {
    }

}
