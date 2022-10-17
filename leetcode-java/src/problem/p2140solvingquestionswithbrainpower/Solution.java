package problem.p2140solvingquestionswithbrainpower;

/**
 * 2140. Solving Questions With Brainpower
 *
 * https://leetcode.cn/problems/solving-questions-with-brainpower/
 *
 * You are given a 0-indexed 2D integer array questions where questions[i] = [pointsi, brainpoweri].
 *
 * The array describes the questions of an exam, where you have to process the questions in order
 * (i.e., starting from question 0) and make a decision whether to solve or skip each question.
 * Solving question i will earn you pointsi points but you will be unable to solve each of the next
 * brainpoweri questions. If you skip question i, you get to make the decision on the next question.
 *
 * For example, given questions = [[3, 2], [4, 3], [4, 4], [2, 5]]:
 * If question 0 is solved, you will earn 3 points but you will be unable to solve questions 1 and 2.
 * If instead, question 0 is skipped and question 1 is solved, you will earn 4 points but
 * you will be unable to solve questions 2 and 3.
 *
 * Return the maximum points you can earn for the exam.
 */

public class Solution {

    public long mostPoints(int[][] questions) {
        int n = questions.length;
        long[] dp = new long[n];
        for (int i = n - 1; i >= 0; i--) {
            dp[i] = Math.max(i == n - 1 ? 0 : dp[i + 1], dp[i] + questions[i][0]);

            int next = i + questions[i][1] + 1;
            if (next < n) {
                dp[i] = Math.max(dp[i], dp[next] + questions[i][0]);
            }
        }

        return dp[0];
    }

    public static void main(String[] args) {
        assert new Solution().mostPoints(new int[][]{
            {21,2},{1,2},{12,5},{7,2},{35,3},{32,2},{80,2},
            {91,5},{92,3},{27,3},{19,1},{37,3},{85,2},{33,4},
            {25,1},{91,4},{44,3},{93,3},{65,4},{82,3},{85,5},
            {81,3},{29,2},{25,1},{74,2},{58,1},{85,1},{84,2},
            {27,2},{47,5},{48,4},{3,2},{44,3},{60,5},{19,2},
            {9,4},{29,5},{15,3},{1,3},{60,2},{63,3},{79,3},
            {19,1},{7,1},{35,1},{55,4},{1,4},{41,1},{58,5}
        }) == 781;

        assert new Solution().mostPoints(new int[][]{{3,2},{4,3},{4,4},{2,5}}) == 5L;
        assert new Solution().mostPoints(new int[][]{{1,1},{2,2},{3,3},{4,4},{5,5}}) == 7;
    }

}
