package weekly.bw148.C;

import common.Checker;

import java.util.*;

/**
 * 3425. Longest Special Path
 *
 * https://leetcode.cn/contest/biweekly-contest-148/problems/longest-special-path/
 *
 * You are given an undirected tree rooted at node 0 with n nodes numbered from 0 to n - 1, represented
 * by a 2D array edges of length n - 1, where edges[i] = [ui, vi, lengthi] indicates an edge between
 * nodes ui and vi with length lengthi. You are also given an integer array nums, where nums[i] represents
 * the value at node i.
 *
 * A special path is defined as a downward path from an ancestor node to a descendant node such that all
 * the values of the nodes in that path are unique.
 *
 * Note that a path may start and end at the same node.
 *
 * Return an array result of size 2, where result[0] is the length of the longest
 * special path, and result[1] is the minimum number of nodes in all possible
 * longest special paths.
 */

/** @noinspection unchecked*/
public class Solution {

    private Map<Integer, Integer>[] g;
    private int[] next = null, sum = null, depth = null, values = null;

    public int[] longestSpecialPath(int[][] edges, int[] nums) {
        values = nums;
        next = new int[nums.length];
        depth = new int[nums.length];
        sum = new int[nums.length];

        g = new Map[nums.length];
        Arrays.setAll(g, i -> new HashMap<>());
        for (var edge : edges) {
            g[edge[0]].put(edge[1], edge[2]);
            g[edge[1]].put(edge[0], edge[2]);
        }

        dfs(0, -1, 0);
        return new int[]{maxLength, minimumNodes};
    }

    private int maxLength = 0, minimumNodes = 1;
    private final Map<Integer, Deque<Integer>> seen = new HashMap<>();

    // 路径中不能存在相同的两个值, 所以遍历树中的每个节点之后需要找到前一个相同值所在的位置
    // 我们需要从与当前节点最近的非重复节点开始计算特殊路径的长度以及节点个数
    private void dfs(int curr, int parent, int head) {
        // 根据节点的值获取一个堆栈用于找到最近的相同值节点
        var dq = seen.computeIfAbsent(values[curr], k -> new ArrayDeque<>());
        // 找到与当前节点的值相同的节点的所在位置, 这个节点需要比之前找到的最近节点更近
        if (!dq.isEmpty() && depth[next[dq.peek()]] > depth[head]) head = next[dq.peek()];

        // 现在我们找到了一段符合条件的特殊路径, 检查是否需要更新答案
        if (sum[curr] - sum[head] > maxLength) {
            maxLength = sum[curr] - sum[head];
            minimumNodes = depth[curr] - depth[head] + 1;
        } else if (sum[curr] - sum[head] == maxLength) {
            minimumNodes = Math.min(minimumNodes, depth[curr] - depth[head] + 1);
        }

        dq.push(curr);
        for (var e : g[curr].entrySet()) {
            if (e.getKey() == parent) continue;

            next[curr] = e.getKey();
            depth[e.getKey()] = depth[curr] + 1;
            sum[e.getKey()] = sum[curr] + e.getValue();

            dfs(e.getKey(), curr, head);
        }

        // 恢复现场
        dq.pop();
    }

    public static void main(String[] args) {
        assert Checker.check(new Solution().longestSpecialPath(new int[][]{{4,0,2},{3,1,8},{3,2,9},{3,4,8}}, new int[]{5,5,1,3,3}), new int[]{9, 2});

        assert Checker.check(new Solution().longestSpecialPath(new int[][]{{0,1,2},{1,2,3},{1,3,5},{1,4,4},{2,5,6}}, new int[]{2,1,2,1,3,1}), new int[]{6, 2});
        assert Checker.check(new Solution().longestSpecialPath(new int[][]{{1,0,8}}, new int[]{2,2}), new int[]{0, 1});
    }

}
