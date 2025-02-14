package weekly.bw149.C;

import common.PrettyPrinter;

import java.util.TreeMap;

/**
 * 3440. Reschedule Meetings for Maximum Free Time II
 *
 * https://leetcode.cn/contest/biweekly-contest-149/problems/reschedule-meetings-for-maximum-free-time-ii/
 *
 * You are given an integer eventTime denoting the duration of an event.
 * You are also given two integer arrays startTime and endTime, each of length n.
 *
 * These represent the start and end times of n non-overlapping meetings that occur
 * during the event between time t = 0 and time t = eventTime, where the ith meeting
 * occurs during the time [startTime[i], endTime[i]].
 *
 * You can reschedule at most one meeting by moving its start time while maintaining
 * the same duration, such that the meetings remain non-overlapping, to maximize the
 * longest continuous period of free time during the event.
 *
 * Return the maximum amount of free time possible after rearranging the meetings.
 *
 * Note that the meetings can not be rescheduled to a time outside the
 * event and they should remain non-overlapping.
 *
 * Note: In this version, it is valid for the relative ordering of the meetings
 * to change after rescheduling one meeting.
 */

public class Solution {

    public int maxFreeTime(int eventTime, int[] startTime, int[] endTime) {
        int n = startTime.length;
        TreeMap<Integer, Integer> gaps = new TreeMap<>();
        for (int i = 0; i <= n; i++) {
            gaps.merge((i == n ? eventTime : startTime[i]) - (i == 0 ? 0 : endTime[i - 1]), 1, Integer::sum);
        }

        PrettyPrinter.println(gaps);

        // 不需要保持相对顺序, 但是只能移动一个
        //  - 找到所有的空隙, 使得能放下当前这个会议
        int ans = gaps.lastKey();
        for (int i = 0; i < n; i++) {
            int prevEnd = i == 0 ? 0 : endTime[i - 1];
            int currStart = startTime[i], currEnd = endTime[i];
            int nextStart = i + 1 == n ? eventTime : startTime[i + 1];

            // 是否能找到一个大于等于当前会议的 gap, 但是不能放在当前左右 gap 里
            gaps.merge(currStart - prevEnd, -1, Solution::sum);
            gaps.merge(nextStart - currEnd, -1, Solution::sum);
            // 是否能放在其他地方
            if (gaps.ceilingKey(currEnd - currStart) != null) ans = Math.max(ans, nextStart - prevEnd);
            // 否则只能放在当前 gap 里
            ans = Math.max(ans, nextStart - prevEnd - (currEnd - currStart));

            // 恢复现场
            gaps.merge(currStart - prevEnd, 1, Solution::sum);
            gaps.merge(nextStart - currEnd, 1, Solution::sum);
        }

        return ans;
    }

    private static Integer sum(Integer a, Integer b) { return a + b == 0 ? null : a + b; }

    public static void main(String[] args) {
        assert new Solution().maxFreeTime(34, new int[]{0, 17}, new int[]{14, 19}) == 18;
    }

}
