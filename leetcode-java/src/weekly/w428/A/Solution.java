package weekly.w428.A;

/**
 * 3386. Button with Longest Push Time
 *
 * https://leetcode.cn/contest/weekly-contest-428/problems/button-with-longest-push-time/
 *
 * You are given a 2D array events which represents a sequence of events
 * where a child pushes a series of buttons on a keyboard.
 *
 * Each events[i] = [indexi, timei] indicates that the button at index indexi was pressed at time timei.
 *
 * The array is sorted in increasing order of time.
 * The time taken to press a button is the difference in time between consecutive button presses.
 * The time for the first button is simply the time at which it was pressed.
 *
 * Return the index of the button that took the longest time to push. If multiple buttons have the
 * same longest time, return the button with the smallest index.
 */

public class Solution {

    public int buttonWithLongestTime(int[][] events) {
        int ans = events[0][0], maxTime = events[0][1];
        for (int i = 1; i < events.length; i++) {
            if (events[i][1] - events[i - 1][1] > maxTime) {
                maxTime = events[i][1] - events[i - 1][1];
                ans = events[i][0];
            } else if (events[i][1] - events[i - 1][1] == maxTime && events[i][0] < ans) {
                maxTime = events[i][1] - events[i - 1][1];
                ans = events[i][0];
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().buttonWithLongestTime(new int[][]{{9, 4}, {19, 5}, {2, 8}, {3, 11}, {2, 15}}) == 2;
    }

}
