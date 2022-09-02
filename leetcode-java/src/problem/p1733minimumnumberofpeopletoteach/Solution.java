package problem.p1733minimumnumberofpeopletoteach;

import common.Tag;

import java.util.HashSet;
import java.util.Set;

/**
 * 1733. Minimum Number of People to Teach
 *
 * https://leetcode.cn/problems/minimum-number-of-people-to-teach/
 *
 * On a social network consisting of m users and some friendships between users, two users can communicate
 * with each other if they know a common language.
 *
 * You are given an integer n, an array languages, and an array friendships where:
 *
 * There are n languages numbered 1 through n,
 * languages[i] is the set of languages the ith user knows, and
 * friendships[i] = [ui, vi] denotes a friendship between the users ui and vi.
 * You can choose one language and teach it to some users so that all friends can communicate
 * with each other. Return the minimum number of users you need to teach.
 *
 * Note that friendships are not transitive, meaning if x is a friend of y and y is a
 * friend of z, this doesn't guarantee that x is a friend of z.
 */

public class Solution {

    private boolean hasCommon(int[][] languages, int a, int b) {
        for (var x : languages[a]) {
            for (var y : languages[b]) {
                if (x == y) return true;
            }
        }
        return false;
    }

    @Tag({"并集", "集合"})
    public int minimumTeachings(int n, int[][] languages, int[][] friendships) {
        int[] languageFreq = new int[n + 1];
        Set<Integer> obstacle = new HashSet<>();
        for (var friendship : friendships) {
            int a = friendship[0] - 1, b = friendship[1] - 1;
            if (!hasCommon(languages, a, b)) {
                if (obstacle.add(a)) {
                    for (var lang : languages[a]) languageFreq[lang]++;
                }
                if (obstacle.add(b)) {
                    for (var lang : languages[b]) languageFreq[lang]++;
                }
            }
        }

        int mostKnow = 0;
        for (var freq : languageFreq) mostKnow = Math.max(mostKnow, freq);
        return obstacle.size() - mostKnow;
    }

    public static void main(String[] args) {
        assert new Solution().minimumTeachings(2, new int[][]{{1},{2},{1,2}}, new int[][]{{1,2},{1,3},{2,3}}) == 1;
        assert new Solution().minimumTeachings(3, new int[][]{{2},{1,3},{1,2},{3}}, new int[][]{{1,4},{1,2},{3,4},{2,3}}) == 2;
    }

}
