package problem.p1792maximumaveragepassratio;

import common.Checker;

import java.util.PriorityQueue;

/**
 * 1792. Maximum Average Pass Ratio
 *
 * https://leetcode.cn/problems/maximum-average-pass-ratio/
 *
 * There is a school that has classes of students and each class will be having a final exam.
 * You are given a 2D integer array classes, where classes[i] = [passi, totali].
 * You know beforehand that in the ith class, there are totali total students, but
 * only passi number of students will pass the exam.
 *
 * You are also given an integer extraStudents. There are another extraStudents
 * brilliant students that are guaranteed to pass the exam of any class they are
 * assigned to. You want to assign each of the extraStudents students to a class
 * in a way that maximizes the average pass ratio across all the classes.
 *
 * The pass ratio of a class is equal to the number of students of the class that will
 * pass the exam divided by the total number of students of the class.
 * The average pass ratio is the sum of pass ratios of all the classes divided by
 * the number of the classes.
 *
 * Return the maximum possible average pass ratio after assigning the extraStudents
 * students. Answers within 10-5 of the actual answer will be accepted.
 */

public class Solution {

    public double maxAverageRatio(int[][] classes, int extraStudents) {
        // 按照每个班级新增一名通过人员对总体通过率的大小
        PriorityQueue<double[]> pq = new PriorityQueue<>((a, b) -> {
            double da = (a[0] + 1) / (a[1] + 1) - a[0] / a[1];
            double db = (b[0] + 1) / (b[1] + 1) - b[0] / b[1];
            return Double.compare(db, da);
        });
        for (var c : classes) pq.add(new double[]{c[0], c[1]});

        for (; extraStudents > 0; extraStudents--) {
            double[] curr = pq.remove();
            curr[0]++; curr[1]++;
            pq.add(curr);
        }

        double total = 0;
        while (!pq.isEmpty()) {
            double[] curr = pq.remove();
            total += curr[0] / curr[1];
        }
        return total / classes.length;
    }

    public static void main(String[] args) {
        assert Checker.check(new Solution().maxAverageRatio(new int[][]{{1,2},{3,5},{2,2}}, 2), 0.78333);
        assert Checker.check(new Solution().maxAverageRatio(new int[][]{{2,4},{3,9},{4,5},{2,10}}, 4), 0.53485);
    }

}
