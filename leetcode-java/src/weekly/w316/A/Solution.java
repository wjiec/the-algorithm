package weekly.w316.A;

import ability.Ability;

/**
 * 6214. Determine if Two Events Have Conflict
 *
 * https://leetcode.cn/contest/weekly-contest-316/problems/determine-if-two-events-have-conflict/
 *
 * You are given two arrays of strings that represent two inclusive events that
 * happened on the same day, event1 and event2, where:
 *
 * event1 = [startTime1, endTime1] and
 * event2 = [startTime2, endTime2].
 * Event times are valid 24 hours format in the form of HH:MM.
 *
 * A conflict happens when two events have some non-empty
 * intersection (i.e., some moment is common to both events).
 *
 * Return true if there is a conflict between two events. Otherwise, return false.
 */

public class Solution {

    public boolean haveConflict(String[] event1, String[] event2) {
        int[] e1s = Ability.parseTime(event1[0]);
        int[] e1e = Ability.parseTime(event1[1]);

        int[] e2s = Ability.parseTime(event2[0]);
        int[] e2e = Ability.parseTime(event2[1]);

        int ts1 = e1s[0] * 60 + e1s[1];
        int ts2 = e1e[0] * 60 + e1e[1];

        int ts3 = e2s[0] * 60 + e2s[1];
        int ts4 = e2e[0] * 60 + e2e[1];

        boolean[] visited = new boolean[3601];
        for (int i = ts1; i <= ts2; i++) visited[i] = true;

        for (int i = ts3; i <= ts4; i++) if (visited[i]) return true;
        return false;
    }

    public static void main(String[] args) {
    }

}
