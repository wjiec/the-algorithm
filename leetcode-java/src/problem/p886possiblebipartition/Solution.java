package problem.p886possiblebipartition;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 886. Possible Bipartition
 *
 * https://leetcode.cn/problems/possible-bipartition/
 *
 * We want to split a group of n people (labeled from 1 to n) into two groups of any size.
 * Each person may dislike some other people, and they should not go into the same group.
 *
 * Given the integer n and the array dislikes where dislikes[i] = [ai, bi] indicates that
 * the person labeled ai does not like the person labeled bi,
 * return true if it is possible to split everyone into two groups in this way.
 */

public class Solution {

    private final Map<Integer, Integer> colors = new HashMap<>();
    private final Map<Integer, Set<Integer>> edges = new HashMap<>();

    public boolean possibleBipartition(int n, int[][] dislikes) {
        for (var dislike : dislikes) {
            edges.computeIfAbsent(dislike[0], v -> new HashSet<>())
                .add(dislike[1]);
            edges.computeIfAbsent(dislike[1], v -> new HashSet<>())
                .add(dislike[0]);
        }

        for (int node = 1; node <= n; node++) {
            if (!colors.containsKey(node) && !dfs(node, 1)) {
                return false;
            }
        }

        return true;
    }

    private boolean dfs(int node, int color) {
        if (colors.containsKey(node)) {
            return colors.get(node) == color;
        }

        colors.put(node, color);
        if (edges.containsKey(node)) {
            for (int edge : edges.get(node)) {
                if (!dfs(edge, -color)) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        assert new Solution().possibleBipartition(4, new int[][]{{1,2},{1,3},{2,4}});
        assert !new Solution().possibleBipartition(3, new int[][]{{1,2},{1,3},{2,3}});
        assert !new Solution().possibleBipartition(5, new int[][]{{1,2},{2,3},{3,4},{4,5},{1,5}});
    }

}
