package problem.p547numberofprovinces;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 547. Number of Provinces
 *
 * https://leetcode-cn.com/problems/number-of-provinces/
 *
 * There are n cities. Some of them are connected, while some are not. If city a is connected directly with city b,
 * and city b is connected directly with city c, then city a is connected indirectly with city c.
 *
 * A province is a group of directly or indirectly connected cities and no other cities outside of the group.
 *
 * You are given an n x n matrix isConnected where isConnected[i][j] = 1 if the ith city and the jth city
 * are directly connected, and isConnected[i][j] = 0 otherwise.
 *
 * Return the total number of provinces.
 */

public class Solution {

    public int findCircleNum(int[][] isConnected) {
        int n = isConnected.length, ans = 0;
        boolean[] visited = new boolean[n];
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                ans++;

                Queue<Integer> queue = new ArrayDeque<>();
                for (queue.add(i); !queue.isEmpty(); ) {
                    int curr = queue.remove();
                    for (int j = 0; j < n; j++) {
                        if (!visited[j] && isConnected[curr][j] == 1) {
                            queue.add(j);
                            visited[j] = true;
                        }
                    }
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().findCircleNum(new int[][]{{1,0,0,1},{0,1,1,0},{0,1,1,1},{1,0,1,1}}) == 1;

        assert new Solution().findCircleNum(new int[][]{{1,1,0},{1,1,0},{0,0,1}}) == 2;
        assert new Solution().findCircleNum(new int[][]{{1,0,0},{0,1,0},{0,0,1}}) == 3;
    }

}
