package problem.p252meetingrooms;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 252. Meeting Rooms
 *
 * https://leetcode-cn.com/problems/meeting-rooms/
 *
 * Given an array of meeting time intervals where intervals[i] = [starti, endi],
 * determine if a person could attend all meetings.
 */

public class Solution {

    public boolean canAttendMeetings(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparingInt(v -> v[0]));
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] < intervals[i - 1][1]) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        assert !new Solution().canAttendMeetings(new int[][]{
            {0,30},
            {5,10},
            {15,20}
        });

        assert new Solution().canAttendMeetings(new int[][]{
            {7,10},
            {2,4},
        });
    }

}
