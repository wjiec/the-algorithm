package problem.p1353maximumnumberofeventsthatcanbeattended;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 1353. Maximum Number of Events That Can Be Attended
 *
 * https://leetcode.cn/problems/maximum-number-of-events-that-can-be-attended/
 *
 * You are given an array of events where events[i] = [startDayi, endDayi].
 * Every event i starts at startDayi and ends at endDayi.
 *
 * You can attend an event i at any day d where startTimei <= d <= endTimei.
 * You can only attend one event at any time d.
 *
 * Return the maximum number of events you can attend.
 */

public class Solution {

    public int maxEvents(int[][] events) {
        Arrays.sort(events, Comparator.comparingInt(v -> v[0]));

        int ans = 0, n = events.length;
        PriorityQueue<Integer> q = new PriorityQueue<>();
        for (int i = 0, d = 1; i < n || !q.isEmpty(); d++) {
            // 开始时间是当前日的会议
            while (i < n && events[i][0] == d) {
                q.add(events[i++][1]);
            }
            // 删除所有过期的会议
            while (!q.isEmpty() && q.peek() < d) q.remove();
            // 如果有会议，就参加最小的
            if (!q.isEmpty()) { q.remove(); ans++; }
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().maxEvents(new int[][]{{1,2},{2,3},{3,4}}) == 3;
        assert new Solution().maxEvents(new int[][]{{1,2},{2,3},{3,4},{1,2}}) == 4;
    }

}
