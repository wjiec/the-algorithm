package daily.d210825p797allpathsfromsourcetotarget;

import java.util.ArrayList;
import java.util.List;

/**
 * 797. All Paths From Source to Target
 *
 * https://leetcode-cn.com/problems/all-paths-from-source-to-target/
 *
 * Given a directed acyclic graph (DAG) of n nodes labeled from 0 to n - 1,
 * find all possible paths from node 0 to node n - 1 and return them in any order.
 *
 * The graph is given as follows: graph[i] is a list of all nodes you can visit from node i
 * (i.e., there is a directed edge from node i to node graph[i][j]).
 */

public class Solution {

    private final List<List<Integer>> ans = new ArrayList<>();

    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        dfs(graph, 0, graph.length - 1, new ArrayList<>());
        return ans;
    }

    private void dfs(int[][] graph, int current, int target, List<Integer> path) {
        path.add(current);
        if (current == target) {
            ans.add(new ArrayList<>(path));
            path.remove(path.size() - 1);
            return;
        }

        for (var n : graph[current]) dfs(graph, n, target, path);
        path.remove(path.size() - 1);
    }

    public static void main(String[] args) {
        System.out.println(new Solution().allPathsSourceTarget(new int[][]{
            {1,2}, {3}, {3}, {}
        }));
        System.out.println(new Solution().allPathsSourceTarget(new int[][]{
            {4,3,1}, {3,2,4}, {3}, {4}, {}
        }));
        System.out.println(new Solution().allPathsSourceTarget(new int[][]{
            {1}, {}
        }));
        System.out.println(new Solution().allPathsSourceTarget(new int[][]{
            {1,2,3}, {2}, {3}, {}
        }));
        System.out.println(new Solution().allPathsSourceTarget(new int[][]{
            {1,3}, {2}, {3}, {}
        }));
    }

}
