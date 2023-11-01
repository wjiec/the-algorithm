package problem.p2127maximumemployeestobeinvitedtoameeting;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

/**
 * 2127. Maximum Employees to Be Invited to a Meeting
 *
 * https://leetcode.cn/problems/maximum-employees-to-be-invited-to-a-meeting
 *
 * A company is organizing a meeting and has a list of n employees, waiting to be invited.
 *
 * They have arranged for a large circular table, capable of seating any number of employees.
 *
 * The employees are numbered from 0 to n - 1. Each employee has a favorite person and they
 * will attend the meeting only if they can sit next to their favorite person at the table.
 *
 * The favorite person of an employee is not themself.
 *
 * Given a 0-indexed integer array favorite, where favorite[i] denotes the favorite
 * person of the ith employee, return the maximum number of employees that can be
 * invited to the meeting.
 */

public class Solution {

    public int maximumInvitations(int[] favorite) {
        int[] inDegree = new int[favorite.length];
        for (int j : favorite) inDegree[j]++;

        int[] depth = new int[favorite.length];
        boolean[] visited = new boolean[favorite.length];
        Queue<Integer> queue = new ArrayDeque<>();

        Arrays.fill(depth, 1);
        for (int i = 0; i < inDegree.length; i++) {
            if (inDegree[i] == 0) queue.add(i);
        }

        while (!queue.isEmpty()) {
            int curr = queue.remove();
            visited[curr] = true;

            int next = favorite[curr];
            depth[next] = Math.max(depth[next], depth[curr] + 1);
            if (--inDegree[next] == 0) queue.add(next);
        }

        int circle = 0, maxLength = 0;
        for (int i = 0; i < favorite.length; i++) {
            if (!visited[i]) {
                int next = favorite[i];
                if (favorite[next] == i) {
                    maxLength += depth[i] + depth[next];
                    visited[i] = visited[next] = true;
                } else {
                    int curr = i, len = 0;
                    do {
                        len++;
                        curr = favorite[curr];
                        visited[curr] = true;
                    } while (curr != i);
                    circle = Math.max(circle, len);
                }
            }
        }
        return Math.max(circle, maxLength);
    }

    public static void main(String[] args) {
        assert new Solution().maximumInvitations(new int[]{2,2,1,2}) == 3;
        assert new Solution().maximumInvitations(new int[]{1,2,0}) == 3;
        assert new Solution().maximumInvitations(new int[]{3,0,1,4,1}) == 4;
    }

}
