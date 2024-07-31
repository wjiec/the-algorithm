package problem.p2151maximumgoodpeoplebasedonstatements;

/**
 * 2151. Maximum Good People Based on Statements
 *
 * https://leetcode.cn/problems/maximum-good-people-based-on-statements/description/
 *
 * There are two types of persons:
 *
 * The good person: The person who always tells the truth.
 * The bad person: The person who might tell the truth and might lie.
 * You are given a 0-indexed 2D integer array statements of size n x n that represents the
 * statements made by n people about each other. More specifically, statements[i][j] could be
 * one of the following:
 *
 * 0 which represents a statement made by person i that person j is a bad person.
 * 1 which represents a statement made by person i that person j is a good person.
 * 2 represents that no statement is made by person i about person j.
 *
 * Additionally, no person ever makes a statement about themselves. Formally, we have that
 * statements[i][i] = 2 for all 0 <= i < n.
 *
 * Return the maximum number of people who can be good based on the statements made by the n people.
 */

public class Solution {

    public int maximumGood(int[][] statements) {
        int n = statements.length, ans = 0;
        for (int i = 1; i < 1 << n; i++) {
            if (Integer.bitCount(i) <= ans) continue;

            // i & (1 << j) != 0 表示在该种情况下 j 是好人, 验证是否正确
            boolean[] isGood = new boolean[n];
            for (int j = 0; j < n; j++) {
                isGood[j] = (i & (1 << j)) != 0;
            }

            boolean ok = true;
            for (int j = 0; j < n && ok; j++) {
                for (int k = 0; k < n; k++) {
                    if (isGood[j] && statements[j][k] != 2) {
                        if (isGood[k] != (statements[j][k] == 1)) {
                            ok = false; break;
                        }
                    }
                }
            }

            if (ok) ans = Integer.bitCount(i);
        }

        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().maximumGood(new int[][]{
            {2,1,2},
            {1,2,2},
            {2,0,2},
        }) == 2;
    }

}
