package daily.d211214p630coursescheduleiii;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 630. Course Schedule III
 *
 * https://leetcode-cn.com/problems/course-schedule-iii/
 *
 * There are n different online courses numbered from 1 to n. You are given an array
 * courses where courses[i] = [durationi, lastDayi] indicate that the ith course
 * should be taken continuously for durationi days and must be finished before or on lastDayi.
 *
 * You will start on the 1st day and you cannot take two or more courses simultaneously.
 *
 * Return the maximum number of courses that you can take.
 */

public class Solution {

    public int scheduleCourse(int[][] courses) {
        Arrays.sort(courses, Comparator.comparingInt(v -> v[1]));

        int total = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
        for (var course : courses) {
            int duration = course[0], deadline = course[1];
            if (total + duration <= deadline) {
                total += duration;
                pq.offer(duration);
            } else if (!pq.isEmpty() && pq.peek() > duration) {
                total -= pq.poll() - duration;
                pq.offer(duration);
            }
        }

        return pq.size();
    }

    public static void main(String[] args) {
        assert new Solution().scheduleCourse(new int[][]{{1,2}, {2,3}}) == 2;

        assert new Solution().scheduleCourse(new int[][]{{100,200}, {200,1300}, {1000,1250}, {2000,3200}}) == 3;
        assert new Solution().scheduleCourse(new int[][]{{1,2}}) == 1;
        assert new Solution().scheduleCourse(new int[][]{{3,2},{4,3}}) == 0;
    }

}
