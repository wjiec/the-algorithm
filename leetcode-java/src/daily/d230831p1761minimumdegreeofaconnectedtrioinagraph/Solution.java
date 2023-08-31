package daily.d230831p1761minimumdegreeofaconnectedtrioinagraph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 1761. Minimum Degree of a Connected Trio in a Graph
 *
 * https://leetcode.cn/problems/minimum-degree-of-a-connected-trio-in-a-graph/
 *
 * You are given an undirected graph. You are given an integer n which is the number of nodes
 * in the graph and an array edges, where each edges[i] = [ui, vi] indicates that there is an
 * undirected edge between ui and vi.
 *
 * A connected trio is a set of three nodes where there is an edge between every pair of them.
 *
 * The degree of a connected trio is the number of edges where one endpoint is in the trio, and the other is not.
 *
 * Return the minimum degree of a connected trio in the graph, or -1 if the graph has no connected trios.
 */

public class Solution {

    @SuppressWarnings("unchecked")
    public int minTrioDegree(int n, int[][] edges) {
        int[] d = new int[n];
        boolean[][] g = new boolean[n][n];
        for (var edge : edges) {
            int a = edge[0] - 1, b = edge[1] - 1;
            g[a][b] = g[b][a] = true;
            ++d[a]; ++d[b];
        }

        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (g[i][j]) {
                    for (int k = j + 1; k < n; k++) {
                        if (g[i][k] && g[j][k]) {
                            ans = Math.min(ans, d[i] + d[j] + d[k] - 6);
                        }
                    }
                }
            }
        }

        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    public static void main(String[] args) {
    }

}
