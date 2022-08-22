package problem.p1583countunhappyfriends;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 1583. Count Unhappy Friends
 *
 * https://leetcode.cn/problems/count-unhappy-friends/
 *
 * You are given a list of preferences for n friends, where n is always even.
 *
 * For each person i, preferences[i] contains a list of friends sorted in the order of preference.
 * In other words, a friend earlier in the list is more preferred than a friend later in the list.
 * Friends in each list are denoted by integers from 0 to n-1.
 *
 * All the friends are divided into pairs. The pairings are given in a list pairs, where
 * pairs[i] = [xi, yi] denotes xi is paired with yi and yi is paired with xi.
 *
 * However, this pairing may cause some of the friends to be unhappy. A friend x is unhappy
 * if x is paired with y and there exists a friend u who is paired with v but:
 *
 * x prefers u over y, and
 * u prefers x over v.
 * Return the number of unhappy friends.
 */

@SuppressWarnings("SuspiciousNameCombination")
public class Solution {

    private final Map<Integer, Integer> map = new HashMap<>();

    public int unhappyFriends(int n, int[][] preferences, int[][] pairs) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n - 1; j++) {
                map.put(i << 16 | preferences[i][j], n - j);
            }
        }

        Set<Integer> unhappy = new HashSet<>();
        for (int i = 0; i < pairs.length; i++) {
            int x = pairs[i][0], y = pairs[i][1];
            for (int j = 0; j < pairs.length; j++) {
                if (i != j) {
                    int u = pairs[j][0], v = pairs[j][1];
                    if (unhappy(x, y, u, v)) unhappy.add(x);
                    if (unhappy(y, x, u, v)) unhappy.add(y);
                    if (unhappy(x, y, v, u)) unhappy.add(x);
                    if (unhappy(y, x, v, u)) unhappy.add(y);
                }
            }
        }
        return unhappy.size();
    }

    private boolean unhappy(int x, int y, int u, int v) {
        return map.get(x << 16 | u) > map.get(x << 16 | y) &&
            map.get(u << 16 | x) > map.get(u << 16 | v);
    }

    private static class FastArray {
        public int unhappyFriends(int n, int[][] preferences, int[][] pairs) {
            int[][] friends = new int[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n - 1; j++) {
                    friends[i][preferences[i][j]] = j;
                }
            }

            int[] matches = new int[n];
            for (var pair : pairs) {
                matches[pair[0]] = pair[1];
                matches[pair[1]] = pair[0];
            }

            int ans = 0;
            for (int x = 0; x < n; x++) {
                int y = matches[x];
                for (int i = 0; i < friends[x][y]; i++) {
                    int u = preferences[x][i];
                    int v = matches[u];
                    if (friends[u][x] < friends[u][v]) {
                        ans++;
                        break;
                    }
                }
            }
            return ans;
        }
    }

    public static void main(String[] args) {
        assert new Solution().unhappyFriends(4, new int[][]{
            {1, 2, 3}, {3, 2, 0}, {3, 1, 0}, {1, 2, 0}
        }, new int[][]{{0, 1}, {2, 3}}) == 2;
        assert new Solution().unhappyFriends(2, new int[][]{
            {1}, {0}
        }, new int[][]{{1, 0}}) == 0;
        assert new Solution().unhappyFriends(4, new int[][]{
            {1, 3, 2}, {2, 3, 0}, {1, 3, 0}, {0, 2, 1}
        }, new int[][]{{1, 3}, {0, 2}}) == 4;

        assert new FastArray().unhappyFriends(4, new int[][]{
            {1, 2, 3}, {3, 2, 0}, {3, 1, 0}, {1, 2, 0}
        }, new int[][]{{0, 1}, {2, 3}}) == 2;
        assert new FastArray().unhappyFriends(2, new int[][]{
            {1}, {0}
        }, new int[][]{{1, 0}}) == 0;
        assert new FastArray().unhappyFriends(4, new int[][]{
            {1, 3, 2}, {2, 3, 0}, {1, 3, 0}, {0, 2, 1}
        }, new int[][]{{1, 3}, {0, 2}}) == 4;
    }

}
