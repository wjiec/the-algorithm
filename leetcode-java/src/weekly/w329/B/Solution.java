package weekly.w329.B;

import java.util.Arrays;

/**
 * 2545. Sort the Students by Their Kth Score
 *
 * https://leetcode.cn/problems/sort-the-students-by-their-kth-score/
 *
 * There is a class with m students and n exams. You are given a 0-indexed m x n integer
 * matrix score, where each row represents one student and score[i][j] denotes the score
 * the ith student got in the jth exam. The matrix score contains distinct integers only.
 *
 * You are also given an integer k. Sort the students (i.e., the rows of the matrix)
 * by their scores in the kth (0-indexed) exam from the highest to the lowest.
 *
 * Return the matrix after sorting it.
 */

public class Solution {

    public int[][] sortTheStudents(int[][] score, int k) {
        Arrays.sort(score, (a, b) -> b[k] - a[k]);
        return score;
    }

    public static void main(String[] args) {
    }

}
