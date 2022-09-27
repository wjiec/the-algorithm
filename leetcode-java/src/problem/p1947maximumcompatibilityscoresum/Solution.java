package problem.p1947maximumcompatibilityscoresum;

import java.util.Arrays;

/**
 * 1947. Maximum Compatibility Score Sum
 *
 * https://leetcode.cn/problems/maximum-compatibility-score-sum/
 *
 * There is a survey that consists of n questions where each question's answer is either 0 (no) or 1 (yes).
 *
 * The survey was given to m students numbered from 0 to m - 1 and m mentors numbered from 0 to m - 1.
 * The answers of the students are represented by a 2D integer array students where students[i] is an integer
 * array that contains the answers of the ith student (0-indexed).
 *
 * The answers of the mentors are represented by a 2D integer array mentors where mentors[j] is an integer
 * array that contains the answers of the jth mentor (0-indexed).
 *
 * Each student will be assigned to one mentor, and each mentor will have one student assigned to them.
 * The compatibility score of a student-mentor pair is the number of answers that are the same for both
 * the student and the mentor.
 *
 * For example, if the student's answers were [1, 0, 1] and the mentor's answers were [0, 0, 1], then
 * their compatibility score is 2 because only the second and the third answers are the same.
 *
 * You are tasked with finding the optimal student-mentor pairings to maximize the sum of the compatibility scores.
 *
 * Given students and mentors, return the maximum compatibility score sum that can be achieved.
 */

public class Solution {

    private int ans = 0;
    private int[][] students;
    private int[][] mentors;
    private final int[][] memo = new int[9][9];

    public int maxCompatibilitySum(int[][] students, int[][] mentors) {
        this.students = students; this.mentors = mentors;
        for (int[] m : memo) Arrays.fill(m, -1);
        dfs(0, 0);
        return ans;
    }

    private void dfs(int idx, int sum) {
        if (idx == students.length) {
            ans = Math.max(ans, sum);
            return;
        }

        for (int i = 0; i < mentors.length; i++) {
            if (mentors[i] != null) {
                int[] temp = mentors[i];
                int s = sum + score(idx, i);
                mentors[i] = null;
                dfs(idx + 1, s);
                mentors[i] = temp;
            }
        }
    }

    private int score(int s, int m) {
        if (memo[s][m] != -1) return memo[s][m];

        int ans = 0, n = students[s].length;
        for (int i = 0; i < n; i++) {
            if (students[s][i] == mentors[m][i]) ans++;
        }
        memo[s][m] = ans;
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().maxCompatibilitySum(new int[][]{{1,1,0},{1,0,1},{0,0,1}}, new int[][]{{1,0,0},{0,0,1},{1,1,0}}) == 8;
        assert new Solution().maxCompatibilitySum(new int[][]{{0,0},{0,0},{0,0}}, new int[][]{{1,1},{1,1},{1,1}}) == 0;
    }

}
