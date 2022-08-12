package problem.p1466reorderroutestomakeallpathsleadtothecityzero;

import java.util.*;

/**
 * 1466. Reorder Routes to Make All Paths Lead to the City Zero
 *
 * https://leetcode.cn/problems/reorder-routes-to-make-all-paths-lead-to-the-city-zero/
 *
 * There are n cities numbered from 0 to n - 1 and n - 1 roads such that there is only one
 * way to travel between two different cities (this network form a tree). Last year, The ministry of
 * transport decided to orient the roads in one direction because they are too narrow.
 *
 * Roads are represented by connections where connections[i] = [ai, bi] represents a road from city ai to city bi.
 *
 * This year, there will be a big event in the capital (city 0), and many people want to travel to this city.
 *
 * Your task consists of reorienting some roads such that each city can visit the city 0.
 * Return the minimum number of edges changed.
 *
 * It's guaranteed that each city can reach city 0 after reorder.
 */

@SuppressWarnings("DuplicatedCode")
public class Solution {

    public int minReorder(int n, int[][] connections) {
        int ans = 0, BACK = 1 << 24, MASK = BACK - 1;
        Map<Integer, Set<Integer>> map = new HashMap<>();
        for (var connection : connections) {
            map.computeIfAbsent(connection[0], v -> new HashSet<>())
                .add(connection[1]);
            map.computeIfAbsent(connection[1], v -> new HashSet<>())
                .add(connection[0] | BACK);
        }

        boolean[] visited = new boolean[n];
        Queue<Integer> queue = new ArrayDeque<>();
        visited[0] = true; queue.add(0);

        while (!queue.isEmpty()) {
            int curr = queue.remove();
            for (var next : map.get(curr)) {
                int real = next & MASK;
                if (!visited[real]) {
                    if ((next & BACK) == 0) ans++;
                    visited[real] = true;
                    queue.add(real);
                }
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().minReorder(6, new int[][]{{0,1},{1,3},{2,3},{4,0},{4,5}}) == 3;
        assert new Solution().minReorder(5, new int[][]{{1,0},{1,2},{3,2},{3,4}}) == 2;
        assert new Solution().minReorder(3, new int[][]{{1,0},{2,0}}) == 0;
    }

}
