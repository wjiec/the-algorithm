package problem.p2054twobestnonoverlappingevents;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 2054. Two Best Non-Overlapping Events
 *
 * https://leetcode.cn/problems/two-best-non-overlapping-events/
 *
 * You are given a 0-indexed 2D integer array of events where events[i] = [startTimei, endTimei, valuei].
 * The ith event starts at startTimei and ends at endTimei, and if you attend this event, you will
 * receive a value of valuei. You can choose at most two non-overlapping events to attend such that
 * the sum of their values is maximized.
 *
 * Return this maximum sum.
 *
 * Note that the start time and end time is inclusive: that is, you cannot attend two events where
 * one of them starts and the other ends at the same time.
 *
 * More specifically, if you attend an event with end time t, the next event must start at or after t + 1.
 */

public class Solution {

    public int maxTwoEvents(int[][] events) {
        Arrays.sort(events, Comparator.comparingInt(e -> e[0]));
        PriorityQueue<int[]> ends = new PriorityQueue<>(Comparator.comparingInt(e -> e[1]));

        int ans = 0, max = 0;
        for (var event : events) {
            while (!ends.isEmpty() && ends.peek()[1] < event[0]) {
                max = Math.max(max, ends.remove()[2]);
            }

            ans = Math.max(ans, event[2] + max);
            ends.add(event);
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().maxTwoEvents(new int[][]{{10,83,53},{63,87,45},{97,100,32},{51,61,16}}) == 85;

        assert new Solution().maxTwoEvents(new int[][]{{1,3,2},{4,5,2},{2,4,3}}) == 4;
        assert new Solution().maxTwoEvents(new int[][]{{1,3,2},{4,5,2},{1,5,5}}) == 5;
        assert new Solution().maxTwoEvents(new int[][]{{1,5,3},{1,5,1},{6,6,5}}) == 8;
    }

}
