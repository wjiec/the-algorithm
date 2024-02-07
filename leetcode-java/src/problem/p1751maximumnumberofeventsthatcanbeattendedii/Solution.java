package problem.p1751maximumnumberofeventsthatcanbeattendedii;

import java.util.*;

/**
 * 1751. Maximum Number of Events That Can Be Attended II
 *
 * https://leetcode.cn/problems/maximum-number-of-events-that-can-be-attended-ii
 *
 * You are given an array of events where events[i] = [startDayi, endDayi, valuei].
 * The ith event starts at startDayi and ends at endDayi, and if you attend
 * this event, you will receive a value of valuei. You are also given an integer k
 * which represents the maximum number of events you can attend.
 *
 * You can only attend one event at a time. If you choose to attend an event, you
 * must attend the entire event. Note that the end day is inclusive: that is, you
 * cannot attend two events where one of them starts and the other ends on the same day.
 *
 * Return the maximum sum of values that you can receive by attending events.
 */

public class Solution {

    private final TreeMap<Integer, Map<Integer, Integer>> m = new TreeMap<>();

    public int maxValue(int[][] events, int k) {
        for (var ev : events) {
            m.computeIfAbsent(ev[0], v -> new HashMap<>())
                .merge(ev[1], ev[2], Integer::max);
        }

        return dfs(0, k);
    }

    private final Map<Long, Integer> memo = new HashMap<>();

    private int dfs(int curr, int remain) {
        Integer next = m.higherKey(curr);
        if (next == null || remain == 0) return 0;

        long key = ((long) next << 32) | remain;
        if (!memo.containsKey(key)) {
            int ans = dfs(next, remain);
            for (var found : m.get(next).entrySet()) {
                ans = Math.max(ans, found.getValue() + dfs(found.getKey(), remain - 1));
            }
            memo.put(key, ans);
        }

        return memo.get(key);
    }

    private static class DynamicProgramming {
        public int maxValue(int[][] events, int k) {
            Arrays.sort(events, Comparator.comparingInt(e -> e[1]));

            int[][] dp = new int[events.length + 1][k + 1];
            for (int i = 0; i < events.length; i++) {
                int found = search(events, i, events[i][0]);
                for (int j = 1; j <= k; j++) {
                    dp[i + 1][j] = Math.max(dp[i][j], dp[found + 1][j - 1] + events[i][2]);
                }
            }
            return dp[events.length][k];
        }

        private int search(int[][] events, int right, int target) {
            int left = -1;
            while (left + 1 < right) {
                int mid = left + (right - left) / 2;
                if (events[mid][1] < target) left = mid;
                else right = mid;
            }
            return left;
        }
    }

    public static void main(String[] args) {
        assert new Solution().maxValue(new int[][]{{1,2,4},{3,4,3},{2,3,1}}, 2) == 7;
        assert new Solution().maxValue(new int[][]{{1,2,4},{3,4,3},{2,3,10}}, 2) == 10;
        assert new Solution().maxValue(new int[][]{{1,1,1},{2,2,2},{3,3,3},{4,4,4}}, 3) == 9;
    }

}
