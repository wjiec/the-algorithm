package problem.p1450numberofstudentsdoinghomeworkatagiventime;

/**
 * 1450. Number of Students Doing Homework at a Given Time
 *
 * https://leetcode-cn.com/problems/number-of-students-doing-homework-at-a-given-time/
 *
 * Given two integer arrays startTime and endTime and given an integer queryTime.
 *
 * The ith student started doing their homework at the time startTime[i] and finished it at time endTime[i].
 *
 * Return the number of students doing their homework at time queryTime.
 * More formally, return the number of students where queryTime lays
 * in the interval [startTime[i], endTime[i]] inclusive.
 */

public class Solution {

    public int busyStudent(int[] startTime, int[] endTime, int queryTime) {
        int ans = 0;
        for (int i = 0, l = startTime.length; i < l; i++) {
            if (startTime[i] <= queryTime && queryTime <= endTime[i]) {
                ans++;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().busyStudent(new int[]{1,2,3}, new int[]{3,2,7}, 4) == 1;
        assert new Solution().busyStudent(new int[]{4}, new int[]{4}, 4) == 1;
        assert new Solution().busyStudent(new int[]{4}, new int[]{4}, 5) == 0;
        assert new Solution().busyStudent(new int[]{1,1,1,1}, new int[]{1,3,2,4}, 7) == 0;
        assert new Solution().busyStudent(new int[]{9,8,7,6,5,4,3,2,1}, new int[]{10,10,10,10,10,10,10,10,10}, 5) == 5;
    }

}
