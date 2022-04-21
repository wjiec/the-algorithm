package problem.p253meetingroomsii;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 253. Meeting Rooms II
 *
 * https://leetcode-cn.com/problems/meeting-rooms-ii/
 *
 * Given an array of meeting time intervals `intervals` where intervals[i] = [starti, endi],
 * return the minimum number of conference rooms required.
 */

public class Solution {

    public int minMeetingRooms(int[][] intervals) {
        int ans = 0;
        Arrays.sort(intervals, Comparator.comparingInt(v -> v[0]));
        for (int i = 0; i < intervals.length; i++) {
            if (intervals[i] != null) {
                ans++;
                int prev = intervals[i][1];
                for (int j = i + 1; j < intervals.length; j++) {
                    if (intervals[j] != null && intervals[j][0] >= prev) {
                        prev = intervals[j][1];
                        intervals[j] = null;
                    }
                }
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().minMeetingRooms(new int[][]{{0,30},{5,10},{15,20}}) == 2;
        assert new Solution().minMeetingRooms(new int[][]{{7,10},{2,4}}) == 1;
    }

}
