package problem.p2440createcomponentswithsamevalue;

import java.util.ArrayList;
import java.util.List;

/**
 * 2440. Create Components With Same Value
 *
 * https://leetcode.cn/problems/create-components-with-same-value
 *
 * There is an undirected tree with n nodes labeled from 0 to n - 1.
 *
 * You are given a 0-indexed integer array nums of length n where nums[i] represents the value of the ith node.
 * You are also given a 2D integer array edges of length n - 1 where edges[i] = [ai, bi] indicates that
 * there is an edge between nodes ai and bi in the tree.
 *
 * You are allowed to delete some edges, splitting the tree into multiple connected components.
 * Let the value of a component be the sum of all nums[i] for which node i is in the component.
 *
 * Return the maximum number of edges you can delete, such that every connected component in the
 * tree has the same value.
 */

public class Solution {

    private int[] nums = null;
    private List<Integer>[] graph = null;

    @SuppressWarnings("unchecked")
    public int componentValue(int[] nums, int[][] edges) {
        this.nums = nums;
        graph = new List[nums.length];
        for (int i = 0; i < graph.length; i++) {
            graph[i] = new ArrayList<>();
        }

        for (var edge : edges) {
            graph[edge[0]].add(edge[1]);
            graph[edge[1]].add(edge[0]);
        }

        int total = 0, max = 0;
        for (var v : nums) {
            total += v;
            max = Math.max(max, v);
        }

        for (int p = total / max; p > 0; p--) {
            if (total % p == 0) {
                if (dfs(0, -1, total / p) == 0) {
                    return p - 1;
                }
            }
        }
        return -1; // unreached
    }

    private int dfs(int curr, int parent, int target) {
        int sum = nums[curr];
        for (var next : graph[curr]) {
            if (next != parent) {
                int ans = dfs(next, curr, target);
                if (ans < 0) return -1;
                sum += ans;
            }
        }

        if (sum > target) return -1;
        return sum % target;
    }

    public static void main(String[] args) {
    }

}
