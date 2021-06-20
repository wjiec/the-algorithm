package problem.p997findthetownjudge;

/**
 * 997. Find the Town Judge
 *
 * https://leetcode-cn.com/problems/find-the-town-judge/
 *
 * In a town, there are n people labelled from 1 to n.
 * There is a rumor that one of these people is secretly the town judge.
 *
 * If the town judge exists, then:
 *
 * The town judge trusts nobody.
 * Everybody (except for the town judge) trusts the town judge.
 * There is exactly one person that satisfies properties 1 and 2.
 * You are given trust, an array of pairs trust[i] = [a, b]
 * representing that the person labelled a trusts the person labelled b.
 *
 * If the town judge exists and can be identified, return the label of the town judge.  Otherwise, return -1.
 */

public class Solution {

    public int findJudge(int n, int[][] trust) {
        int[][] people = new int[n + 1][2]; // 0-被几个人信任 1-信任几个人
        for (int[] chain : trust) {
            people[chain[1]][0]++;
            people[chain[0]][1]++;
        }

        for (int i = 1; i <= n; i++) {
            if (people[i][0] == n - 1 && people[i][1] == 0) return i;
        }
        return -1;
    }

    public static void main(String[] args) {
        assert new Solution().findJudge(2, new int[][]{{1,2}}) == 2;
        assert new Solution().findJudge(3, new int[][]{{1,3}, {2,3}}) == 3;
        assert new Solution().findJudge(3, new int[][]{{1,3}, {2,3}, {3,1}}) == -1;
        assert new Solution().findJudge(3, new int[][]{{1,2}, {2,3}}) == -1;
        assert new Solution().findJudge(4, new int[][]{{1,3}, {1,4}, {2,3}, {2,4}, {4,3}}) == 3;
    }

}
